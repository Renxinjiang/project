package com;

/**
 * @PackageName: com
 * @ClassName: DaoFactory
 * @Description:
 * @author: 呆呆
 * @date: 2020/1/6
 */
public class DaoFactory {
    public static AdminDao getAdminDaoInstance() {
        return new AdminDaoImpl();
    }

}
