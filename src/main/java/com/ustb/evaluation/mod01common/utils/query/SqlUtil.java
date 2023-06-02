

package com.ustb.evaluation.mod01common.utils.query;


/*
 * sql语句之间的逻辑条件*/

import com.ustb.evaluation.mod01common.domain.page.PageRequest;
import com.ustb.evaluation.mod01common.domain.page.PageRequestFlexible;
import com.ustb.evaluation.mod01common.domain.query.*;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.domain.query.ParameterizedSQL;

import java.util.ArrayList;
import java.util.List;


public class SqlUtil {


    /*输入true或者false返回对应的新条件枚举信息*/
    private static final NewCondition getNewCondition(Boolean newCondition) {
        if (newCondition) {
            return NewCondition.NewConditionIsTrue;
        } else {
            return NewCondition.NewConditionIsFalse;
        }
    }

    /*输入条件之间的逻辑名称AND或OR，返回对应的逻辑条件枚举信息*/
    private static final ConditionLogic getConditionLogic(String conditionLogicName) {
        String ln = conditionLogicName.trim().toUpperCase();
        if (ln.equals("AND")) {
            return ConditionLogic.AND;
        } else if (ln.equals("OR")) {
            return ConditionLogic.OR;
        }
        throw new PromptException("sql语句之间的逻辑条件只能是AND或者OR！");
    }

    /*输入字段逻辑名称，返回对应的字段逻辑枚举信息*/
    private static final QueryFieldLogic getQueryFieldLogic(String logicName) {
        String ln = logicName.trim().toUpperCase();
        switch (ln) {
            case "LM":
                return QueryFieldLogic.LMATCH;
            case "RM":
                return QueryFieldLogic.RMATCH;
            case "LORM":
                return QueryFieldLogic.MATCH;
            case ">":
                return QueryFieldLogic.GREATER;
            case ">=":
                return QueryFieldLogic.GREATEREQUAL;
            case "=":
                return QueryFieldLogic.EQUAL;
            case "<>":
                return QueryFieldLogic.NOTEQUAL;
            case "<=":
                return QueryFieldLogic.LESSEQUAL;
            case "<":
                return QueryFieldLogic.LESS;
        }
        throw new PromptException("sql语句的逻辑条件错误，只能是LM,RM,LORM,>,>=,=,<=,<中的一个！");
    }

    /*输入java对象的字段和表的所有查询字段集合，找到相关的查询字段信息*/
    private static final QueryField getFieldOfTable(String fieldOfJava, List<QueryField> queryField) {

        for (QueryField qf : queryField) {
            if (qf.getFieldOfJava().trim().equals(fieldOfJava.trim())) {
                return qf;
            }
        }

        throw new PromptException("查询的字段" + fieldOfJava + "非法，不能查询！");
    }

    //外部传入进来的查询排序
    private static final void translateQueryOrder(StringBuilder sbOrder, StringBuilder sbOrderCHN,
                                                  List<QueryOrder> listOrder, List<QueryField> conditionFieldList) {
        if (listOrder == null || listOrder.size() <= 0) {
            return;
        }
        sbOrder.append(" order by ");
        sbOrderCHN.append(" 排序：");
        QueryField field;

        QueryOrder order = listOrder.get(0);
        field = getFieldOfTable(order.getFieldOfJava(), conditionFieldList);
        if (order.getAscend()) {
            sbOrder.append(field.getFieldOfTable() + " asc");
            sbOrderCHN.append(field.getFieldNameCHN() + " 升序");
        } else {
            sbOrder.append(field.getFieldOfTable() + " desc");
            sbOrderCHN.append(field.getFieldNameCHN() + " 降序");
        }

        for (int i = 1; i < listOrder.size(); i++) {
            order = listOrder.get(i);
            field = getFieldOfTable(order.getFieldOfJava(), conditionFieldList);
            sbOrder.append(" ,");
            if (order.getAscend()) {
                sbOrder.append(field.getFieldOfTable() + " asc");
                sbOrderCHN.append(field.getFieldNameCHN() + " 升序");
            } else {
                sbOrder.append(field.getFieldOfTable() + " desc");
                sbOrderCHN.append(field.getFieldNameCHN() + " 降序");
            }
        }
    }

