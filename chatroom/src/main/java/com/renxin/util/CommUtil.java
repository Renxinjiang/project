package com.renxin.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @PackageName: com.renxin.util
 * @ClassName: CommUtil
 * @Description:
 * 封装所有公共操作，包括加载配置文件、json操作等
 * @author: 呆呆
 * @date: 2019/11/16
 */
public class CommUtil {
    //第三方类库：建造者模式
    private static final Gson GSON = new GsonBuilder().create();

    //所有工具方法都是静态的
    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        //获取该文件的输入流，把资源文件变为输入流
        //getResourceAsStream读取和源文件同目录的资源文件夹
        InputStream in = CommUtil.class.getClassLoader().getResourceAsStream(fileName);
        try {
            //将输入流加载进来
            properties.load(in);
        } catch (IOException e) {
            System.err.println("资源文件加载失败");
            e.printStackTrace();
            return null;
        }
        return properties;
    }

    /**
     * 将任意对象序列化为json字符串
     * @param obj
     * @return
     */
    public static String object2Json(Object obj) {
        return GSON.toJson(obj);
    }
    // private static final Gson GSON = new GsonBuilder().create();
    /**
     * 将json串反序列化为对象
     * @param jsonStr
     * @param objClz
     * @return
     */
    public static Object json2Object(String jsonStr,Class objClz) {
        return GSON.fromJson(jsonStr,objClz);
    }

}
