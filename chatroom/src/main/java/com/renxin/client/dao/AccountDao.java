package com.renxin.client.dao;

import com.renxin.client.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

/**
 * @PackageName: com.renxin.client.dao
 * @ClassName: AccountDao
 * @Description:
 * 业务层，关于用户的dao
 * @author: 呆呆
 * @date: 2019/11/16
 */
public class AccountDao extends BasedDao{
    //新增一个用户
    public boolean userReg(User user) {
        // insert
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO user(username, password,brief)" + " VALUES (?,?,?)";
            //执行sql，受影响的行数
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getUserName());
            //加密后的密码
            preparedStatement.setString(2, DigestUtils.md5Hex(user.getPassword()));
            preparedStatement.setString(3,user.getBrief());
            int rows = preparedStatement.executeUpdate();
            if (rows == 1)
                return true;
        }catch (SQLException e) {
            System.err.println("用户注册失败");
            e.printStackTrace();
        }finally {
            closeResources(connection,preparedStatement);
        }
        return false;
    }

    //查询
    public User userLogin(String userName,String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,DigestUtils.md5Hex(password));
            resultSet = preparedStatement.executeQuery();
            //resultSet中有值，将其返回
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setBrief(resultSet.getString("brief"));
                return user;
            }
        }catch (SQLException e) {
            System.err.println("用户登录失败");
        }finally {
            closeResources(connection,preparedStatement,resultSet);
        }
        //表示这个用户不存在
        return null;
    }
}
