package servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @PackageName: servlet
 * @ClassName: WordsServlet
 * @Description:
 * @author: 呆呆
 * @date: 2020/3/25
 */
@WebServlet("/words")
public class WordsServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("application/json; charset=utf-8");
            JSONArray jsonArray = new JSONArray();

            Map<String,Integer> map = new TreeMap<>();
            try (Connection connection = DBUtil.getConnection()) {
                String sql = "SELECT words FROM t_tangshi";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next()) {
                            String words = rs.getString("words");
                            String word[] = words.split(",");
                            for (int i = 0;i < word.length;i++){
                                if (!map.containsKey(word[i])){
                                    map.put(word[i],1);
                                }else {
                                    map.put(word[i], map.get(word[i]) + 1);
                                }
                            }
                        }
                        for (Map.Entry<String,Integer> entry : map.entrySet()){
                            JSONArray item = new JSONArray();
                            item.add(entry.getKey());
                            item.add(entry.getValue());
                            jsonArray.add(item);
                        }
                        resp.getWriter().write(jsonArray.toJSONString());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JSONObject object = new JSONObject();
                object.put("error", e.getMessage());
                resp.getWriter().println(object.toJSONString());
            }
        }
    }

