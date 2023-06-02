package com.ustb.evaluation.mod03system.domain.SysMenuFuntion;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysMenuFunctionConstant extends TableConstant<SysMenuFunction> {
    public SysMenuFunctionConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.menu_id menu_id,b.code menu_id_code,b.name menu_id_name," +
                "a.func_id func_id,c.code func_id_code,c.name func_id_name,c.authority func_id_authority,a.memo memo";//查询列表
        selectFrom = " from `sys_menu_function` a left outer join `sys_menu` b on a.menu_id=b.id left outer join `sys_function` c on a.func_id=c.id";
        tableName = "sys_menu_function";//表单名
    }

    @Override
    public void initQueryFields() { //用于java字段和数据中的字段的对应
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("menu_id", "a.menu_id", "菜单id", "'", "'"));
        ls.add(new QueryField("menu_id_code", "b.code", "菜单编码", "'", "'"));
        ls.add(new QueryField("menu_id_name", "b.name", "菜单名称", "'", "'"));
        ls.add(new QueryField("func_id", "a.func_id", "功能id", "'", "'"));
        ls.add(new QueryField("func_id_code", "c.code", "功能编码", "'", "'"));
        ls.add(new QueryField("func_id_name", "c.name", "功能名称", "'", "'"));
        ls.add(new QueryField("func_id_authority", "c.authority", "功能授权", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("menu_id");
        ls.add("func_id");
        ls.add("memo");
        ls.add("createBy");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.menu_id}");
        ls.add("#{object.func_id}");
        ls.add("#{object.memo}");
        ls.add("#{operator}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("menu_id = #{object.menu_id}");
        ls.add("func_id = #{object.func_id}");
        ls.add("memo = #{object.memo}");
        ls.add("lastUpdateBy = #{operator}");
        updateContent = ls;
    }
}
