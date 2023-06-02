package com.ustb.evaluation.mod02infrastructure.domain;

import lombok.Data;

@Data
public class LoginInfo {
    private String username;
    private String password;
    private String captcha;
}
