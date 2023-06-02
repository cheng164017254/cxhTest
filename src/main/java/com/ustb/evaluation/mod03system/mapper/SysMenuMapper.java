package com.ustb.evaluation.mod03system.mapper;

import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMenuMapper extends CurdMapper<SysMenu> {
}
