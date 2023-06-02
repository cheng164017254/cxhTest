package com.ustb.evaluation.mod05test.domain.DicPolitics;

import com.ustb.evaluation.mod01common.domain.query.QueryField;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @Description
 * @create 2022-11-27-17:51
 */
@Component
public class DicPoliticsConstant extends TableConstant<DicPolitics> {

    public DicPoliticsConstant() {
        super();
    }

    @Override
    public void initFirtSevenData() {
        selectFields = "select a.id id,a.fcode fcode,a.fname fname,a.fsortNumber,a.memo memo";
        selectFrom = " from dic_politics a";
        selectFieldsSimple = selectFields;
        selectFromSimple = selectFrom;
        selectFieldsComplex = selectFields;
        selectFromComplex = selectFrom;
        tableName = "dic_politics";//表单名
        hasSafe = false;
    }

    @Override
    public void initQueryFields() {
        List<QueryField> ls = safeQueryFields;
        ls.add(new QueryField("id", "a.id", "id", "", ""));
        ls.add(new QueryField("fcode", "a.deptId", "政治面貌编号", "", ""));
        ls.add(new QueryField("fname", "a.fname", "政治面貌名称", "'", "'"));
        ls.add(new QueryField("fsortNumber", "a.fsortNumber", "排序序号", "'", "'"));
        ls.add(new QueryField("memo", "a.memo", "备注", "'", "'"));
        queryFields = ls;
    }

    @Override
    public void initInsertColumns() {
        List<String> ls = new ArrayList<>();
        ls.add("fcode");
        ls.add("fname");
        ls.add("fsortNumber");
        ls.add("memo");
        insertColumns = ls;
    }

    @Override
    public void initInsertValues() {
        List<String> ls = new ArrayList<>();
        ls.add("#{object.fcode}");
        ls.add("#{object.fname}");
        ls.add("#{object.fsortNumber}");
        ls.add("#{object.memo}");
        insertValues = ls;
    }

    @Override
    public void initUpdateContent() {
        List<String> ls = new ArrayList<>();
        ls.add("fcode = #{object.fcode}");
        ls.add("fname = #{object.fname}");
        ls.add("fsortNumber = #{object.fsortNumber}");
        ls.add("memo = #{object.memo}");
        updateContent = ls;
    }
}
