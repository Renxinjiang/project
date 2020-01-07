package com;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: com.JdbcUtil
 * @Description:
 * jdbc工具类
 * @author: 呆呆
 * @date: 2020/1/6
 */
public class JdbcUtil {
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverClass = null;
    /**
     * 静态代码块中（只加载一次）
     */
    static{
        try {
            //读取db.properties文件
            Properties props = new Properties();
            InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");// 类加载器加载文件

            //加载文件
            props.load(in);
            //读取信息
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
            driverClass = props.getProperty("driverClass");


            //1.加载驱动程序
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("驱程程序加载失败");
        }
    }

    /**
     * 抽取获取连接对象的方法
     */
    public static Connection getConnection(){
        // 2.创建连接
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放资源的方法
     * 3.释放资源    后打开先关闭
     */
    public static void close(Connection conn,Statement stmt){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection conn,Statement stmt,ResultSet rs){
        if(rs!=null)
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
