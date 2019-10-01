package com.vic.service.impl;

import com.vic.bean.User;
import com.vic.dao.UserDao;
import com.vic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public String register(User user) {
        return userDao.register(user);
    }
}
