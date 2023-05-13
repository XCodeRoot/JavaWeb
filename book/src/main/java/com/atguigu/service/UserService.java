package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 用户登录
     * @param user
     * @return 登录失败返回 null ,返回有值 则登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可以用
     * @param username
     * @return 返回true表示用户名已存在,返回false表示用户名可用
     */
    public boolean existsUsername(String username);





}
