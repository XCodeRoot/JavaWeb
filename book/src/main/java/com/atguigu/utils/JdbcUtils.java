package com.atguigu.utils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;

    static {

        try {//抛出异常
            Properties properties = new Properties();
            //读取jdbc.properties
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //类型转换
            //创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取数据库连接池中的连接
     * 如果返回null说明连接失败
     * 有值就是连接成功
     * @return
     */
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  connection;
    }

    /**
     * 关闭连接,放回数据库连接池
     * @param connection
     */
    public static void close(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
