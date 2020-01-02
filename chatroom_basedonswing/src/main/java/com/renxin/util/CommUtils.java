package com.renxin.util;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @PackageName: com.renxin.util
 * @ClassName: CommUtils
 * @Description:
 * 工具类
 * 封装公共的工具方法
 * @author: 呆呆
 * @date: 2019/11/14
 */
public class CommUtils {
    /**
     * 加载配置文件信息
     * @param filename 要加载的配置文件名称
     * @return
     */
    public static Properties loadProperties(String filename){
        Properties properties = new Properties();
        //将配置文件转为输入流
        InputStream in = CommUtils.class.getClassLoader().getResourceAsStream(filename);
        //加载配置信息
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
