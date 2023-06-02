package com.ustb.evaluation.mod05test.domain.StuAcid;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @Description
 * @create 2022-11-27-20:36
 */
@Component
public class StuAcidConstant extends TableConstant<StuAcid>{
    public StuAcidConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.fstudentId fstudentId,a.fsampleTime fsampleTime,a.ftestTime ftestTime,a.ftestUnit ftestUnit,a.fresult fresult,a.ftestAddress ftestAddress,a.memo memo";
        selectFrom = " from stu_acid a";
        selectFieldsSimple = selectFields;
        selectFromSimple = selectFrom;
        selectFieldsComplex = selectFields;
        selectFromComplex = selectFrom;
        tableName = "stu_acid";//表单名
        hasSafe = false;
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("fstudentId", "a.fstudentId", "学生id", "", ""));
        ls.add(new QueryField("fsampleTime", "a.fsampleTime", "采样时间", "'", "'"));
        ls.add(new QueryField("ftestTime", "a.ftestTime", "检测时间", "'", "'"));
        ls.add(new QueryField("ftestUnit", "a.ftestUnit", "检测单位", "", ""));
        ls.add(new QueryField("fresult", "a.fresult", "检测结果", "", ""));
        ls.add(new QueryField("ftestAddress", "a.ftestAddress", "采样地点", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("fstudentId");
        ls.add("fsampleTime");
        ls.add("ftestTime");
        ls.add("ftestUnit");
        ls.add("fresult");
        ls.add("ftestAddress");
        ls.add("memo");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.fstudentId}");
        ls.add("#{object.fsampleTime}");
        ls.add("#{object.ftestTime}");
        ls.add("#{object.ftestUnit}");
        ls.add("#{object.fresult}");
        ls.add("#{object.ftestAddress}");
        ls.add("#{object.memo}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("fstudentId = #{object.fstudentId}");
        ls.add("fsampleTime = #{object.fsampleTime}");
        ls.add("ftestTime = #{object.ftestTime}");
        ls.add("ftestUnit = #{object.ftestUnit}");
        ls.add("fresult = #{object.fresult}");
        ls.add("ftestAddress = #{object.ftestAddress}");
        ls.add("memo = #{object.memo}");
        updateContent = ls;
    }
}
