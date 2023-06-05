package com.CodeRoot.dao.impl;

import com.CodeRoot.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //抽象

    private QueryRunner queryRunner=new QueryRunner();

    /**
     * update()方法用来执行: Insert\Update\Delete
     * 如果返回-1表示执行失败
     * @return 返回影响行数
     */
    public int update(String sql,Object...args){
        //1.创建连接
        Connection connection = JdbcUtils.getConnection();
        try {
            //调用的是 QueryRunner 里的 update()方法, 这个方法 你传入连接,SQL语句和占位符的值,
            //它会自动创建preparedStatement对象,然后进行占位符赋值
            //然后执行SQL语句 并返回 影响行数
            //相当于是之前的

            //2.创建preparedStatement对象
            //3.占位符赋值
            //4.发送SQL语句,并返回影响行数
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.回收资源
            JdbcUtils.close(connection);
        }
        return -1;
    }

    /**
     * 查询返回一个javaBean的sql语句
     *
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回类型的泛型
     * @return
     */
    public<T> T  queryForOne(Class<T>type, String sql,Object...args){
        //1.创建连接
        Connection connection = JdbcUtils.getConnection();

        try {
            //2.创建preparedStatement对象
            //3.占位符赋值
            //4.发送SQL语句 并返回 结果集
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5.回收连接
            JdbcUtils.close(connection);
        }
        return null;
    }


    /**
     * 查询返回多个javaBean的sql语句
     *
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回类型的泛型
     * @return List<T> 返回List集合 结果集
     */
    public<T> List<T> queryForList(Class<T>type, String sql, Object...args){
        //1.建立连接
        Connection connection = JdbcUtils.getConnection();

        try {
            //2.创建preparedStatement对象
            //3.占位符赋值
            //4.执行SQL语句 , 返回结果集
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql,Object...args){
        //1.创建连接
        Connection connection = JdbcUtils.getConnection();
        try {
            //2.创建preparedStatement对象
            //3.占位符赋值
            //4.执行SQL语句,返回结果集
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }
}