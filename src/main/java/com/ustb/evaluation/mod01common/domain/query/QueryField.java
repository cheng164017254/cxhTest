package com.ustb.evaluation.mod01common.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryField {
    String fieldOfJava;  //java的字段名称
    String fieldOfTable;//表中的字段名称
    String fieldNameCHN;//中文表达
    String valuePrifix;//值前的数据，比如字符串，需要有"'"
    String valueSuffix;//值后的数据，比如字符串，需要有"'"


}