    private static final void translateQueryCondition(StringBuilder sb1, StringBuilder sb2, QueryField field,
                                                      Integer index, List<String> listValue,
                                                      ConditionLogic betweenLogicStatus,
                                                      QueryFieldLogic fieldLogicStatus,
                                                      NewCondition newStatus, QuerySingle querySingle,
                                                      Boolean compile) {
        sb1.append(newStatus.getLastConditionSuffix());//上一个条件的末尾符号
        sb1.append(betweenLogicStatus.getValue());//语句间逻辑的SQL表达
        sb1.append(newStatus.getCurrentConditionSuffix());//当前条件的开始符号，比如（
        sb1.append(field.getFieldOfTable());//数据库的字段
        sb1.append(fieldLogicStatus.getLogic());//字段数据间逻辑的SQL表达
        sb1.append(fieldLogicStatus.getPrefixValue());//字段数据间逻辑的前缀，比如加concat('%'等
        if (compile) {
            sb1.append("#{list[");//接下来三行是添加编译sql的?代表的数据绑定表达式
            sb1.append(index.toString());
            sb1.append("]}");
            listValue.add(querySingle.getFieldValue());
        } else {
            sb1.append(field.getValuePrifix());//数据的前缀，比如字符串的话，需要加'符号
            sb1.append(querySingle.getFieldValue());//字段对应的数据的值
            sb1.append(field.getValueSuffix());//数据的后缀，比如字符串的话，需要加'符号
        }
        sb1.append(fieldLogicStatus.getSuffixValue());//字段数据间逻辑的后缀，比如加'%')等

        sb2.append(newStatus.getLastConditionSuffix());//上一个条件的末尾符号
        sb2.append(betweenLogicStatus.getLogicName());//语句间逻辑的中文
        sb2.append(newStatus.getCurrentConditionSuffix());//当前条件的开始符号，比如（
        sb2.append(field.getFieldNameCHN());//字段中文名称
        sb2.append(fieldLogicStatus.getLogicCHN());//字段数据间逻辑的中文表达
        sb2.append(field.getValuePrifix());//数据的前缀，比如字符串的话，需要加'符号
        sb2.append(querySingle.getFieldValue());//字段对应的数据的值
        sb2.append(field.getValueSuffix());//数据的后缀，比如字符串的话，需要加'符号
    }

    private static final void translateQueryConditionFirst(StringBuilder sb1, StringBuilder sb2, QueryField field,
                                                           Integer index, List<String> listValue,
                                                           ConditionLogic betweenLogicStatus,
                                                           QueryFieldLogic fieldLogicStatus,
                                                           NewCondition newStatus, QuerySingle querySingle,
                                                           Boolean compile) {
        sb1.append(newStatus.getCurrentConditionSuffix());//当前条件的开始符号，比如（
        sb1.append(field.getFieldOfTable());//数据库的字段
        sb1.append(fieldLogicStatus.getLogic());//字段数据间逻辑的SQL表达
        sb1.append(fieldLogicStatus.getPrefixValue());//字段数据间逻辑的前缀，比如加concat('%'等
        if (compile) {
            sb1.append("#{list[");//接下来三行是添加编译sql的?代表的数据绑定表达式
            sb1.append(index.toString());
            sb1.append("]}");
            listValue.add(querySingle.getFieldValue());
        } else {
            sb1.append(field.getValuePrifix());//数据的前缀，比如字符串的话，需要加'符号
            sb1.append(querySingle.getFieldValue());//字段对应的数据的值
            sb1.append(field.getValueSuffix());//数据的后缀，比如字符串的话，需要加'符号
        }
        sb1.append(fieldLogicStatus.getSuffixValue());//字段数据间逻辑的后缀，比如加'%')等

        sb2.append(newStatus.getCurrentConditionSuffix());//当前条件的开始符号，比如（
        sb2.append(field.getFieldNameCHN());//字段中文名称
        sb2.append(fieldLogicStatus.getLogicCHN());//字段数据间逻辑的中文表达
        sb2.append(field.getValuePrifix());//数据的前缀，比如字符串的话，需要加'符号
        sb2.append(querySingle.getFieldValue());//字段对应的数据的值
        sb2.append(field.getValueSuffix());//数据的后缀，比如字符串的话，需要加'符号
    }


