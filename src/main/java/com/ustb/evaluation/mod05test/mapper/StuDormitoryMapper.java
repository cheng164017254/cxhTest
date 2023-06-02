package com.ustb.evaluation.mod05test.mapper;

import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod05test.domain.StuDormitory.StuDormitory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengcheng
 * @date 2022/11/27 - 22:00
 */
@Mapper
public interface StuDormitoryMapper extends CurdMapper<StuDormitory> {
}
