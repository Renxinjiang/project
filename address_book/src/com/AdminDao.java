package com;

public interface AdminDao {
    // 增加操作
    public void insert(Admin aadmin);
    //显示操作
    public void show() throws Exception;
    // 修改操作
    public void update(Admin admin);
    // 删除操作
    public void delete(String id);
    // 按姓名查询操作
    public Admin queryByName(String name);
    // 按邮编排序
    public void sort();
}

