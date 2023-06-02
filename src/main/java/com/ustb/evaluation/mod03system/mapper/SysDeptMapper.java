package com.ustb.evaluation.mod03system.mapper;

import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod03system.domain.SysDept.SysDept;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDeptMapper extends CurdMapper<SysDept>  {

}
