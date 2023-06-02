

package com.ustb.evaluation.mod01common.domain.query;


/**
 * 与前端进行特殊交互需要使用的状态码
 * 注意：如果状态码以4开始，则表示需要用户进行重新登录，这个要给客户端说清楚
 */
public enum ConditionLogic {
    AND("AND", " 并且 ", " and "),
    OR("OR", " 或者 ", " or "),
    ;

    private final String logicName;//逻辑名称
    private final String logicCHN;//逻辑名称中文
    private final String value;//在sql语句中的逻辑表达

    ConditionLogic(String logicName, String logicCHN, String value) {
        this.logicName = logicName;
        this.logicCHN = logicCHN;
        this.value = value;
    }


    public String getLogicName() {
        return logicName;
    }

    public String getLogicCHN() {
        return logicCHN;
    }

    public String getValue() {
        return value;
    }

}
