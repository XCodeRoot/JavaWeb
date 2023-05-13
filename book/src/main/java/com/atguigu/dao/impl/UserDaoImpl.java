package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

// UserDaoImpl 继承 BaseDao 并实现 UserDao接口
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    //传入占位符的值 username
    public User queryUserByUsername(String username) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    //传入占位符的值 username和password 两个参数
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);

    }

    @Override
    // 传入user对象,我们通过 user类的公共 get and set()方法 ,获取到 用户的name,password,email
    // 然后通过update()方法,将用户的属性 传到数据库

    public int saveUser(User user) {
        String sql= " insert into t_user(`username`,`password`,`email`)VALUES(?,?,?);";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());//Insert语句所以用update()方法
    }
}
