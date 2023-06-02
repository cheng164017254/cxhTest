package com.ustb.evaluation.mod01common.utils.security;

import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextInfoUtil {
    public static SysUser currentUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        return (SysUser) auth.getPrincipal();
    }
}
