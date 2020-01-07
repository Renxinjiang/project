package com;

import java.sql.*;
import java.util.Scanner;

/**
 * @PackageName: PACKAGE_NAME
 * @ClassName: com.Main
 * @Description:
 * @author: 呆呆
 * @date: 2020/1/6
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        AdminDaoImpl admindao = new AdminDaoImpl();
        while (true){
            System.out.println("********************");
            System.out.println("*     [1]添加      *");
            System.out.println("*     [2]显示      *");
            System.out.println("*     [3]删除      *");
            System.out.println("*     [4]查询      *");
            System.out.println("*     [5]修改      *");
            System.out.println("*     [6]排序      *");
            System.out.println("********************");
            System.out.println("请输入你的选择");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{// 添加
                    System.out.println("请输入联系人姓名");
                    String name1 = scanner.next();
                    System.out.println("请输入联系人地址");
                    String name2 = scanner.next();
                    System.out.println("请输入联系人电话号码");
                    String name3 = scanner.next();
                    System.out.println("请输入联系人邮编");
                    String name4 = scanner.next();
                    System.out.println("请输入联系人电子邮箱");
                    String name5 = scanner.next();
                    System.out.println("请输入联系人家庭电话");
                    String name6 = scanner.next();
                    Admin admin = new Admin(name1, name2, name3, name4,name5,name6);
                    admindao.insert(admin);
                    break;
                }
                case 2:{// 显示
                    System.out.println("通讯录信息如下");
                    admindao.show();
                    break;
                }
                case 3:{// 删除
                    System.out.println("请输入您要删除的联系人的姓名是?");
                    String name = scanner.next();
                    admindao.delete(name);
                    break;
                }
                case 4:{//按姓名查询
                    System.out.println("请输入您要查询的名字");
                    String name = scanner.next();
                    admindao.queryByName(name);
                    break;
                }
                case 5:{//修改
                    System.out.println("请输入您要修改的联系人姓名");
                    String name1 = scanner.next();
                    System.out.println("请输入您要修改的联系人地址");
                    String name2 = scanner.next();
                    System.out.println("请输入您要修改的联系人电话号码");
                    String name3 = scanner.next();
                    System.out.println("请输入您要修改的联系人邮编");
                    String name4 = scanner.next();
                    System.out.println("请输入您要修改的联系人电子邮箱");
                    String name5 = scanner.next();
                    System.out.println("请输入您要修改的联系人家庭电话");
                    String name6 = scanner.next();
                    Admin admin = new Admin(name1, name2, name3, name4,name5,name6);
                    admindao.update(admin);
                    break;
                }
                case 6:{
                    admindao.sort();
                }
            }
        }


    }
}
