package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: util.TestGson
 * @Description: Gson测试
 * JSON可以用map表示，也可以用对象表示
 * JSON里的键值对没有顺序
 * @author: 呆呆
 * @date: 2020/2/17
 */
class Information{
    public String name;
    public String phone;
    public  String address;
}

public class TestGson {
    public static void main1(String[] args) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("phone","1234");
        map.put("address","陕西西安");

        // 通过map转成JSON结构的字符串
        // 1.创建gson对象
        Gson gson = new GsonBuilder().create();
        // 2.使用toJson方法将键值对转换成JSON结构字符串
        String str = gson.toJson(map);
        System.out.println(str);
    }

    public static void main(String[] args) {
        Information info = new Information();
        info.name = "李四";
        info.phone = "2234";
        info.address = "陕西咸阳";
        // 1.创建gson对象
        Gson gson = new GsonBuilder().create();
        // 2.使用toJson方法将键值对转换成JSON结构字符串
        String str = gson.toJson(info);
        System.out.println(str);
    }
}
