package com.atguigu.util;

import javax.servlet.http.Cookie;

public class CookieUtils {

    /**
     *
     * @param name 想查找的cookie名字
     * @param cookies 所有cookie组成的集合 , 一般用req.getCookies()找到所有cookie
     * @return
     */
    public static Cookie findCookie(String name,Cookie[] cookies){
        if(name==null||cookies==null||cookies.length==0 ){
            return null;
        }

        for (Cookie cookie : cookies) {
            if(name.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }
}
