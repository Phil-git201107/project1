package com.chiczu.project1.service.impl;

import com.chiczu.project1.dao.BaseDao;
import com.chiczu.project1.dao.UserDao;
import com.chiczu.project1.dao.impl.UserDaoImpl;
import com.chiczu.project1.pojo.User;
import com.chiczu.project1.service.UserService;

/**
 * @User: Nick
 * @Date: 2020-11-08 15:54
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
