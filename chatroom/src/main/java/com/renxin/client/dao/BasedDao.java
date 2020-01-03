package com.renxin.client.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.renxin.util.CommUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @PackageName: com.renxin.client.dao
 * @ClassName: BasedDao
 * @Description:
 * dao层基础类，封装数据源、获取连接、关闭资源等共有操作
 * @author: 呆呆
 * @date: 2019/11/16
 */

public class BasedDao {
    //数据源用DruidDataSource
    private static DruidDataSource DATASOURCE;
    // 1.加载数据源
    //静态代码块---类一加载的时候数据源就会被加载
    static {
        Properties pros = CommUtil.loadProperties("db.properties");
        try {
            //将db.properties配置文件传进来
            DATASOURCE = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            System.err.println("数据源加载失败");
            e.printStackTrace();
        }
    }

    // 2.获取连接
    //获取连接是所有子类共有的，所有子类需要继承，所以用protected
    protected Connection getConnection() {
        try {
            //池中连接可以重复利用
            return (Connection) DATASOURCE.getPooledConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4.关闭资源
    //更新类，要关闭两个资源：Connection和Statement
    protected void closeResources(Connection conn, Statement statement) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //select类，要关闭三个资源：Connection和Statement和ResultSet
    protected void closeResources(Connection conn, Statement statement, ResultSet resultSet) {
        closeResources(conn,statement);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
