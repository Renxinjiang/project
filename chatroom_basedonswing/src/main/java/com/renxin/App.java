package com.renxin;

import com.renxin.util.CommUtils;

import java.sql.*;
import java.util.Collection;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        //Properties properties = CommUtils.loadProperties("db.properties");
        //System.out.println(properties);
        //获取配置文件指定的参数
        // System.out.println(properties.getProperty("url"));
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = CommUtils.loadProperties("db.properties");
        String url =properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
       /*
        String url = "jdbc:mysql://localhost:3306/jdbc_text";
        String username = "root";
        String password = "794521557";
        */
        //2.获取连接
        // DriverManager是驱动管理类
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
            // 3.执行相应的sql语句
            statement = connection.createStatement();
            String sqlStr = "SELECT * FROM user";
            rs = statement.executeQuery(sqlStr);
            while (rs.next()){
                int id = rs.getInt("id");
                String userName = rs.getString("username");
                String ps = rs.getString("password");
                System.out.println("id为："+id+",用户名为："+userName+"，密码为："+ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 4.关闭资源
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
