package com.ustb.evaluation.mod01common.service.utils;

import com.ustb.evaluation.mod01common.domain.query.*;
import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod01common.utils.query.SqlUtil;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtil {
    /*通过id返回对象*/
    public static final Object getObjectById(String fieldValue, TableConstant constant, Boolean compile, CurdMapper mapper) {
        List<Object> ls = getListByField("id", fieldValue, "=", constant, null, compile, mapper);
        if (ls.size() <= 0) return null;
        else return ls.get(0);
    }

    /*通过一个唯一性的字段返回单个对象*/
    public static final Object getObjectByUniqueField(String fieldOfJava, String fieldValue, String logicName,
                                                      TableConstant constant, List<QueryOrder> orderList,
                                                      Boolean compile, CurdMapper mapper) {

        List<Object> ls = getListByField(fieldOfJava, fieldValue, logicName, constant, orderList, compile, mapper);
        if (ls.size() <= 0) return null;
        else return ls.get(0);
    }

    //私有函数，针对一个字段查询，返回对象列表数据，主要用于根据id或者unique字段返回唯一的对象
    private static final List<Object> getListByField(String fieldOfJava, String fieldValue, String logicName, TableConstant constant, List<QueryOrder> queryOrderList, Boolean compile, CurdMapper mapper) {
        //1.首先设置查询条件
        List<QuerySingle> list = SqlUtil.getlistQueryConditionSingle(fieldOfJava, fieldValue, logicName);
        QueryConditionsFlexible queryConditionsFlexible =new QueryConditionsFlexible();
        queryConditionsFlexible.setListCondition(list);
        queryConditionsFlexible.setListOrder(queryOrderList);
        queryConditionsFlexible.setDirect(false);


        //2.将查询条件生成动态sql和编译形式的参数
        ParameterizedSQL parameterizedSQL = SqlUtil.getParameterizedSQLTable(queryConditionsFlexible, constant,  compile);

        //3.调用mapper功能
        List<Object> ls = (List<Object>) mapper.select(parameterizedSQL.getSql(), parameterizedSQL.getListValue());
        return ls;
    }

    public static final List<Object> getListByFields(QueryConditionsFlexible queryConditionsFlexible, TableConstant constant, Boolean compile, CurdMapper mapper) {
        //1.将查询条件生成动态sql和编译形式的参数
        ParameterizedSQL parameterizedSQL = SqlUtil.getParameterizedSQLTable(queryConditionsFlexible, constant,  compile);

        //2.调用mapper功能
        List<Object> ls = (List<Object>) mapper.select(parameterizedSQL.getSql(), parameterizedSQL.getListValue());
        return ls;
    }

    //根据条件，返回是否存在相关数据
    public static final Boolean isExisted(QueryConditionsFlexible queryConditionsFlexible, TableConstant constant, Boolean compile, CurdMapper mapper) {
        //1.将查询条件生成动态sql和编译形式的参数
        ParameterizedSQL parameterizedSQL = SqlUtil.getParameterizedSQLIsExisted(queryConditionsFlexible, constant,  compile);

        //2.调用mapper功能
        List<Object> ls = (List<Object>) mapper.select(parameterizedSQL.getSql(), parameterizedSQL.getListValue());

        if (ls.size() > 0) return true;
        else return false;
    }

    //根据id删除数据（写法比较固定）
    public static Integer deleteObjectById(String fieldValue, TableConstant constant, CurdMapper mapper) {
        //1.首先生成条件和数据列表
        String sql = "delete from " + constant.getTableName() + " where id=#{list[0]}";
        List<String> listValue = new ArrayList<>();
        listValue.add(fieldValue);

        //2.调用mapper返回结果
        return mapper.deleteById(sql, listValue);
    }

}
