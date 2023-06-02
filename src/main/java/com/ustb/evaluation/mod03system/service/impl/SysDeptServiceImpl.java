package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.domain.query.QueryOrder;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.CurdService;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysDept.SysDept;
import com.ustb.evaluation.mod03system.domain.SysDept.SysDeptTree;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl extends CurdServiceImpl<SysDept> {

    @Autowired
    protected CurdService<SysUser> serviceUser;

    @Override
    protected void insertUpdateCheck(SysDept object, Boolean update) {
        //1.编码的唯一性验证
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "code", "=", object.getCode()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了部门编码：" + object.getCode());
        }

        //2.名称的唯一性验证
        list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }
        list.add(new QuerySingle(true, "and", "name", "=", object.getName()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了部门名称：" + object.getName());
        }

        //3.新编码长度大于2时，必须要确定存在上级编码
        if (object.getCode().length() > 2) {
            String codeParent = object.getCode().substring(0, object.getCode().length() - 2);
            list = new ArrayList<>();
            list.add(new QuerySingle(true, "and", "code", "=", codeParent));
            if (!isExisted(list)) {
                throw new PromptException("编号为‘" + object.getCode() + "'不存在上级部门，数据错误，请检查！");
            }
        }

        //4.下级编码存在时，修改当前编码，必须同时将下级编码的前缀相同的也修改了
        if (update) {
            // (1)首先查找要修改的编码
            SysDept sysDeptOld = findById(object.getId());
            if (sysDeptOld == null) {
                throw new PromptException("没有找到要修改的部门信息：" + object.getName() + ",请核查!");
            }
            if (!object.getCode().equals(sysDeptOld.getCode())) {
                if (!(object.getCode().length() == sysDeptOld.getCode().length())) {
                    throw new PromptException("编码只能更改为长度相同的另一个编码：" + object.getCode() + ",请核查!");
                }
                String begin = Integer.toString(object.getCode().length() + 1);

                String sql = "update sys_dept set code=concat('" + object.getCode() + "',substr(code," + begin + ")) " +
                        "where code like '" + sysDeptOld.getCode() + "%';";
                mapper.updateBySQL(sql);//直接执行相关的语句,service里面没有，因为此功能内部使用，不对外开放
            }
        }
    }

    @Override
    protected void deleteCheck(Long id) {
        SysDept sd = findById(id);
        if (sd != null) {
            List<QuerySingle> ls = new ArrayList<>();
            ls.add(new QuerySingle(true, "and", "code", "LM", sd.getCode()));
            ls.add(new QuerySingle(true, "and", "code", "<>", sd.getCode()));
            if (isExisted(ls)) {
                throw new PromptException("存在下级部门，不能删除当前部门：" + sd.getName() + "，请检查！");
            }
            //判断是否有用户存在，存在则不能删除此id代表的部门
            ls = new ArrayList<>();
            ls.add(new QuerySingle(true, "and", "dept_id", "=", id.toString()));
            if (serviceUser.isExisted(ls)) {
                throw new PromptException("存在当前部门的用户，不能删除当前部门：" + sd.getName() + "，请检查！");
            }


        }
    }

    public List<SysDeptTree> findTree(Boolean includeDeleted) {
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        List<QuerySingle> lsquery = new ArrayList<>();
        if (!includeDeleted) {
            lsquery.add(new QuerySingle(true, "and", "deleted", "=", "0"));
        }
        queryConditionsFlexible.setDirect(false);
        queryConditionsFlexible.setListCondition(lsquery);
        List<QueryOrder> lsorder = new ArrayList<>();
        lsorder.add(new QueryOrder("code", true));
        queryConditionsFlexible.setListOrder(lsorder);
        List<SysDept> lsDept = find(queryConditionsFlexible);

        List<SysDept> lsTop = new ArrayList<>();
        String code = "";
        for (int i = 0; i < lsDept.size(); i++) {
            code = lsDept.get(i).getCode();
            if (code.length() == 2) {
                lsTop.add(lsDept.get(i));
            }
        }

        List<SysDeptTree> ls = new ArrayList<>();
        for (int i = 0; i < lsTop.size(); i++) {
            SysDept sd = lsTop.get(i);
            SysDeptTree sdt = new SysDeptTree(sd.getId(), sd.getCode(), sd.getName(), sd.getMemo(), sd.getDeleted());
            findTreeChildren(sdt, lsDept);
            ls.add(sdt);
        }
        return ls;
    }

    //给定一个根结点，形成树形数据
    private void findTreeChildren(SysDeptTree nodeNow, List<SysDept> ls) {
        String codeNow = nodeNow.getCode();
        int lenNow = codeNow.length();
        int len;
        boolean bFind = false;
        String code, codePrefix;
        SysDeptTree sdt;
        SysDept sd;
        for (int i = 0; i < ls.size(); i++) {
            sd = ls.get(i);
            code = sd.getCode();
            len = code.length();
            if (len == (lenNow + 2)) {
                codePrefix = code.substring(0, lenNow);
                if (codePrefix.equals(codeNow)) {
                    sdt = new SysDeptTree(sd.getId(), sd.getCode(), sd.getName(), sd.getMemo(), sd.getDeleted());
                    nodeNow.getChildren().add(sdt);
                    findTreeChildren(sdt, ls);
                }
            }
        }
    }
}
