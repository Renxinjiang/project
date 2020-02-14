package com.renxin.client.service;

import com.renxin.client.dao.AccountDao;
import com.renxin.client.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @PackageName: com.renxin.client.service
 * @ClassName: UserReg
 * @Description:
 * 用户注册
 * @author: 呆呆
 * @date: 2019/11/18
 */
public class UserReg {
    private JPanel regPanel;
    private JTextField userNameText;
    private JPasswordField passwordText;
    private JTextField briefText;
    private JButton confimBtn;

    // service层需要调用dao层的方法
    private AccountDao accountDao = new AccountDao();

    public UserReg() {
        // 主方法的内容放在构造方法中
        JFrame frame = new JFrame("用户注册");
        frame.setContentPane(regPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //居中显示该界面，默认是在左上角显示
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        //点击提交信息按钮提交此方法
        confimBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1.获取界面上三个控件的内容
                String userName  = userNameText.getText();
                String password = String.valueOf(passwordText.getPassword());  // 默认是字符，需要包装
                String brief = briefText.getText();
                // 2.调用dao层方法将信息持久化到数据库
                User user = new User();
                user.setUserName(userName);
                user.setPassword(password);
                user.setBrief(brief);
                System.out.println(user);
                if (accountDao.userReg(user)) {
                    // 弹出提示框提示用户信息注册成功
                    // 返回登录界面
                    JOptionPane.showMessageDialog(null, "注册成功!","成功信息",
                            JOptionPane.INFORMATION_MESSAGE);
                    //让注册界面消失，返回登录界面
                    frame.setVisible(false);
                }else {
                    // 弹出提示框告知用户注册失败
                    // 保留当前注册页面
                    JOptionPane.showMessageDialog(null, "注册失败!","错误信息",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
