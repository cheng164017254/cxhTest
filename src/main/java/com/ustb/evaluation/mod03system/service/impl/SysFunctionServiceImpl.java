package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.domain.query.QueryOrder;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunctionTree;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysFunctionServiceImpl extends CurdServiceImpl<SysFunction> {

    @Override
    protected void insertUpdateCheck(SysFunction object, Boolean update) {
        //1.功能编码的唯一性
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "code", "=", object.getCode()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了功能编码：" + object.getCode());
        }

        //2.新编码长度大于2时，必须要确定存在上级编码
        if (object.getCode().length() > 2) {
            String codeParent = object.getCode().substring(0, object.getCode().length() - 2);
            list = new ArrayList<>();
            list.add(new QuerySingle(true, "and", "code", "=", codeParent));
            if (!isExisted(list)) {
                throw new PromptException("功能编码为‘" + object.getCode() + "'不存在上级功能，数据错误，请检查！");
            }
        }

        //3.下级编码存在时，修改当前编码，必须同时将下级编码的前缀相同的也修改了
        if (update) {
            // (1)首先查找要修改的编码
            SysFunction sysFunctionOld = findById(object.getId());
            if (sysFunctionOld == null) {
                throw new PromptException("没有找到要修改的功能信息：" + object.getName() + ",请核查!");
            }
            if (!object.getCode().equals(sysFunctionOld.getCode())) {
                if (!(object.getCode().length() == sysFunctionOld.getCode().length())) {
                    throw new PromptException("编码只能更改为长度相同的另一个编码：" + object.getCode() + ",请核查!");
                }
                String begin = Integer.toString(object.getCode().length() + 1);

                String sql = "update sys_function set code=concat('" + object.getCode() + "',substr(code," + begin + ")) " +
                        "where code like '" + sysFunctionOld.getCode() + "%';";
                mapper.updateBySQL(sql);//直接执行相关的语句,service里面没有，因为此功能内部使用，不对外开放
            }
        }
    }

    @Override
    protected void deleteCheck(Long id) {
        SysFunction sf = findById(id);
        if (sf != null) {
            List<QuerySingle> ls = new ArrayList<>();
            ls.add(new QuerySingle(true, "and", "code", "LM", sf.getCode()));
            ls.add(new QuerySingle(true, "and", "code", "<>", sf.getCode()));
            if (isExisted(ls)) {
                throw new PromptException("存在下级功能，不能删除当前功能：" + sf.getName() + "，请检查！");
            }
        }
    }

    public List<SysFunctionTree> findTree() {
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        List<QuerySingle> lsquery = new ArrayList<>();

        queryConditionsFlexible.setListCondition(lsquery);
        List<QueryOrder> lsorder = new ArrayList<>();
        lsorder.add(new QueryOrder("code", true));
        queryConditionsFlexible.setListOrder(lsorder);
        List<SysFunction> lsFunc = find(queryConditionsFlexible);

        List<SysFunction> lsTop = new ArrayList<>();
        String code = "";
        for (int i = 0; i < lsFunc.size(); i++) {
            code = lsFunc.get(i).getCode();
            if (code.length() == 2) {
                lsTop.add(lsFunc.get(i));
            }
        }

        List<SysFunctionTree> ls = new ArrayList<>();
        for (int i = 0; i < lsTop.size(); i++) {
            SysFunction sf = lsTop.get(i);
            SysFunctionTree sft = new SysFunctionTree(sf.getId(), sf.getCode(), sf.getName(), sf.getAuthority(), sf.getType(), sf.getMemo());
            findTreeChildren(sft, lsFunc);
            ls.add(sft);
        }
        return ls;
    }

    //给定一个根结点，形成树形数据
    private void findTreeChildren(SysFunctionTree nodeNow, List<SysFunction> ls) {
        String codeNow = nodeNow.getCode();
        int lenNow = codeNow.length();
        int len;
        boolean bFind = false;
        String code, codePrefix;
        SysFunctionTree sft;
        SysFunction sf;
        for (int i = 0; i < ls.size(); i++) {
            sf = ls.get(i);
            code = sf.getCode();
            len = code.length();
            if (len == (lenNow + 2)) {
                codePrefix = code.substring(0, lenNow);
                if (codePrefix.equals(codeNow)) {
                    sft = new SysFunctionTree(sf.getId(), sf.getCode(), sf.getName(), sf.getAuthority(), sf.getType(), sf.getMemo());
                    nodeNow.getChildren().add(sft);
                    findTreeChildren(sft, ls);
                }
            }
        }
    }
}
