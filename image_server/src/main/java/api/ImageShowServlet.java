package api;

import dao.Image;
import dao.ImageDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;

/**
 * @PackageName: api
 * @ClassName: ImageShowServlet
 * @Description:
 * @author: 呆呆
 * @date: 2020/3/3
 */
public class ImageShowServlet extends HttpServlet {
    //防盗链机制：白名单
    static private HashSet<String> whiteList = new HashSet<>();

    static {
        whiteList.add("http://127.0.0.1:8080/image_server/index.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否在白名单之中
        String referer = req.getHeader("Referer");
        if (!whiteList.contains(referer)) {
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write("{ \"ok\": false, \"reason\": \"未授权的访问\" }");
            return;
        }
        // 1. 解析出 imageId
        String imageId = req.getParameter("imageId");
        if (imageId == null || imageId.equals("")) {
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write("{ \"ok\": false, \"reason\": \"imageId 解析失败\" }");
            return;
        }
        // 2. 根据 imageId 查找数据库, 得到对应的图片属性信息(需要知道图片存储的路径)
        ImageDao imageDao = new ImageDao();
        Image image = imageDao.selectOne(Integer.parseInt(imageId));
        // 3. 根据路径打开文件, 读取其中的内容, 写入到响应对象中
        resp.setContentType(image.getContentType());
        File file = new File(image.getPath());
        // 由于图片是二进制文件, 应该使用字节流的方式读取文件
        OutputStream outputStream = resp.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        while (true) {
            int len = fileInputStream.read(buffer);
            if (len == -1) {
                // 文件读取结束
                break;
            }
            // 此时已经读到一部分数据, 放到 buffer 里, 把 buffer 中的内容写到响应对象中
            outputStream.write(buffer);
        }
        fileInputStream.close();
        outputStream.close();
    }
}
