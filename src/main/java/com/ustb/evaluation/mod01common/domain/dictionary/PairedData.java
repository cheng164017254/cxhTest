package com.ustb.evaluation.mod01common.domain.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 每个表都有的追溯用字段
 */
@Data
@AllArgsConstructor
public class PairedData {
    private String data;//真实的数据
    private String show;//显示的数据
}
