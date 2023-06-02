package com.ustb.evaluation.mod01common.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParameterizedSQL {
    private String Sql;
    private String SqlCHN;
    private List<String> listValue;
}
