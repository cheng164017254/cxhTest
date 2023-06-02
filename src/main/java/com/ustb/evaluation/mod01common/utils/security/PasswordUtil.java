package com.ustb.evaluation.mod01common.utils.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码工具类
 *
 * @author Louis
 * @date Sep 1, 2018
 */
public class PasswordUtil {
    private static final Integer len=10;

    public static String encodePassword(String password){
        return new BCryptPasswordEncoder(len).encode(password);
    }

    public static Boolean matches(String password, String encodedPassword) {
        return new BCryptPasswordEncoder(len).matches(password, encodedPassword);
    }

}
