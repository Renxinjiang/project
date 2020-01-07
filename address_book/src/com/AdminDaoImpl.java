package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com
 * @ClassName: AdminDaoImpl
 * @Description:
 * @author: 呆呆
 * @date: 2020/1/6
 */
public class AdminDaoImpl implements AdminDao {
    // 全局变量初始化
    PreparedStatement pstmt = null; // 预编译对象
    Connection conn = null; // 连接对象
    ResultSet rs = null; // 结果集对象

    // 增加操作
    @Override
    public void insert(Admin admin) {
        try {
            // 0.需要操作的sql
            String sql = "INSERT INTO contact_system.contact (name,address,phone,postcode,email,homePhone) values(?,?,?,?,?,?);";
            // 1.使用工具类连接数据库
            conn = JdbcUtil.getConnection();
            // 2.预编译sql
            pstmt = conn.prepareStatement(sql);
            // 3.设置参数
            pstmt.setString(1, admin.getName());
            pstmt.setString(2,admin.getAddress());
            pstmt.setString(3, admin.getPhone());
            pstmt.setString(4,admin.getPostcode());
            pstmt.setString(5, admin.getEmail());
            pstmt.setString(6,admin.getHomePhone());
            // 4.执行相应操作并返回结果
            int result = pstmt.executeUpdate();
            System.out.println("影响了:" + result + "记录");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, pstmt, null); // 关闭对象
        }
    }

    // 显示
    @Override
    public void show() throws Exception {
        // 0.需要执行的sql语句
        String sql = "SELECT * FROM contact_system.contact";
        // 1.使用工具类连接数据库
        conn = JdbcUtil.getConnection();
        // 2.预编译sql
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery(sql);
        // 3.执行相应操作并返回结果
        while (rs.next()){
            String name = rs.getString(1);
            String address = rs.getString(2);
            String phone = rs.getString(3);
            String postcode = rs.getString(4);
            String email = rs.getString(5);
            String homePhone = rs.getString(6);
            System.out.println("姓名: "+name+"\t"
                    +"地址："+address+"\t"
                    +"电话："+phone+"\t"
                    +"邮编："+postcode+"\t"
                    +"邮箱："+email+"\t"
                    +"家庭电话："+homePhone);
        }
    }

    // 修改操作
    @Override
    public void update(Admin admin) {
        try {
            // 0.需要操作的sql
            String sql = "UPDATE contact_system.contact SET address=?,phone=?,postcode=?,email=?,homePhone=? WHERE name=?";
            // 1.使用工具类连接数据库
            conn = JdbcUtil.getConnection();
            // 2.预编译sql
            pstmt = conn.prepareStatement(sql);
            // 3.设置参数
            pstmt.setString(1, admin.getAddress());
            pstmt.setString(2, admin.getPhone());
            pstmt.setString(3,admin.getPostcode());
            pstmt.setString(4, admin.getEmail());
            pstmt.setString(5,admin.getHomePhone());
            pstmt.setString(6, admin.getName());
            // 4.执行相应操作并返回结果
            int result = pstmt.executeUpdate();
            System.out.println("影响了" + result + "记录");

        } catch (Exception e) {
            throw new RuntimeException(e);
            // 5.关闭数据库连接对象
        } finally {
            JdbcUtil.close(conn, pstmt, null); // 关闭对象
        }
    }

    // 删除操作
    @Override
    public void delete(String name){
        try {
            // 0.需要执行的sql语句
            String sql = "DELETE FROM contact_system.contact WHERE name=?";

            // 1.使用工具类连接数据库
            conn = JdbcUtil.getConnection();
            // 2.预编译sql
            pstmt = conn.prepareStatement(sql);
            // 3.设置参数
            pstmt.setString(1, name);
            // 4.执行相应操作并返回结果
            int result = pstmt.executeUpdate();
            System.out.println("影响了" + result + "记录");

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 5.关闭数据库连接对象
            JdbcUtil.close(conn, pstmt, null);
        }
    }

    // 按name查询操作
    @Override
    public Admin queryByName(String name){
        Admin admin = null;
        try {
            // 0.需要执行的sql操作
            String sql = "SELECT name,address,phone,postcode,email,homePhone FROM contact_system.contact WHERE name=?";
            // 1.使用工具类连接数据库
            conn = JdbcUtil.getConnection();
            // 2.预编译sql
            pstmt = conn.prepareStatement(sql);
            // 3.设置参数
            pstmt.setString(1, name);
            // 4.执行相应操作并返回结果
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 查询出内容，之后将查询出的内容赋值给admin对象
                admin = new Admin(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                System.out.println("姓名: "+admin.getName()+"\t"
                        +"地址："+admin.getAddress()+"\t"
                        +"电话："+admin.getPhone()+"\t"
                        +"邮编："+admin.getPostcode()+"\t"
                        +"邮箱："+admin.getEmail()+"\t"
                        +"家庭电话："+admin.getHomePhone());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 5.关闭数据库连接对象
            JdbcUtil.close(conn, pstmt, rs);
        }
        return admin;

    }

    @Override
    public void sort() {
        Admin admin = null;
        // 0.需要执行的sql语句
        String sql = "SELECT * FROM contact_system.contact ORDER BY postcode";
        // 1.使用工具类连接数据库
        conn = JdbcUtil.getConnection();
        // 2.预编译sql
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                // 查询出内容，之后将查询出的内容赋值给admin对象
                admin = new Admin(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                System.out.println("姓名: "+admin.getName()+"\t"
                        +"地址："+admin.getAddress()+"\t"
                        +"电话："+admin.getPhone()+"\t"
                        +"邮编："+admin.getPostcode()+"\t"
                        +"邮箱："+admin.getEmail()+"\t"
                        +"家庭电话："+admin.getHomePhone());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
