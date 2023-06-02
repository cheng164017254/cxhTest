package com.ustb.evaluation.mod01common.setting;

public enum GlobalSettings {
    LOGIN_ADDRESS("LoginFix","/login"),
    JWT_PREFIX("JWTPrefix", "Bearer "),
    TOKEN_KEEPLIVE("TokenKeepLive","100"),//JWT的存活时间（分钟计算）
    ;

    private final String key;

    private final String value;

    GlobalSettings(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return value;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return this.key + " " + this.value;
    }
}
