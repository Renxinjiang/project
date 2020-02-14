package com.renxin.client.entity;

import lombok.Data;

/**
 * @PackageName: com.renxin.client.entity
 * @ClassName: User
 * @Description:
 * 数据库user表的实体类
 * @author: 呆呆
 * @date: 2019/11/16
 */

@Data  //注解
public class User {
    //类型要与数据库表的类型完全一致
    //数据库的实体，所有的基本数据类型要用包装类
    private Integer id;
    private String userName;
    private String password;
    private String brief;
}
