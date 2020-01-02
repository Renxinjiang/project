package com.renxin;

import com.renxin.util.CommUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * @PackageName: com.renxin
 * @ClassName: JDBCTest
 * @Description:
 * @author: 呆呆
 * @date: 2019/11/14
 */
public class JDBCTest {
    private static String url;
    private static String username;
    private static String password;

    static{
        // 1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = CommUtils.loadProperties("db.properties");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
    @Test
    //测试JDBC的查询操作
    public void queryTest(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM user";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("id为："+id+"，用户名为："+username+"，密码为："+password);
            }
        }catch (SQLException e){

        }finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    //测试JDBC的插入操作
    public void insertTest(){
        Connection connection = null;
        Statement statement = null;
        //此处不需要返回结果集
        try {
            connection = DriverManager.getConnection(url,username,password);
            //先将sql置为空，写完statement将sql传进去后，再写sql会有提示
            String sql = "INSERT INTO user(username,password)VALUES('test3','456')";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            Assert.assertEquals(1,rows);
        }catch (SQLException e){

        }finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void deleteTest(){
        Connection connection = null;
        Statement statement = null;
        //此处不需要返回结果集
        try {
            connection = DriverManager.getConnection(url,username,password);
            //先将sql置为空，写完statement将sql传进去后，再写sql会有提示
            String sql = "DELETE FROM user WHERE id = '4'";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            Assert.assertEquals(1,rows);
        }catch (SQLException e){

        }finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void updateTest(){
        Connection connection = null;
        Statement statement = null;
        //此处不需要返回结果集
        try {
            connection = DriverManager.getConnection(url,username,password);
            //先将sql置为空，写完statement将sql传进去后，再写sql会有提示
            String sql = "UPDATE FROM user SET password = '123' WHERE id='2'";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            Assert.assertEquals(1,rows);
        }catch (SQLException e){

        }finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
