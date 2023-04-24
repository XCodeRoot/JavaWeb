package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoITest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsername("admin123")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(UserDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("用户名或密码错误,登陆失败");
        }else {
            System.out.println("查询成功");
        }

    }

    @Test
    public void saveUser() {
    }
}