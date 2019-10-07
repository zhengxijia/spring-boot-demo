package com.vic.service.impl;

import com.vic.bean.UserInfo;
import com.vic.config.JwtProperties;
import com.vic.service.AuthService;
import com.vic.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtProperties prop;
    @Override
    public String login(String username, String password) {
        //假设用户名为vic密码为123才能登陆成功
        try {
            if (username.equals("vic")&&password.equals("123")){
                String token = JwtUtils.generateToken(new UserInfo(Long.valueOf(1), password), prop.getPrivateKey(), prop.getExpire());
                return token;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
        return null;
    }
}
