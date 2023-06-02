package com.ustb.evaluation.mod02infrastructure.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.utils.IoCloseUtil;
import com.ustb.evaluation.mod01common.utils.security.JwtUtil;
import com.ustb.evaluation.mod01common.utils.security.PasswordUtil;
import com.ustb.evaluation.mod02infrastructure.domain.LoginInfo;
import com.ustb.evaluation.mod02infrastructure.domain.UserForToken;
import com.ustb.evaluation.mod02infrastructure.prop.RsaKeyProperties;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import com.ustb.evaluation.mod03system.service.impl.SysUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api("登录控制器")
@RestController
public class LoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private SysUserServiceImpl sysUserService;
    @Autowired
    RsaKeyProperties prop;

    @ApiOperation("获取登录时的验证码，并将验证码代表的文字放入session中")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IoCloseUtil.closeQuietly(out);
    }


    /**
     * 登录接口
     */
    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody LoginInfo loginInfo, HttpServletRequest request) throws IOException {
        String username = loginInfo.getUsername();
        String password = loginInfo.getPassword();
        String captcha = loginInfo.getCaptcha();
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
      //此时的功能，不进行验证
//        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//		if(kaptcha == null){
//			throw new PromptException("验证码已失效");
//		}
//		if(!captcha.equals(kaptcha)){
//			throw new PromptException("验证码不正确");
//		}
        // 用户信息
        SysUser user = sysUserService.findByUniqueField("username",username);
        // 账号不存在、密码错误
        if (user == null) {
            return new ApiResult(ResponseStatus.WRONG, "账号不存在！", null);
        }
        if (!PasswordUtil.matches( password, user.getPassword())) {
            return new ApiResult(ResponseStatus.WRONG, "密码不正确！", null);
        }
        // 账号锁定
        if (user.getStatus() == 2) {
            return new ApiResult(ResponseStatus.WRONG, "账号已被禁用,请联系管理员！", null);
        }

        // 账号锁定
        if (user.getStatus() == 3) {
            return new ApiResult(ResponseStatus.WRONG, "账号已被锁定,请联系管理员！", null);
        }

        UserForToken userForToken=new UserForToken(username);

        String token = JwtUtil.generateTokenExpireInMinutes(userForToken, prop.getPrivateKey());

        return new ApiResult(ResponseStatus.SUCCESS, "用户认证通过！", token);
    }
}
