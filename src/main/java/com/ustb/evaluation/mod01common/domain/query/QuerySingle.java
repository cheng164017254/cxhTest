package com.ustb.evaluation.mod01common.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuerySingle {
    private Boolean newCondition;//是否是新的条件
    private String conditionLogic;//条件之间的逻辑名称(and 或者 or)
    private String fieldOfJava;//查询的Java对象的名称（以此来对应具体的数据库字段名称）
    private String filedLogic;//字段和值之间的逻辑名称（>,<,等等)
    private String fieldValue;//查询的字段的值
}
