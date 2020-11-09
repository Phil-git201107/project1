package com.chiczu.project1.dao;

import com.chiczu.project1.pojo.User;

/**
 * @User: Nick
 * @Date: 2020-11-08 15:20
 */
public interface UserDao {

    public User queryUserByUsernameAndPassword(String username, String password);

}
