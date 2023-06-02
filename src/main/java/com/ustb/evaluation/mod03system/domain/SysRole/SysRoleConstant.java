package com.ustb.evaluation.mod03system.domain.SysRole;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysRoleConstant extends TableConstant<SysRole> {
    public SysRoleConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.code code,a.name name,a.memo memo";//查询列表
        selectFrom = " from `sys_role` a";
        tableName = "sys_role";//表单名
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("code", "a.code", "角色编码", "'", "'"));
        ls.add(new QueryField("name", "a.name", "角色名称", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("code");
        ls.add("name");
        ls.add("memo");
        ls.add("createBy");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.code}");
        ls.add("#{object.name}");
        ls.add("#{object.memo}");
        ls.add("#{operator}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("code = #{object.code}");
        ls.add("name = #{object.name}");
        ls.add("memo = #{object.memo}");
        ls.add("lastUpdateBy = #{operator}");
        updateContent = ls;
    }
}
