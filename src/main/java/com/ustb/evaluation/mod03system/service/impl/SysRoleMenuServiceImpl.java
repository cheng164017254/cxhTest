package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysRole.SysRole;
import com.ustb.evaluation.mod03system.domain.SysRoleMenu.SysRoleMenu;
import com.ustb.evaluation.mod03system.domain.SysRoleMenu.SysRoleMenuBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends CurdServiceImpl<SysRoleMenu> {

    @Autowired
    CurdServiceImpl<SysRole> curdService;

    @Override
    protected void insertUpdateCheck(SysRoleMenu object, Boolean update) {
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "role_id", "=", object.getRole_id().toString()));
        list.add(new QuerySingle(true, "and", "menu_id", "=", object.getMenu_id().toString()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了角色id：" + object.getRole_id() + ",菜单id:" + object.getMenu_id());
        }
    }

    @Override
    protected void deleteCheck(Long id) {

    }

    @Transactional
    public void saveBatch(SysRoleMenuBatch sysRoleMenuBatch) {
        //1.查看是否存在当前角色
        SysRole sr = curdService.findById(sysRoleMenuBatch.getRole_id());
        if (sr == null) {
            throw new PromptException("不存在当前角色，请刷新数据后重试！");
        }
        //2.删除当前角色已有的数据
        String sql = "delete from sys_role_menu where role_id=" + sr.getId().toString() + ";";
        deleteBySQL(sql);//直接执行相关的语句,service里面没有，因为此功能内部使用，不对外开放

        //3.增加新的数据
        Long[] menus = sysRoleMenuBatch.getMenu_ids();
        if (menus == null || menus.length == 0) {
            return;
        }

        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        for (int i = 0; i < menus.length; i++) {
            sysRoleMenu.setId(0L);
            sysRoleMenu.setRole_id(sr.getId());
            sysRoleMenu.setMenu_id(menus[i]);
            sysRoleMenu.setMemo("");
            insert(sysRoleMenu);
        }
    }



}
