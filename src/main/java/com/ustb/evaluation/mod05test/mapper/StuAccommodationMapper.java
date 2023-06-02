package com.ustb.evaluation.mod05test.mapper;

import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod05test.domain.StuAccommodation.StuAccommodation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengcheng
 * @date 2022/11/27 - 21:27
 */
@Mapper
public interface StuAccommodationMapper extends CurdMapper<StuAccommodation> {
}
