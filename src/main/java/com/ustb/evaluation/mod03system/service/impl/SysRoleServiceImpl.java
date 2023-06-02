package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenu;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenuTree;
import com.ustb.evaluation.mod03system.domain.SysRole.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl extends CurdServiceImpl<SysRole>  {

    @Autowired
    protected SysMenuServiceImpl menuService; //menu的service

    @Override
    protected void insertUpdateCheck(SysRole object, Boolean update) {
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "code", "=", object.getCode()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了角色编码：" + object.getCode());
        }

        //2.首先验证name的唯一性
        list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }
        list.add(new QuerySingle(true, "and", "name", "=", object.getName()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了角色名称：" + object.getName());
        }
    }

    @Override
    protected void deleteCheck(Long id) {

    }


    //根据角色id返回当前角色具有的功能树
    public List<SysMenuTree> findRoleMenusTree(Long role_id) {
        String sql = "Where a.id in (select menu_id from sys_role_menu where role_id=" + role_id.toString() + ")";
        String sqlCHN = "角色id为：" + role_id.toString() + "的菜单授权";
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        queryConditionsFlexible.setDirect(true);
        queryConditionsFlexible.setSqlCondition(sql);
        queryConditionsFlexible.setSqlCondtionCHN(sqlCHN);
        List<SysMenu> lsMenu = menuService.find(queryConditionsFlexible);
        return menuService.createTree(lsMenu);
    }

    //根据角色id返回当前角色具有的功能树
    public List<Long> findRoleMenusButton(Long role_id) {
        String sql = "Where (a.type <> 0) and  (a.id in (select menu_id from sys_role_menu where role_id=" + role_id.toString() + "))";
        String sqlCHN = "角色id为：" + role_id.toString() + "的菜单按钮授权";
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        queryConditionsFlexible.setDirect(true);
        queryConditionsFlexible.setSqlCondition(sql);
        queryConditionsFlexible.setSqlCondtionCHN(sqlCHN);
        List<SysMenu> lsMenu = menuService.find(queryConditionsFlexible);
        List<Long> ls = new ArrayList<>();
        for (SysMenu sm : lsMenu) {
            ls.add(sm.getId());
        }
        return ls;
    }


}
