package com.ustb.evaluation.mod05test.domain.StuAccommodation;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.QueryOrder;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @date 2022/11/27 - 20:33
 */
@Component
public class StuAccommodationConstant extends TableConstant<StuAccommodation> {

    public StuAccommodationConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.fdormitoryId fdormitoryId,a.fstudentId fstudentId,a.fbed fbed,a.fin fin,a.memo memo";
        selectFrom = " from stu_accommodation a";
        selectFieldsSimple = selectFields;
        selectFromSimple = selectFrom;
        selectFieldsComplex = selectFields;
        selectFromComplex = selectFrom;
        tableName = "stu_accommodation";//表单名
        hasSafe = false;
        defaultOrders.add(new QueryOrder("id",true));
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("fdormitoryId", "a.fdormitoryId", "宿舍id", "'", "'"));
        ls.add(new QueryField("fstudentId", "a.fstudentId", "学生id", "'", "'"));
        ls.add(new QueryField("fbed", "a.fbed", "床位", "", ""));
        ls.add(new QueryField("fin", "a.fin", "是否在宿舍", "", ""));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("fdormitoryId");
        ls.add("fstudentId");
        ls.add("fbed");
        ls.add("fin");
        ls.add("memo");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.fdormitoryId}");
        ls.add("#{object.fstudentId}");
        ls.add("#{object.fbed}");
        ls.add("#{object.fin}");
        ls.add("#{object.memo}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("fdormitoryId = #{object.fdormitoryId}");
        ls.add("fstudentId = #{object.fstudentId}");
        ls.add("fbed = #{object.fbed}");
        ls.add("fin = #{object.fin}");
        ls.add("memo = #{object.memo}");
        updateContent = ls;
    }
}
