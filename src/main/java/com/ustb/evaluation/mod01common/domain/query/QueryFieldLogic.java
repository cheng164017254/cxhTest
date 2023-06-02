

package com.ustb.evaluation.mod01common.domain.query;


/**
 * 与前端进行特殊交互需要使用的状态码
 * 注意：如果状态码以4开始，则表示需要用户进行重新登录，这个要给客户端说清楚
 */
public enum QueryFieldLogic {
    LMATCH("LM", "左匹配", " like ", "CONCAT(", ", '%')", "字符串特有的左匹配"),
    RMATCH("RM", "右匹配", " like ", "CONCAT('%', ", ")", "字符串特有的右匹配"),
    MATCH("LORM", "匹配", " like ", "CONCAT('%', " ,", '%')", "字符串特有的匹配"),
    GREATER(">", "大于", " > ", "", "", "大于"),
    GREATEREQUAL(">=", "大于等于", " >= ", "", "", "大于等于"),
    EQUAL("=", "等于", " = ", "", "", "等于"),
    NOTEQUAL("<>", "不等于", " <> ", "", "", "不等于"),
    LESSEQUAL("<=", "小于等于", " <= ", "", "", "小于等于"),
    LESS("<", "小于", " < ", "", "", "小于"),
    ;

    private String logicName;//逻辑名称
    private String logicCHN;//中文逻辑名称
    private String logic;//逻辑的真的值，比如>,<=,like等等
    private String prefixValue;//比如前面的%
    private String suffixValue;//比如后面的的%
    private String description;//逻辑的信息描述，详细描述

    QueryFieldLogic(String logicName, String logicCHN, String logic,
                    String prefixValue, String suffixValue, String description) {
        this.logicName = logicName;
        this.logicCHN = logicCHN;
        this.logic = logic;
        this.prefixValue = prefixValue;
        this.suffixValue = suffixValue;
        this.description = description;
    }


    public String getLogicName() {
        return logicName;
    }

    public String getLogicCHN() {
        return logicCHN;
    }

    public String getLogic() {
        return logic;
    }

    public String getPrefixValue() {
        return prefixValue;
    }

    public String getSuffixValue() {
        return suffixValue;
    }

    public String getDescription() {
        return description;
    }
}
