package com.ustb.evaluation.mod03system.domain.SysFunction;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysFunctionConstant extends TableConstant<SysFunction> {
    public SysFunctionConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.code code,a.name name,a.authority authority,a.type type,a.memo memo";//查询列表
        selectFrom = " from `sys_function` a";
        tableName = "sys_function";//表单名
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("code", "a.code", "功能编码", "'", "'"));
        ls.add(new QueryField("name", "a.name", "功能名称", "'", "'"));
        ls.add(new QueryField("authority", "a.authority", "授权标识", "'", "'"));
        ls.add(new QueryField("type", "a.type", "数据类型", "", ""));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("code");
        ls.add("name");
        ls.add("authority");
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
        ls.add("#{object.authority}");
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
        ls.add("authority = #{object.authority}");
        ls.add("type = #{object.type}");
        ls.add("memo = #{object.memo}");
        ls.add("lastUpdateBy = #{operator}");
        updateContent = ls;
    }
}
