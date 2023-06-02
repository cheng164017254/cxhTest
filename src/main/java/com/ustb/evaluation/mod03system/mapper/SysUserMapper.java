package com.ustb.evaluation.mod03system.mapper;

import com.ustb.evaluation.mod01common.mapper.provider.BaseProvider;
import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysUserMapper extends CurdMapper<SysUser> {
    @SelectProvider(type = BaseProvider.class, method = "paramToSql")
    List<SysFunction> selectFunction(@Param("sql") String sql, @Param("list") List<String> list);
}
