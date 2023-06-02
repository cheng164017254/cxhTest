package com.ustb.evaluation.mod01common.mapper;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import com.ustb.evaluation.mod01common.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/*1.前面四个是参数化SQL的数据库基本功能CURD的功能，可放心由各种客户端调用
* 2.后面四个是利用拼接形成的SQL语句，因此只传递一个sql语句进入即可，一般这样的功能不应该由客户端调用，而是由内部功能调用
* 3.即时2不由外部调用，都要慎用，必须保证拼接的sql语句没有注入的风险
* 4.给出一个BaseProvider返回当前函数的参数里的sql，这是Provider写法需要的，这个写法把参数sql变成了需要mapper执行的语句*/
@Component
public interface CurdMapper<T extends  FieldsBasic> {
    @InsertProvider(type = BaseProvider.class, method = "paramToSql")
    Integer insert(@Param("sql") String sql, @Param("object") T object, @Param("operator") String operator);

    @UpdateProvider(type = BaseProvider.class, method = "paramToSql")
    Integer update(@Param("sql") String sql, @Param("object") T object, @Param("operator") String operator);

    @DeleteProvider(type = BaseProvider.class, method = "paramToSql")
    Integer deleteById(@Param("sql") String sql, @Param("list") List<String> list);

    @SelectProvider(type = BaseProvider.class, method = "paramToSql")
    List<T> select(@Param("sql") String sql, @Param("list") List<String> list);

    @InsertProvider(type=BaseProvider.class,method = "paramToSql")
    Integer insertBySQL(@Param("sql") String sql);

    @UpdateProvider(type=BaseProvider.class,method = "paramToSql")
    Integer updateBySQL(@Param("sql") String sql);

    @DeleteProvider(type=BaseProvider.class,method = "paramToSql")
    Integer deleteBySQL(@Param("sql") String sql);

    @SelectProvider(type=BaseProvider.class,method = "paramToSql")
    List<T> selectBySQL(@Param("sql") String sql);
}
