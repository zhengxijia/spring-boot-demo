package com.vic.controller;

import com.vic.config.JwtProperties;
import com.vic.service.AuthService;
import com.vic.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class JJWTController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties prop;

    /**
     * 登录授权/加密过程
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response){
        // 登录校验
        String token = this.authService.login(username, password);
        if (StringUtils.isBlank(token)) {
            System.out.println("登录失败，请重新登录");
            return "登录失败，请重新登录";
        }
        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, prop.getCookieName(),
                token, prop.getExpire(), null, true);
        return token;
    }
}
