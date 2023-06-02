package com.ustb.evaluation.mod03system.domain.SysUserRole;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysUserRoleConstant extends TableConstant<SysUserRole> {
    public SysUserRoleConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.user_id user_id,b.username user_id_username," +
                "a.role_id role_id,c.code role_id_code,c.name role_id_name,a.memo memo";//查询列表
        selectFrom = " from `sys_user_role` a left outer join `sys_user` b on a.user_id=b.id " +
                "left outer join  `sys_role` c on a.role_id=c.id ";
        tableName = "sys_user_role";//表单名
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("user_id", "a.user_id", "用户id", "'", "'"));
        ls.add(new QueryField("user_id_username", "b.username", "用户名称", "'", "'"));
        ls.add(new QueryField("role_id", "a.role_id", "角色id", "'", "'"));
        ls.add(new QueryField("role_id_code", "c.code", "角色编码", "'", "'"));
        ls.add(new QueryField("role_id_name", "c.name", "角色名称", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("user_id");
        ls.add("role_id");
        ls.add("memo");
        ls.add("createBy");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.user_id}");
        ls.add("#{object.role_id}");
        ls.add("#{object.memo}");
        ls.add("#{operator}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("user_id = #{object.user_id}");
        ls.add("role_id = #{object.role_id}");
        ls.add("memo = #{object.memo}");
        ls.add("lastUpdateBy = #{operator}");
        updateContent = ls;
    }
}
