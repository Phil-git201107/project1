package com.chiczu.project1.test;

import java.util.HashMap;

import com.chiczu.project1.dao.UserDao;
import com.chiczu.project1.dao.impl.UserDaoImpl;
import com.chiczu.project1.pojo.User;
import org.junit.Test;



public class UserDaoTest {


	@Test
    public void queryUserByUsernameAndPassword() {
		UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsernameAndPassword("錢二", "a123456");
        System.out.println(user);
    }
	
}

