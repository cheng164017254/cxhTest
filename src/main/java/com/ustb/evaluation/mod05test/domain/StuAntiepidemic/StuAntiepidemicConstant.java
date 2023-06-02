package com.ustb.evaluation.mod05test.domain.StuAntiepidemic;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuHong
 * @Description
 * @create 2022-11-27-21:53
 */
@Component
public class StuAntiepidemicConstant extends TableConstant<StuAntiepidemic> {
    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.fstudentId fstudentId,a.finCampus finCampus,a.flocation flocation,a.fleaveTime fleaveTime,a.fhealthId fhealthId,a.fhealthMemo fhealthMemo,a.fstatusID fstatusID,a.memo memo";
        selectFrom = " from stu_antiepidemic a";
        selectFieldsSimple = selectFields;
        selectFromSimple = selectFrom;
        selectFieldsComplex = selectFields;
        selectFromComplex = selectFrom;
        tableName = "stu_antiepidemic";//表单名
        hasSafe = false;
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("fstudentId", "a.fstudentId", "学生id", "", ""));
        ls.add(new QueryField("finCampus", "a.finCampus", "是否在校", "'", "'"));
        ls.add(new QueryField("flocation", "a.flocation", "目前位置", "'", "'"));
        ls.add(new QueryField("fleaveTime", "a.fleaveTime", "离校时间", "", ""));
        ls.add(new QueryField("fhealthId", "a.fhealthId", "健康等级", "", ""));
        ls.add(new QueryField("fhealthMemo", "a.fhealthMemo", "健康描述", "'", "'"));
        ls.add(new QueryField("fstatusID", "a.fstatusID", "健康码", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("fstudentId");
        ls.add("finCampus");
        ls.add("flocation");
        ls.add("fleaveTime");
        ls.add("fhealthId");
        ls.add("fhealthMemo");
        ls.add("fstatusID");
        ls.add("memo");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.fstudentId}");
        ls.add("#{object.finCampus}");
        ls.add("#{object.flocation}");
        ls.add("#{object.fleaveTime}");
        ls.add("#{object.fhealthId}");
        ls.add("#{object.fhealthMemo}");
        ls.add("#{object.fstatusID}");
        ls.add("#{object.memo}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("fstudentId = #{object.fstudentId}");
        ls.add("finCampus = #{object.finCampus}");
        ls.add("flocation = #{object.flocation}");
        ls.add("fleaveTime = #{object.fleaveTime}");
        ls.add("fhealthId= #{object.fhealthId}");
        ls.add("fhealthMemo = #{object.fhealthMemo}");
        ls.add("fstatusID = #{object.fstatusID}");
        ls.add("memo = #{object.memo}");
        updateContent = ls;
    }
}
