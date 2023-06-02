package com.ustb.evaluation.mod05test.mapper;

import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod05test.domain.StuStudent.StuStudent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chengcheng
 * @Description
 * @create 2022-11-27-15:38
 */
@Mapper
public interface StuStudentMapper extends CurdMapper<StuStudent> {
}
