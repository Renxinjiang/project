package servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @PackageName: servlet
 * @ClassName: Test
 * @Description:
 * @author: 呆呆
 * @date: 2020/3/25
 */
public class Test {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "SELECT words FROM t_tangshi";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        String words = rs.getString("words");
                        System.out.println(words);
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            JSONObject object = new JSONObject();
            object.put("error", e.getMessage());
        }
    }
}
