package com.ustb.evaluation.mod01common.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
@AllArgsConstructor
public class QueryOrder {
    private String fieldOfJava;
    private Boolean ascend;
}
