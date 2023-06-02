

package com.ustb.evaluation.mod01common.domain.query;


/**
 * 与前端进行特殊交互需要使用的状态码
 * 注意：如果状态码以4开始，则表示需要用户进行重新登录，这个要给客户端说清楚
 */
public enum NewCondition {
    NewConditionIsTrue(")", "("),
    NewConditionIsFalse("", ""),
    ;

    private final String lastConditionSuffix;//上一个条件的尾部填充
    private final String currentConditionSuffix;//当前条件的头部填充


    NewCondition(String lastConditionSuffix, String currentConditionSuffix) {
        this.lastConditionSuffix = lastConditionSuffix;
        this.currentConditionSuffix = currentConditionSuffix;
    }

    /**
     * Return the integer value of this status code.
     */
    public String getCurrentConditionSuffix() {
        return currentConditionSuffix;
    }

    /**
     * Return the msg of this status code.
     */
    public String getLastConditionSuffix() {
        return lastConditionSuffix;
    }

}
