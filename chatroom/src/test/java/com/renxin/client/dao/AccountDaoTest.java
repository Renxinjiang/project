package com.renxin.client.dao;

import com.renxin.client.entity.User;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDaoTest {
    private AccountDao accountDao = new AccountDao();
    @Test
    public void userReg() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("123");
        user.setBrief("可可爱爱");
        boolean b = accountDao.userReg(user);
        Assert.assertTrue(b);
    }

    @Test
    public void userLogin() {
        User user = accountDao.userLogin("test","123");
        Assert.assertNotNull(user);
    }
}