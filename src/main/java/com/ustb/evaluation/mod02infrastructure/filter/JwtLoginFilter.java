package com.ustb.evaluation.mod02infrastructure.filter;

import com.ustb.evaluation.mod01common.setting.GlobalSettings;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod02infrastructure.prop.RsaKeyProperties;
import com.ustb.evaluation.mod01common.utils.security.JwtUtil;
import com.ustb.evaluation.mod01common.utils.http.RequestUtil;
import com.ustb.evaluation.mod01common.utils.http.ResponseUtil;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private RsaKeyProperties prop;
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        //拦截url为 "/login" 的POST请求
        // super(new AntPathRequestMatcher("/login", "POST"));
        // super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        // System.out.println(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.authenticationManager = authenticationManager;
        this.prop = prop;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        SysUser sysUser = RequestUtil.read(request, SysUser.class);
        String username = "";//sysUser.getUsername();
        username = username != null ? username : "";
        String password = sysUser.getPassword();
        password = password != null ? password : "";
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authRequest);
    }

    /**
     * 认证成功所执行的方法
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SysUser sysUser = new SysUser();
//        sysUser.setUsername(authResult.getName());
//        sysUser.setSysRoles(new ArrayList(authResult.getAuthorities()));
        String token = JwtUtil.generateTokenExpireInMinutes(sysUser, prop.getPrivateKey());
        ResponseUtil.write(response, ResponseStatus.SUCCESS, "用户认证通过！", GlobalSettings.JWT_PREFIX.getValue() + token);
    }

    /**
     * 认证失败所执行的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //清理上下文
        SecurityContextHolder.clearContext();
        //判断异常类
        if (failed instanceof InternalAuthenticationServiceException) {
            throw new PromptException("认证服务不正常！");
        } else if (failed instanceof UsernameNotFoundException) {
            throw new PromptException("用户账号错误！");
        } else if (failed instanceof BadCredentialsException) {
            throw new PromptException("用户密码错误！");
        } else if (failed instanceof AccountExpiredException) {
            throw new PromptException("用户账户已过期！");
        } else if (failed instanceof LockedException) {
            throw new PromptException("用户账户已被锁！");
        } else if (failed instanceof CredentialsExpiredException) {
            throw new PromptException("用户密码已失效！");
        } else if (failed instanceof DisabledException) {
            throw new PromptException("用户账户已被锁！");
        }
    }
}