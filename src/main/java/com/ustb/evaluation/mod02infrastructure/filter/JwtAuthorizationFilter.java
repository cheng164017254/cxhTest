package com.ustb.evaluation.mod02infrastructure.filter;

import com.ustb.evaluation.mod01common.setting.GlobalSettings;
import com.ustb.evaluation.mod01common.domain.http.Payload;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod02infrastructure.domain.UserForToken;
import com.ustb.evaluation.mod02infrastructure.service.UserAuthority;
import com.ustb.evaluation.mod02infrastructure.prop.RsaKeyProperties;
import com.ustb.evaluation.mod01common.utils.security.JwtUtil;
import com.ustb.evaluation.mod01common.utils.http.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 验证过滤器
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private RsaKeyProperties prop;

    @Autowired
    UserAuthority userAuthority;


    //日志记录
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        super(authenticationManager);
        this.prop = prop;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null) {
            log.info("没有请求头的Authorization，有些页面不需要权限控制！");
            //没有信息，则表示未登陆，有些页面可能不需要登录，所以放过，可以继续执行
            chain.doFilter(request, response);
            return;
        }
        if (!header.startsWith(GlobalSettings.JWT_PREFIX.getValue())) {
            //如果token的格式错误，则提示用户非法登录
            log.info("header没有用户token信息!");
            ResponseUtil.write(response, ResponseStatus.ILLEGALUSER, "header没有用户token信息!", null);
            return;
        }

        Payload<UserForToken> payload;//有用的JWT信息；
        try {
            //如果token的格式正确，则先要获取到token
            String token = header.replace(GlobalSettings.JWT_PREFIX.getValue(), "");
            //使用公钥进行解密然后来验证token是否正确
            payload = JwtUtil.getInfoFromToken(token, prop.getPublicKey(), UserForToken.class);
        } catch (ExpiredJwtException e) {
            log.info("登录信息已过期，请重新登录！");
            ResponseUtil.write(response, ResponseStatus.ILLEGALUSER, "登录信息已过期，请重新登录！", null);
            return;
        } catch (Exception e) {
            log.info("token用户信息格式错误!");
            ResponseUtil.write(response, ResponseStatus.ILLEGALUSER, "token用户信息格式错误!", null);
            return;
        }


        try {
            UserForToken userForToken = payload.getUserInfo();
            if (userForToken != null) {
                List<SimpleGrantedAuthority> authorities = userAuthority.getAuthorities(userForToken.getUsername());
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userForToken.getUsername(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            } else {
                log.info("无法解析用户信息，请重新登录！");
                ResponseUtil.write(response, ResponseStatus.ILLEGALUSER, "无法解析用户信息，请重新登录！", null);
                return;
            }
        } catch (Exception e) {
            log.info("非法用户信息，请重新登录！");
            ResponseUtil.write(response, ResponseStatus.ILLEGALUSER, "非法用户信息，请重新登录！", null);
            return;
        }

    }
}