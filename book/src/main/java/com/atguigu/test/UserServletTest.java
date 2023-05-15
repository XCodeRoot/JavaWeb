package com.atguigu.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServletTest {

    public void login(){
        System.out.println("这是login()方法调用了");
    }
    public void regist(){
        System.out.println("这是regist()方法调用了");
    }
    public void updateUser(){
        System.out.println("这是updateUser()方法调用了");
    }
    public void updateUserPassword(){
        System.out.println("这是updateUserPassword()方法调用了");
    }

    public static void main(String[] args) {
        //获取action业务 鉴别字符串,获取相应的业务 方法反射对象
        String action="updateUserPassword";
        try {
            Method method = UserServletTest.class.getDeclaredMethod(action);

            System.out.println(method);

            method.invoke(new UserServletTest());


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
