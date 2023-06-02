package com.ustb.evaluation.mod03system.domain.SysRoleMenu;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysRoleMenuConstant extends TableConstant<SysRoleMenu> {
    public SysRoleMenuConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.role_id role_id,b.code role_id_code,b.name role_id_name," +
                "a.menu_id menu_id,c.code menu_id_code,c.name menu_id_name,a.memo memo";//查询列表
        selectFrom = " from `sys_role_menu` a left outer join `sys_role` b on a.role_id=b.id " +
                "left outer join `sys_menu` c on a.menu_id=c.id";
        tableName = "sys_role_menu";//表单名
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("role_id", "a.role_id", "角色id", "", ""));
        ls.add(new QueryField("role_id_code", "b.code", "角色编码", "'", "'"));
        ls.add(new QueryField("role_id_name", "b.name", "角色名称", "'", "'"));
        ls.add(new QueryField("menu_id", "a.menu_id", "菜单id", "", ""));
        ls.add(new QueryField("menu_id_code", "c.code", "菜单编码", "'", "'"));
        ls.add(new QueryField("menu_id_name", "c.name", "菜单名称", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("role_id");
        ls.add("menu_id");
        ls.add("memo");
        ls.add("createBy");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.role_id}");
        ls.add("#{object.menu_id}");
        ls.add("#{object.memo}");
        ls.add("#{operator}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("role_id = #{object.role_id}");
        ls.add("menu_id = #{object.menu_id}");
        ls.add("memo = #{object.memo}");
        ls.add("lastUpdateBy = #{operator}");
        updateContent = ls;
    }
}
