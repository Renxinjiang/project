package dao;

import common.JavaImageServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: dao
 * @ClassName: ImageDao
 * @Description:
 * 是Image对象的管理器，借助这个类完成Image对象的增删改查操作。
 * 1. 插入图片
 * 2. 查数据库中所有图片的信息
 * 3. 根据imageId查找指定的图片信息
 * 4. 根据imageId删除指定的图片
 * @author: 呆呆
 * @date: 2020/2/27
 */
public class ImageDao {
    /**
     * 将image对象插入数据库中
     * @param image
     */
    public void insert(Image image){
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 创建并拼装 SQL 语句
        String sql = "insert into image_table values(null, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,image.getImageName());
            statement.setInt(2,image.getSize());
            statement.setString(3,image.getUploadTime());
            statement.setString(4,image.getContentType());
            statement.setString(5,image.getPath());
            statement.setString(6,image.getMd5());

            // 3. 执行 SQL 语句
            int ret = statement.executeUpdate();
            if (ret != 1){
                // 程序出现问题, 抛出一个异常
                throw new JavaImageServerException("插入数据库出错!");
            }
        } catch (SQLException  | JavaImageServerException e) {
            e.printStackTrace();
        }finally {
            // 4. 关闭连接和statement对象,放在finally中，避免未被执行到
            DBUtil.close(connection,statement,null);
        }
    }

    /**
     * 查找数据库中所有图片信息
     * @return
     */
    public List<Image> selectAll(){
        List<Image> images = new ArrayList<>();
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 创建并拼装sql语句
        String sql = "select * from image_table";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 3. 执行sql语句
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            // 4. 处理结果集
            while (resultSet.next()){
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));
                image.setImageName(resultSet.getString("imageName"));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContentType(resultSet.getString("contentType"));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                images.add(image);
            }
            return images;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 5. 关闭连接
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    /**
     * 根据 imageId 查找指定的图片信息
     * @param imageId
     * @return
     */
    public Image selectOne(int imageId){
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 创建并拼装sql语句
        String sql = "select * from image_table where imageId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 3. 执行sql语句
            statement = connection.prepareStatement(sql);
            statement.setInt(1,imageId);
            resultSet = statement.executeQuery();
            // 4. 处理结果集
            if (resultSet.next()){
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));
                image.setImageName(resultSet.getString("imageName"));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContentType(resultSet.getString("contentType"));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                return image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 5. 关闭连接
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    /**
     * 根据 imageId 删除指定的图片
     * @param imageId
     */
    public void delete(int imageId){
        // 1. 获取连接
        Connection connection = DBUtil.getConnection();
        // 2. 创建并拼装sql语句
        String sql = "delete from image_table where imageId = ?";
        PreparedStatement statement = null;
        // 3. 执行sql语句
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,imageId);
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new JavaImageServerException("删除数据库操作失败");
            }
        } catch (SQLException | JavaImageServerException e) {
            e.printStackTrace();
        }finally {
            // 4. 关闭连接
            DBUtil.close(connection,statement,null);
        }
    }

    public Image selectByMd5(String md5) {
        // 1. 获取连接
        Connection connection = DBUtil.getConnection();
        // 2. 创建并拼装sql语句
        String sql = "select * from image_table where md5 = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 3. 执行sql语句
            statement = connection.prepareStatement(sql);
            statement.setString(1,md5);
            resultSet = statement.executeQuery();
            // 4. 处理结果集
            if (resultSet.next()){
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));
                image.setImageName(resultSet.getString("imageName"));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContentType(resultSet.getString("contentType"));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                return image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 5. 关闭结果集
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
}
