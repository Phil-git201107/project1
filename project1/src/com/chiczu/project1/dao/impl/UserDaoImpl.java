package com.chiczu.project1.dao.impl;


import com.chiczu.project1.dao.BaseDao;
import com.chiczu.project1.dao.UserDao;
import com.chiczu.project1.pojo.User;

/**
 * @User: Nick
 * @Date: 2020-10-21 16:41
 */
public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password from users where username = ? and password = ?";
        User user = queryForOne(User.class, sql, username,password);
        return user;
    }

}
