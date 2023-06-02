package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.page.PageRequest;
import com.ustb.evaluation.mod01common.domain.page.PageRequestFlexible;
import com.ustb.evaluation.mod01common.domain.page.PageResult;
import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import com.ustb.evaluation.mod03system.domain.SysRole.SysRole;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUserConstant;
import com.ustb.evaluation.mod03system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl extends CurdServiceImpl<SysUser> {
    @Autowired
    private SysUserMapper sum; //为了selectFunction功能添加的

    @Autowired
    private SysUserConstant suc;//为了selectFunction功能添加的

    @Autowired
    private SysRoleServiceImpl sysRoleService;//角色service对象

    @Override
    protected void insertUpdateCheck(SysUser object, Boolean update) {
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "username", "=", object.getUsername()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了用户名称：" + object.getUsername());
        }
    }

    @Override
    protected void deleteCheck(Long id) {

    }

    //根据用户id返回当前用户所具有的角色
    public List<SysRole> findUserRoles(Long user_id) {
        String sql = "Where a.id in (select role_id from sys_user_role where user_id=" + user_id.toString() + ")";
        String sqlCHN = "用户id为：" + user_id.toString() + "的用户角色授权";
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        queryConditionsFlexible.setDirect(true);
        queryConditionsFlexible.setSqlCondition(sql);
        queryConditionsFlexible.setSqlCondtionCHN(sqlCHN);
        return sysRoleService.find(queryConditionsFlexible);
    }

    private String listToString(List<SysRole> lsRole) {
        StringBuilder sb = new StringBuilder();
        for (SysRole so : lsRole) {
            sb.append("," + so.getName());
        }
        String sr = sb.toString();
        if (sr.length() > 0) sr = sr.substring(1);
        return sr;
    }

    @Override
    public PageResult findPage(PageRequestFlexible pageRequest) {
        PageResult pageResult = super.findPage(pageRequest);
        List<?> ls = pageResult.getContent();

        if (ls.size() <= 0) return pageResult;

        SysUser sysUser;
        List<SysUser> lsNew = new ArrayList<>();
        for (Object obj : ls) {
            sysUser = (SysUser) obj;
            sysUser.setRoles(listToString(findUserRoles(sysUser.getId())));
        }
       // pageResult.setContent(lsNew);
        return pageResult;
    }



    //新添加的功能，传入用户名称，得到所有的权限（功能）
    public List<SysFunction> selectFunction(String username) {
        String sql = suc.getSelectFunction();
        List<String> list = new ArrayList<>();
        list.add(username);
        return sum.selectFunction(sql, list);
    }


}
