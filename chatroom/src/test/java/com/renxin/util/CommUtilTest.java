package com.renxin.util;

import com.renxin.client.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class CommUtilTest {

    @Test
    public void loadProperties() {
        Properties pros = CommUtil.loadProperties("db.properties");
        Assert.assertNotNull(pros);
    }

    @Test
    public void obj2Json() {
        User user = new User();
        user.setId(1);
        user.setUserName("test");
        user.setPassword("123");
        user.setBrief("可可爱爱");
        String str = CommUtil.object2Json(user);
        System.out.println(str);
    }

    @Test
    public void json2Object() {
        String str = "{\"id\":1,\"userName\":\"test\",\"password\":\"123\",\"brief\":\"可可爱爱\",\"names\":[\"test2\",\"test\",\"test1\"]}";
        User user = (User) CommUtil.json2Object(str,User.class);
        System.out.println(user);
    }
}