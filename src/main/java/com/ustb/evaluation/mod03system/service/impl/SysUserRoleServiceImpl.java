package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import com.ustb.evaluation.mod03system.domain.SysUserRole.SysUserRole;
import com.ustb.evaluation.mod03system.domain.SysUserRole.SysUserRoleBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends CurdServiceImpl<SysUserRole> {

    @Autowired
    CurdServiceImpl<SysUser> curdService;

    @Override
    protected void insertUpdateCheck(SysUserRole object, Boolean update) {
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "user_id", "=", object.getUser_id().toString()));
        list.add(new QuerySingle(true, "and", "role_id", "=", object.getRole_id().toString()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了用户id：" + object.getUser_id() + ",角色id:" + object.getRole_id());
        }
    }

    @Override
    protected void deleteCheck(Long id) {    }

    @Transactional
    public void saveBatch(SysUserRoleBatch sysUserRoleBatch) {
        //1.查看是否存在当前角色
        SysUser su = curdService.findById(sysUserRoleBatch.getUser_id());
        if (su == null) {
            throw new PromptException("不存在当前用户，请刷新数据后重试！");
        }
        //2.删除当前角色已有的数据
        String sql = "delete from sys_user_role where user_id=" + su.getId().toString() + ";";
        deleteBySQL(sql);//直接执行相关的语句,service里面没有，因为此功能内部使用，不对外开放

        //3.增加新的数据
        Long[] roles = sysUserRoleBatch.getRole_ids();
        if (roles == null || roles.length == 0) {
            return;
        }

        SysUserRole sysUserRole = new SysUserRole();
        for (int i = 0; i < roles.length; i++) {
            sysUserRole.setId(0L);
            sysUserRole.setUser_id(su.getId());
            sysUserRole.setRole_id(roles[i]);
            sysUserRole.setMemo("");
            insert(sysUserRole);
        }
    }

}
