package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.domain.query.QueryOrder;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.CurdService;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenu;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenuTree;
import com.ustb.evaluation.mod03system.domain.SysMenuFuntion.SysMenuFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl extends CurdServiceImpl<SysMenu> {

    @Autowired
    protected CurdService<SysMenuFunction> menuFunctionService; //menu对应的功能表的service

    @Override
    protected void insertUpdateCheck(SysMenu object, Boolean update) {
        //1.菜单编码的唯一性验证
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "code", "=", object.getCode()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了菜单编码：" + object.getCode());
        }

        //2.新编码长度大于2时，必须要确定存在上级编码
        if (object.getCode().length() > 2) {
            String codeParent = object.getCode().substring(0, object.getCode().length() - 2);
            list = new ArrayList<>();
            list.add(new QuerySingle(true, "and", "code", "=", codeParent));
            if (!isExisted(list)) {
                throw new PromptException("菜单编码为‘" + object.getCode() + "'不存在上级菜单，数据错误，请检查！");
            }
        }

        //3.下级编码存在时，修改当前编码，必须同时将下级编码的前缀相同的也修改了
        if (update) {
            // (1)首先查找要修改的编码
            SysMenu sysMenuOld = findById(object.getId());
            if (sysMenuOld == null) {
                throw new PromptException("没有找到要修改的菜单信息：" + object.getName() + ",请核查!");
            }
            if (!object.getCode().equals(sysMenuOld.getCode())) {
                if (!(object.getCode().length() == sysMenuOld.getCode().length())) {
                    throw new PromptException("编码只能更改为长度相同的另一个编码：" + object.getCode() + ",请核查!");
                }
                String begin = Integer.toString(object.getCode().length() + 1);

                String sql = "update sys_menu set code=concat('" + object.getCode() + "',substr(code," + begin + ")) " +
                        "where code like '" + sysMenuOld.getCode() + "%';";
                mapper.updateBySQL(sql);//直接执行相关的语句,service里面没有，因为此功能内部使用，不对外开放
            }
        }


    }

    @Override
    protected void deleteCheck(Long id) {
        SysMenu sm = findById(id);
        if (sm != null) {
            List<QuerySingle> ls = new ArrayList<>();
            ls.add(new QuerySingle(true, "and", "code", "LM", sm.getCode()));
            ls.add(new QuerySingle(true, "and", "code", "<>", sm.getCode()));
            if (isExisted(ls)) {
                throw new PromptException("存在下级菜单，不能删除当前菜单：" + sm.getName() + "，请检查！");
            }
        }
    }

    //获得菜单的树形结构
    public List<SysMenuTree> findTree() {
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        List<QuerySingle> lsquery = new ArrayList<>();
        queryConditionsFlexible.setDirect(false);
        queryConditionsFlexible.setListCondition(lsquery);
        List<QueryOrder> lsorder = new ArrayList<>();
        lsorder.add(new QueryOrder("code", true));
        queryConditionsFlexible.setListOrder(lsorder);
        List<SysMenu> lsMenu = find(queryConditionsFlexible);
        return createTree(lsMenu);
    }

    //以下三个功能形成树形结构
    public List<SysMenuTree> createTree(List<SysMenu> lsMenu) {
        List<SysMenu> lsTop = new ArrayList<>();
        String code = "";
        for (int i = 0; i < lsMenu.size(); i++) {
            code = lsMenu.get(i).getCode();
            if (code.length() == 2) {
                lsTop.add(lsMenu.get(i));
            }
        }

        List<SysMenuTree> ls = new ArrayList<>();
        for (int i = 0; i < lsTop.size(); i++) {
            SysMenu sm = lsTop.get(i);
            SysMenuTree smt = new SysMenuTree(sm.getId(), sm.getCode(), sm.getName(), sm.getIcon(), sm.getUrl(), sm.getType(), sm.getMemo());
            smt.setFunctions(getMenuFunction(sm.getId().toString()));
            createTreeChildren(smt, lsMenu);
            ls.add(smt);
        }
        return ls;
    }

    //给定一个根结点，形成树形数据
    private void createTreeChildren(SysMenuTree nodeNow, List<SysMenu> ls) {
        String codeNow = nodeNow.getCode();
        int lenNow = codeNow.length();
        int len;
        String code, codePrefix;
        SysMenuTree smt;
        SysMenu sm;
        for (int i = 0; i < ls.size(); i++) {
            sm = ls.get(i);
            code = sm.getCode();
            len = code.length();
            if (len == (lenNow + 2)) {
                codePrefix = code.substring(0, lenNow);
                if (codePrefix.equals(codeNow)) {
                    smt = new SysMenuTree(sm.getId(), sm.getCode(), sm.getName(), sm.getIcon(), sm.getUrl(), sm.getType(), sm.getMemo());
                    smt.setFunctions(getMenuFunction(sm.getId().toString()));
                    nodeNow.getChildren().add(smt);
                    createTreeChildren(smt, ls);
                }
            }
        }
    }

    //获得菜单的功能项
    private String getMenuFunction(String Id) {
        List<QuerySingle> lsqc = new ArrayList<>();
        List<QueryOrder> lsqo = new ArrayList<>();
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        queryConditionsFlexible.setDirect(false);
        lsqc.add(new QuerySingle(true, "and", "menu_id", "=", Id));
        lsqo.add(new QueryOrder("func_id_code", true));
        queryConditionsFlexible.setListOrder(lsqo);
        queryConditionsFlexible.setListCondition(lsqc);
        List<SysMenuFunction> lssm = menuFunctionService.find(queryConditionsFlexible);
        StringBuilder sb = new StringBuilder();
        if (lssm != null && lssm.size() > 0) {
            sb.append(lssm.get(0).getFunc_id_authority());
            for (int i = 1; i < lssm.size(); i++) {
                sb.append(",");
                sb.append(lssm.get(i).getFunc_id_authority());
            }
        }
        return sb.toString();
    }
}
