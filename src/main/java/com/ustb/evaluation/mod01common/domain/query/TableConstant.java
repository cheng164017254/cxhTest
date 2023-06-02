package com.ustb.evaluation.mod01common.domain.query;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//为什么这个类没有用到任何的T,但是还是要用泛型？
//因为：与适用该类的泛型类相匹配，明确给出要使用哪个TableConstant!

@Component
public abstract class TableConstant<T> {
    protected List<QueryOrder> defaultOrders = new ArrayList<>();//默认的排序字段，可以没有
    //以下三项确定安全字段的相关内容（很多表都没有此字段）【安全字段标准的设置就是4个字段，必须一模一样】
    protected List<QueryField> safeQueryFields;//为了安全起见，共有的字段（保存表的更改、删除的时间和用户）
    //用于安全的查询select语句部分（很多表都不存在此数据）
    protected String selectsSafe = ",a.createBy createBy,a.createTime createTime,a.lastUpdateBy lastUpdateBy,a.lastUpdateTime lastUpdateTime";
    protected Boolean hasSafe = false;//是否有安全字段,4个特殊的安全字段

    //分为简单对象、正常对象、复杂对象的查询select内容
    protected String selectFieldsSimple;//简单对象，没有这个对象可以不设置这个语句
    protected String selectFields;//正常对象（不包括安全字段之外的所有字段）
    protected String selectFieldsComplex;//复杂对象（包括所有字段），没有这个对象可以不设置这个语句
    protected String selectFrom;//select的from 语句，三个对象共用这一个from
    protected String selectFromSimple;//select的from 语句，三个对象共用这一个from
    protected String selectFromComplex;//select的from 语句，三个对象共用这一个from
    protected String tableName;//表单名字


    //要返回的表的常量部分数据
    protected List<QueryField> queryFields = null;//所有要查询的字段列表
    protected String selectStatementSimple = "";//简单查询的select语句
    protected String selectStatement = "";//正常查询的select语句
    protected String selectStatementComplex = "";//复杂查询的select语句
    protected List<String> insertColumns = null;//表单的字段列表，用于插入用
    protected List<String> insertValues = null;//表单插入时对应的数据列表
    protected List<String> updateContent = null;//表单修改时对应的修改内容列表
    protected String updateById = "id = #{object.id}";//用id字段进行修改时的字符串

    private void initSafeQueryFields() {
        List<QueryField> ls = new ArrayList<>();
        if (hasSafe) {
            ls.add(new QueryField("createBy", "a.createBy", "创建人", "'", "'"));
            ls.add(new QueryField("createTime", "a.createTime", "创建时间", "'", "'"));
            ls.add(new QueryField("lastUpdateBy", "a.lastUpdateBy", "更新人", "'", "'"));
            ls.add(new QueryField("lastUpdateTime", "a.lastUpdateTime", "更新时间", "'", "'"));
        }
        this.safeQueryFields = ls;
    }

    public TableConstant() {
        initFirtSevenData();//7个基本参数的初始化，子类必须实现
        initSafeQueryFields();//当前类的函数，实现安全方面的查询字段
        initQueryFields();//初始化所有的查询字段,子类必须实现

        initInsertColumns();//增加字段列表，子类必须实现
        initInsertValues();//增加值列表，子类必须时间
        initUpdateContent();//修改内容列表，子类必须实现
        initSelectStatent();//查询语句生成
    }

    //确定4项基本的数据，每个表都不一样：selectsMain，selectFrom，tableName，hasSafe，defaultOrders
    public abstract void initFirtSevenData();

    public abstract void initQueryFields();

    public abstract void initInsertColumns();

    public abstract void initInsertValues();

    public abstract void initUpdateContent();

    private void initSelectStatent() {
        selectStatementSimple = selectFieldsSimple + selectFromSimple;
        selectStatement = selectFields + selectFrom;
        selectStatementComplex = selectFieldsComplex + selectFromComplex;
    }

    public List<QueryField> getList() {
        return queryFields;
    }

    public String getSelectStatementSimple() {
        return selectStatementSimple;
    }

    public String getSelectStatent() {
        return selectStatement;
    }

    public String getSelectStatentComplex() {
        return selectStatementComplex;
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getInsertColumns() {
        return insertColumns;
    }

    public List<String> getInsertValues() {
        return insertValues;
    }

    public List<String> getUpdateContent() {
        return updateContent;
    }

    public String getUpdateById() {
        return updateById;
    }

    public List<QueryOrder> getDefaultOrders() {
        return defaultOrders;
    }



}
