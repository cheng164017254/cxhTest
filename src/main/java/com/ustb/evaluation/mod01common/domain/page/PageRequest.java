package com.ustb.evaluation.mod01common.domain.page;

import com.ustb.evaluation.mod01common.domain.query.QueryConditions;
import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest extends QueryConditions {
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 每页数量
     */
    private int pageSize = 20;


}
