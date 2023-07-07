package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 *  工具类
 *      内部包含一个连接池对象,并且对外提供获取连接和回收连接的方法
 *   小提示: 推荐使用静态方法, 因为工具类是没有逻辑的,怎么方便怎么来就行了
 *
 *   实现:
 *      属性:
 *          1. 连接池对象(只实例化一次)
 *              (单例设计模式)
 *          2. static{
 *
 *             }
 *
 *
 *      方法:
 *          1.对外提供 连接的方法
 *          2.回收 外部传入连接 的方法
 *
 *  TODO:
 *      通过ThreadLocal线程本地变量存储连接,确保一个线程的多个方法可以获取到同一个connection
 *      优势: 事务操作的时候 Service和 Dao 属于一个线程,不用再传递参数了
 *      大家都可以调用 getConnection() ,自动获取的是同一个连接
 */

public class JdbcUtils {

    private static DataSource dataSource=null;//创建连接池对象
    private static ThreadLocal<Connection> conns=new ThreadLocal<Connection>();

    static {
        //创建 配置文件对象
        Properties properties = new Properties();
        //src下的文件可以使用类加载器 提供的方法
        //如果配置文件在src 的一个文件夹里 ,则路径为 "XXX/jdbc.properties"
        InputStream ips = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            //导入 外部配置文件 输入流
            properties.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            //创建连接池对象
            dataSource=DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()  {
        Connection conn=conns.get();
        if (conn==null){
            try {
                conn=dataSource.getConnection();//如果当前线程中没有连接,则从连接池里取一个连接
                conns.set(conn);//保存到ThreadLocal线程本地变量中 , 供jdbc使用
                conn.setAutoCommit(false);//将  事 务  设置为手动管理
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /** 提交事务 并 关闭连接
     *
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if(connection!=null){
            try {
                connection.commit();//先 尝试提交
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//无论有没有错误 , 都关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行 remove 操作 ,因为tomcat服务器 底层使用了线程池
        conns.remove();

    }

    /** 回滚事务 并 关闭连接
     *
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if(connection!=null){
            try {
                connection.rollback();// 回滚
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//无论有没有错误 , 都关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行 remove 操作 ,因为tomcat服务器 底层使用了线程池
        conns.remove();
    }



    /*
    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
     */


}
