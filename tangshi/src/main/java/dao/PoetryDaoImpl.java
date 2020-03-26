package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName: dao
 * @ClassName: PoetryDaoImpl
 * @Description:
 * @author: 呆呆
 * @date: 2020/3/24
 */
public class PoetryDaoImpl implements PoetryDao {

    @Override
    public List<Echart> findByAmount(int left, int right) {
        ArrayList<Echart> echarts = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            con = DBUtil.getConnection();
            //2.定义sql语句
            String sql = "select author,count(*) as total from t_tangshi group by author having total >= ? and total <=? order by total DESC";
            //3.创建执行对象
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, left);
            pstm.setInt(2, right);
            //4.查询
            rs = pstm.executeQuery();
            //5.处理结果集
            while (rs.next()) {
                String author = rs.getString("author");
                int total = Integer.parseInt(rs.getString("total"));
                Echart echart = new Echart();
                echart.setName(author);
                echart.setCount(total);
                echarts.add(echart);
            }

            /*//6.测试一下数据
            for (int i = 0; i < echarts.size(); i++) {
                System.out.println(echarts.get(i));
            }*/
            return echarts;
        } catch (Exception e) {
            System.out.println("根据作诗量查询失败!");
            return null;
        } finally {
            //释放资源
            DBUtil.close(con, pstm, rs);
        }
    }

    @Override
    public Map<String, Integer> findWordsByName(String name) {
        //1.创建一个Map集合
        HashMap<String, Integer> map = new HashMap<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            con = DBUtil.getConnection();
            //2.定义sql语句
            String sql;
            //3.创建执行对像
            if ("大家".equals(name)) {
                sql = "select words from tangshi";
                pstm = con.prepareStatement(sql);
            } else {
                //查询指定的诗人
                sql = "select words from t_tangshi where author = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, name);
            }
            //4.查询
            rs = pstm.executeQuery();
            //5.处理结果集
            while (rs.next()) {
                String word = rs.getString("words");
                String[] words = word.split(" ");
                for (String word1 : words) {
                    map.put(word1, map.getOrDefault(word1, 0) + 1);
                }
            }
            /*//6.测试一下数据
            Set<String> sets = map.keySet();
            for(String key:sets){
                System.out.println(map.get(key));
            }*/
            return map;
        }catch(Exception e){
            System.out.println("根据诗人名查询失败!");
            return null;
        }finally{
            //释放资源
            DBUtil.close(con, pstm, rs);
        }
    }

    @Override
    public List<String> findPoetByName(int i) {
        //1.创建一个List集合
        ArrayList<String> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            con = DBUtil.getConnection();
            //2.定义sql语句
            String sql;
            //3.创建执行对像
            sql = "SELECT DISTINCT author FROM t_tangshi GROUP BY author HAVING author LIKE ?";
            pstm = con.prepareStatement(sql);
            if(i == 2){
                pstm.setString(1,"__");
            }else{
                pstm.setString(1,"___");
            }
            //4.查询
            rs = pstm.executeQuery();
            //5.处理结果集
            while (rs.next()) {
                String author = rs.getString("author");
                list.add(author);
            }
            /*//6.测试一下数据
            Set<String> sets = map.keySet();
            for(String key:sets){
                System.out.println(map.get(key));
            }*/
            return list;
        }catch(Exception e){
            System.out.println("根据诗人名字字的个数查询失败!");
            return null;
        }finally{
            //释放资源
            DBUtil.close(con, pstm, rs);
        }
    }
}
