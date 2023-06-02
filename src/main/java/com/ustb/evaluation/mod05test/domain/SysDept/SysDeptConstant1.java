package com.ustb.evaluation.mod05test.domain.SysDept;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.QueryOrder;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @date 2022/11/27 - 16:55
 */
@Component
public class SysDeptConstant1 extends TableConstant<SysDept> {
    public SysDeptConstant1() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.code code,a.name name,a.memo memo,a.deleted deleted";
        selectFrom = " from sys_dept a";
        selectFieldsSimple = selectFields;
        selectFromSimple = selectFrom;
        selectFieldsComplex = selectFields;
        selectFromComplex = selectFrom;
        tableName = "sys_dept";//表单名
        hasSafe = false;
        defaultOrders.add(new QueryOrder("id",true));
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("code", "a.code", "部门编码", "'", "'"));
        ls.add(new QueryField("name", "a.name", "部门名称", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        ls.add(new QueryField("deleted", "a.deleted", "逻辑删除", "", ""));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("code");
        ls.add("name");
        ls.add("memo");
        ls.add("deleted");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.code}");
        ls.add("#{object.name}");
        ls.add("#{object.memo}");
        ls.add("#{object.deleted}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("code = #{object.code}");
        ls.add("name = #{object.name}");
        ls.add("memo = #{object.memo}");
        ls.add("deleted = #{object.deleted}");
        updateContent = ls;
    }
}
