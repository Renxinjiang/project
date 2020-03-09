package dao;




import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @PackageName: dao
 * @ClassName: DBUtil
 * @Description:
 * 封装获取数据库连接的过程。
 * 1. 创建DataSource对象
 * 2. 获取Connection连接
 * 3. 关闭资源（先打开的后关闭）
 * @author: 呆呆
 * @date: 2020/2/18
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mariadb?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "794521557";

    // DataSource为Java的内置类，可以获取数据库连接，进而操作数据库
    // 加volatile是为了保证dataSource = new MysqlDataSource();的原子性
    private static volatile DataSource dataSource = null;
    // 通过单例的模式创建DataSource对象
    public static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (DBUtil.class){
                if (dataSource == null){
                    dataSource = new MysqlDataSource();
                    MysqlDataSource tmpDataSource = (MysqlDataSource) dataSource;
                    tmpDataSource.setURL(URL);
                    tmpDataSource.setUser(USERNAME);
                    tmpDataSource.setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    // 2. 获取连接
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3. 关闭资源
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
