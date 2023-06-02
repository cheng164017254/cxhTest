package com.ustb.evaluation.mod03system.domain.SysMenu;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysMenuConstant extends TableConstant<SysMenu> {
    public SysMenuConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.code code,a.name name,a.icon icon,a.url url,a.type type ,a.memo memo";//查询列表
        selectFrom = " from `sys_menu` a";
        tableName = "sys_menu";//表单名
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("code", "a.code", "菜单编码", "'", "'"));
        ls.add(new QueryField("name", "a.name", "菜单名称", "'", "'"));
        ls.add(new QueryField("icon", "a.icon", "图标名称", "'", "'"));
        ls.add(new QueryField("url", "a.url", "url地址", "'", "'"));
        ls.add(new QueryField("type", "a.type", "菜单类型", "", ""));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("code");
        ls.add("name");
        ls.add("icon");
        ls.add("url");
        ls.add("type");
        ls.add("memo");
        ls.add("createBy");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.code}");
        ls.add("#{object.name}");
        ls.add("#{object.icon}");
        ls.add("#{object.url}");
        ls.add("#{object.type}");
        ls.add("#{object.memo}");
        ls.add("#{operator}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("code = #{object.code}");
        ls.add("name = #{object.name}");
        ls.add("icon = #{object.icon}");
        ls.add("url = #{object.url}");
        ls.add("type = #{object.type}");
        ls.add("memo = #{object.memo}");
        ls.add("lastUpdateBy = #{operator}");
        updateContent = ls;
    }
}
