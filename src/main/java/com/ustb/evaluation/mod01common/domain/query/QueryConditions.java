package com.ustb.evaluation.mod01common.domain.query;

import lombok.Data;

import java.util.List;

//这个主要用于外部访问信息时设置查询条件
@Data
public class QueryConditions {
    /*查询条件（多条记录）*/
    private List<QuerySingle> listCondition;

    /*排序字段*/
    private List<QueryOrder> listOrder;


}
