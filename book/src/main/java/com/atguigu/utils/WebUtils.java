package com.atguigu.utils;

import com.atguigu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtils {


    /**
     * 把Map中的值注入到对应的  JavaBean属性中
     * @param value
     * @param bean
     */
    public static <T>  T copyParamTOBean(Map value, T bean){
        try {
            System.out.println("注入之前"+ bean);
            /**
             * 把所有请求参数都注入到user对象中
             */
            BeanUtils.populate(bean,value);
            System.out.println("注入之后"+ bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     *  将字符串转为int类型
     *
     * @param strInt 接收传来的 字符串
     * @param defaultValue 默认值 (int)
     * @return 转成功就是 返回转换后的int值 ,失败 则返回 默认值
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }

}
