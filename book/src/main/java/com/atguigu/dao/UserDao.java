package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 *  这个接口 有哪些方法 ,是由我们登录界面 需要哪些功能来决定的
 *  接口只是规范,不需要写具体的方法
 */
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
     * @return 返回-1表示操作失败, 其他为 返回操作影响的行数
     */
    public int saveUser(User user);


}
