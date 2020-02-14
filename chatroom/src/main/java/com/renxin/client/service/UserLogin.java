package com.renxin.client.service;

import com.renxin.client.dao.AccountDao;
import com.renxin.client.entity.User;
import com.renxin.util.CommUtil;
import com.vo.MessageVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Set;

/**
 * @PackageName: com.renxin.client.service
 * @ClassName: UserLogin
 * @Description:
 * 用户登录
 * @author: 呆呆
 * @date: 2019/11/18
 */
public class UserLogin {
    private JPanel loginPanel;
    private JPanel labelPanel;
    private JPanel userNamePanel;
    private JTextField userNameText;
    private JPanel passwordPanel;
    private JPanel btnPanel;
    private JButton regBtn;
    private JButton loginBtn;
    private JPasswordField passwordText;

    private AccountDao accountDao = new AccountDao();

    public UserLogin() {
        JFrame frame = new JFrame("用户登录");
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // 居中显示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 点击注册按钮，弹出注册页面
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new对象时，调用UserReg类的无参构造，就会弹出注册界面
                new UserReg();
            }
        });


        // 点击登录按钮，验证用户输入信息是否正确
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户输入
                String userName = userNameText.getText();
                String password = String.valueOf(passwordText.getPassword());
                User user = accountDao.userLogin(userName,password);
                if (user != null) {
                    // 登录成功
                    JOptionPane.showMessageDialog(null,"登录成功", "提示信息",
                            JOptionPane.INFORMATION_MESSAGE);
                    // 跳转到用户列表界面
                    // 与服务器建立连接,将用户名注册到服务端缓存
                    Connect2Server connect2Server = new Connect2Server();
                    // MessageVO是通信的一个载体
                    MessageVO messageVO = new MessageVO();
                    messageVO.setType(1);
                    messageVO.setContent(userName);
                    String msgJson = CommUtil.object2Json(messageVO);
                    try {
                        // 发送信息
                        PrintStream out = new PrintStream(connect2Server.getOut(),
                                true,"UTF-8");
                        out.println(msgJson);
                        // 读取服务端发回的响应,读取到所有的在线好友信息
                        Scanner in = new Scanner(connect2Server.getIn());
                        if (in.hasNextLine()) {
                            String jsonStr = in.nextLine();
                            MessageVO msgFromServer = (MessageVO) CommUtil.json2Object(jsonStr, MessageVO.class);
                            Set<String> names = (Set<String>) CommUtil.json2Object(msgFromServer.getContent(),
                                    Set.class);
                            System.out.println("在线好友为:"+names);
                            // 加载好友列表界面,将登陆界面不可见
                            frame.setVisible(false);
                            // 跳转到好友列表界面需要传递用户名,与服务器建立的连接,所有在线的好友信息
                            new FriendList(userName,connect2Server,names);
                        }
                    } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    }

                }else {
                    JOptionPane.showMessageDialog(null,"登录失败", "错误信息",
                            JOptionPane.ERROR_MESSAGE);
                    // 保留当前登录界面
                }
            }
        });
    }


    public static void main(String[] args) {
        new UserLogin();
    }
}
