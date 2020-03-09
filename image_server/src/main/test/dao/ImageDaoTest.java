package dao;

import org.junit.Test;

import java.util.List;

/**
 * @PackageName: dao
 * @ClassName: ImageDaoTest
 * @Description:
 * 对ImageDao数据库的操作进行单元测试
 * @author: 呆呆
 * @date: 2020/2/27
 */
public class ImageDaoTest {
    private ImageDao imageDao = new ImageDao();

    /*
    @Test
    public void insert() {
        Image image = new Image();
        image.setImageName("1.png");
        image.setSize(100);
        image.setUploadTime("20200227");
        image.setContentType("image/png");
        image.setPath("./data/1.png");
        image.setMd5("112233");
        imageDao.insert(image);
    }
    */

    @Test
    public void selectAll() {
        List<Image> images = imageDao.selectAll();
        System.out.println(images);
    }

    @Test
    public void selectOne() {
        Image image = imageDao.selectOne(1);
        System.out.println(image);
    }

    @Test
    public void delete() {
        imageDao.delete(6);
    }
}
