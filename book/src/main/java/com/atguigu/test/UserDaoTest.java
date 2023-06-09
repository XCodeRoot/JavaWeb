package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

public class UserDaoTest {

    // 接口的引用 = 实现了该接口 的类的对象
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {

        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {

        if(  userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("用户名或密码错误,登陆失败");
        }else {
            System.out.println("查询成功");
        }

    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"wzg168","123456","wzg168@qq.com")));
    }
}