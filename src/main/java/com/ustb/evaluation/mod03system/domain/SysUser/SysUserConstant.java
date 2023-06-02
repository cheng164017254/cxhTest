package com.ustb.evaluation.mod03system.domain.SysUser;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysUserConstant extends TableConstant<SysUser> {
    public SysUserConstant() {
        super();
    }

    public String selectFunction = "select code,name,memo from sys_function where id " +
            "in (select func_id from sys_menu_function where menu_id " +
            "in (select menu_id from sys_role_menu where role_id " +
            "in (select role_id from sys_user_role where user_id " +
            "in (select id from sys_user where username=#{list[0]})))) order by code";
    public String getSelectFunction(){
        return selectFunction;
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.dept_id dept_id,b.name dept_id_name, a.username username,a.nickname nickname," +
                "a.password password,a.email email,a.mobile mobile,a.avatar avatar,a.status status,a.memo memo,a.deleted deleted";
        selectFrom = " from `sys_user` a left outer join sys_dept b on a.dept_id = b.id";
        tableName = "sys_user";//表单名
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("dept_id", "a.dept_id", "部门编码", "'", "'"));
        ls.add(new QueryField("dept_id_name", "b.name", "部门名称", "'", "'"));
        ls.add(new QueryField("username", "a.username", "用户名称", "'", "'"));
        ls.add(new QueryField("nickname", "a.nickname", "用户昵称", "'", "'"));
        ls.add(new QueryField("email", "a.email", "邮件地址", "'", "'"));
        ls.add(new QueryField("mobile", "a.mobile", "手机号", "'", "'"));
        ls.add(new QueryField("avatar", "a.avatar", "头像", "'", "'"));
        ls.add(new QueryField("status", "a.status", "用户状态", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        ls.add(new QueryField("deleted", "a.deleted", "是否删除", "", ""));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("dept_id");
        ls.add("username");
        ls.add("nickname");
        ls.add("password");
        ls.add("email");
        ls.add("mobile");
        ls.add("avatar");
        ls.add("status");
        ls.add("memo");
        ls.add("createBy");
        ls.add("deleted");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.dept_id}");
        ls.add("#{object.username}");
        ls.add("#{object.nickname}");
        ls.add("#{object.password}");
        ls.add("#{object.email}");
        ls.add("#{object.mobile}");
        ls.add("#{object.avatar}");
        ls.add("#{object.status}");
        ls.add("#{object.memo}");
        ls.add("#{operator}");
        ls.add("#{object.deleted}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("dept_id = #{object.dept_id}");
        ls.add("username = #{object.username}");
        ls.add("nickname = #{object.nickname}");
        ls.add("email = #{object.email}");
        ls.add("mobile = #{object.mobile}");
        ls.add("avatar = #{object.avatar}");
        ls.add("status = #{object.status}");
        ls.add("memo = #{object.memo}");
        ls.add("lastUpdateBy = #{operator}");
        ls.add("deleted = #{object.deleted}");
        updateContent = ls;
    }
}
