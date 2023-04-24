package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {//接口 列出 注册和登录所需要的功能

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 根据 用户名 和 密码 查询用户信息
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);


    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);


}
