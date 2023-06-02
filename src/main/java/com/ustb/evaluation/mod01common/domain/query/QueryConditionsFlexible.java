package com.ustb.evaluation.mod01common.domain.query;

import lombok.Data;

import java.util.List;

//扩展了QueryConditions，使得内部可以直接执行sql语句（内部执行，一般不会形成sql注入攻击)

@Data
public class QueryConditionsFlexible extends QueryConditions {

    //2021年9月21日新增--处理直接不需要解析的sql条件

    private Boolean  direct=false;//是表示查询条件直接给出，否则需要解析listCondition

    private String sqlCondition;//内部使用或者在确保不会sql注入攻击的情况下使用

    private String sqlCondtionCHN;//查询条件的中文

}
