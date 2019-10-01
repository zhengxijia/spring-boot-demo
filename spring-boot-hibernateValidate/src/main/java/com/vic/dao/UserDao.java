package com.vic.dao;

import com.vic.bean.User;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    public String register(User user){
        System.out.println(user);
        return "注册成功";
    }
}
