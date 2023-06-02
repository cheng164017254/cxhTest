package com.ustb.evaluation.mod05test.domain.StuDormitory;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.QueryOrder;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @date 2022/11/27 - 21:53
 */
@Component
public class StuDormitoryConstant extends TableConstant<StuDormitory> {
    public StuDormitoryConstant(){
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.fcode fcode,a.fname fname,a.fcapacity fcapacity,a.memo memo";
        selectFrom = " from stu_dormitory a";
        selectFieldsSimple = selectFields;
        selectFromSimple = selectFrom;
        selectFieldsComplex = selectFields;
        selectFromComplex = selectFrom;
        tableName = "stu_dormitory";//表单名
        hasSafe = false;
        defaultOrders.add(new QueryOrder("fsort", true));
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("fcode", "a.fcode", "宿舍编码", "'", "'"));
        ls.add(new QueryField("fname", "a.fname", "宿舍名称", "'", "'"));
        ls.add(new QueryField("fcapacity", "a.fcapacity", "宿舍容量", "", ""));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("fcode");
        ls.add("fname");
        ls.add("fcapacity");
        ls.add("memo");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.fcode}");
        ls.add("#{object.fname}");
        ls.add("#{object.fcapacity}");
        ls.add("#{object.memo}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("fcode = #{object.fcode}");
        ls.add("fname = #{object.fname}");
        ls.add("fsort = #{object.fcapacity}");
        ls.add("memo = #{object.memo}");
        updateContent = ls;
    }
}