    /*
     * 给出直接拼接的SQL查询的结果
     * 复杂条件查询*/
    private static final ParameterizedSQL translateSelectCondition(QueryConditionsFlexible queryConditionsFlexible,
                                                                   List<QueryField> conditionFieldList, Boolean compile) {
        if (conditionFieldList.size() <= 0) {
            throw new PromptException("未建立查询字段信息，不能查询！");
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append(" ");
        sb2.append(" ");
        List<String> listValue = new ArrayList<>();
        if (queryConditionsFlexible.getDirect()) {
            sb1.append(queryConditionsFlexible.getSqlCondition());
            sb2.append(queryConditionsFlexible.getSqlCondtionCHN());
        } else {
            List<QuerySingle> conditionList = queryConditionsFlexible.getListCondition();
            if ((conditionList != null) && (conditionList.size() > 0)) {
                sb1.append("Where ");
                sb2.append("查询条件：");

                QueryField field;
                ConditionLogic betweenLogisStatus;
                QueryFieldLogic fieldLogicStatus;
                NewCondition newStatus;
                QuerySingle condition;

                condition = conditionList.get(0);
                field = getFieldOfTable(condition.getFieldOfJava(), conditionFieldList);
                betweenLogisStatus = ConditionLogic.AND;//and表达
                fieldLogicStatus = getQueryFieldLogic(condition.getFiledLogic());
                newStatus = getNewCondition(true);//全新的条件
                translateQueryConditionFirst(sb1, sb2, field, 0, listValue, betweenLogisStatus, fieldLogicStatus, newStatus, condition, compile);

                for (int i = 1; i < conditionList.size(); i++) {
                    condition = conditionList.get(i);
                    field = getFieldOfTable(condition.getFieldOfJava(), conditionFieldList);
                    betweenLogisStatus = getConditionLogic(condition.getConditionLogic());
                    fieldLogicStatus = getQueryFieldLogic(condition.getFiledLogic());
                    newStatus = getNewCondition(condition.getNewCondition());
                    translateQueryCondition(sb1, sb2, field, i, listValue, betweenLogisStatus, fieldLogicStatus, newStatus, condition, compile);
                }
                sb1.append(")");
                sb2.append(")");
            }
        }


        //加上排序的内容
        List<QueryOrder> orderList = queryConditionsFlexible.getListOrder();
        translateQueryOrder(sb1, sb2, orderList, conditionFieldList);
        return new ParameterizedSQL(sb1.toString(), sb2.toString(), listValue);
    }


    /*返回正常对象的参数化sql(包括sql语句和参数值list)*/
    public static final ParameterizedSQL getParameterizedSQLTable(QueryConditionsFlexible queryConditionsFlexible,
                                                                  TableConstant constant, Boolean compile) {
        //2021年11月21日的相关测试


        ParameterizedSQL parameterizedSQL;
        //1.将查询条件生成动态sql和编译形式的参数
        parameterizedSQL = SqlUtil.translateSelectCondition(queryConditionsFlexible, constant.getList(), true);


        //2.加上查询的字段列表
        parameterizedSQL.setSql(constant.getSelectStatent() + parameterizedSQL.getSql());


        return parameterizedSQL;
    }

    public static final ParameterizedSQL getParameterizedSQLTable(PageRequestFlexible pageRequestFlexible,
                                                                  TableConstant constant, Boolean compile) {
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        queryConditionsFlexible.setDirect(pageRequestFlexible.getDirect());
        queryConditionsFlexible.setListCondition(pageRequestFlexible.getListCondition());
        queryConditionsFlexible.setListOrder(pageRequestFlexible.getListOrder());
        queryConditionsFlexible.setSqlCondition(pageRequestFlexible.getSqlCondition());
        queryConditionsFlexible.setSqlCondtionCHN(pageRequestFlexible.getSqlCondtionCHN());

        return getParameterizedSQLTable(queryConditionsFlexible, constant, compile);
    }

    public static final ParameterizedSQL getParameterizedSQLIsExisted(QueryConditionsFlexible queryConditionsFlexible,
                                                                      TableConstant constant, Boolean compile) {

        //1.将查询条件生成动态sql和编译形式的参数
        ParameterizedSQL parameterizedSQL = SqlUtil.translateSelectCondition(queryConditionsFlexible, constant.getList(), true);
        //2.加上查询的字段列表
        parameterizedSQL.setSql(constant.getSelectStatent() + parameterizedSQL.getSql() + " limit 1");
        return parameterizedSQL;
    }

    public static final List<QuerySingle> getlistQueryConditionSingle(String fieldOfJava, String fieldValue,
                                                                      String logicName) {
        QuerySingle qcs = new QuerySingle(true,
                "and", fieldOfJava, logicName, fieldValue);
        List<QuerySingle> list = new ArrayList<>();
        list.add(qcs);
        return list;
    }
}


