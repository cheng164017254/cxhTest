package com.ustb.evaluation.mod05test.domain.StuStudent;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StuStudentConstant extends TableConstant<StuStudent> {

    public StuStudentConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.fuserId fuserId,a.fcode fcode,a.fname fname,a.fgender fgender,a.fmobile fmobile,a.fcontact fcontact,a.faddress faddress,a.fidCode fidCode,a.fpoliticsId fpoliticsId,a.fclassRoleId fclassRoleId,a.fnationalityId fnationalityId,a.memo memo";
        selectFrom = " from stu_student a";
        selectFieldsSimple = selectFields;
        selectFromSimple = selectFrom;
        selectFieldsComplex = selectFields;
        selectFromComplex = selectFrom;
        tableName = "stu_student";//表单名
        hasSafe = false;
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("fuserId", "a.fuserId", "用户id", "", ""));
        ls.add(new QueryField("fcode", "a.fcode", "学生学号", "", ""));
        ls.add(new QueryField("fname", "a.fname", "学生姓名", "'", "'"));
        ls.add(new QueryField("fgender", "a.fgender", "性别", "'", "'"));
        ls.add(new QueryField("fmobile", "a.fmobile", "年龄", "", ""));
        ls.add(new QueryField("fcontact", "a.fcontact", "联系方式", "", ""));
        ls.add(new QueryField("faddress", "a.faddress", "家庭住址", "'", "'"));
        ls.add(new QueryField("fidCode", "a.fidCode", "年龄", "", ""));
        ls.add(new QueryField("fpoliticsId", "a.fpoliticsId", "政治面貌", "'", "'"));
        ls.add(new QueryField("fclassRoleId", "a.fclassRoleId", "班级干部", "'", "'"));
        ls.add(new QueryField("fnationalityId", "a.fnationalityId", "民族", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));

        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("fuserId");
        ls.add("fcode");
        ls.add("fname");
        ls.add("fgender");
        ls.add("fmobile");
        ls.add("fcontact");
        ls.add("faddress");
        ls.add("fidCode");
        ls.add("fpoliticsId");
        ls.add("fclassRoleId");
        ls.add("fnationalityId");
        ls.add("memo");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.fuserId}");
        ls.add("#{object.fcode}");
        ls.add("#{object.fname}");
        ls.add("#{object.fgender}");
        ls.add("#{object.fmobile}");
        ls.add("#{object.fcontact}");
        ls.add("#{object.faddress}");
        ls.add("#{object.fidCode}");
        ls.add("#{object.fpoliticsId}");
        ls.add("#{object.fclassRoleId}");
        ls.add("#{object.fnationalityId}");
        ls.add("#{object.memo}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("fuserId = #{object.fuserId}");
        ls.add("fcode = #{object.fcode}");
        ls.add("fname = #{object.fname}");
        ls.add("fgender = #{object.fgender}");
        ls.add("fmobile = #{object.fmobile}");
        ls.add("fcontact = #{object.fcontact}");
        ls.add("faddress = #{object.faddress}");
        ls.add("fidCode = #{object.fidCode}");
        ls.add("fpoliticsId = #{object.fpoliticsId}");
        ls.add("fclassRoleId = #{object.fclassRoleId}");
        ls.add("fnationalityId = #{object.fnationalityId}");
        ls.add("memo = #{object.memo}");
        updateContent = ls;
    }
}
