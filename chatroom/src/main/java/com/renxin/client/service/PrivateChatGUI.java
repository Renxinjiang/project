package com.renxin.client.service;

import com.renxin.util.CommUtil;
import com.vo.MessageVO;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * @PackageName: com.renxin.client.service
 * @ClassName: PrivateChatGUI
 * @Description:
 * @author: 呆呆
 * @date: 2019/12/30
 */
public class PrivateChatGUI {
    private JPanel privateChatPanel;
    private JTextArea readFromServer;
    private JTextField send2Server;
    private JFrame frame;

    // 私聊对象的名字是由用户列表传来的
    private String friendName;
    // type:2
    // content:senderName-msg
    // to:friendName
    private String myName;
    private Connect2Server connect2Server;
    private PrintStream out;

    public PrivateChatGUI(String friendName,String myName,Connect2Server connect2Server) {
        this.friendName = friendName;
        this.myName = myName;
        this.connect2Server = connect2Server;
        frame = new JFrame("与"+friendName+"私聊中...");
        frame.setContentPane(privateChatPanel);
        // 关闭私聊界面不能退出客户端
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 只需要隐藏
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);

        // 添加一个输入框的事件，敲回车发送信息
        // 捕捉输入框的键盘输入
        send2Server.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append(send2Server.getText());
                // 1.当捕捉到按下Enter，发送文本信息
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 2.将当前信息发送到服务端
                    String msg = sb.toString();
                    // 发送信息
                    MessageVO messageVO = new MessageVO();
                    messageVO.setType(2);
                    messageVO.setContent(myName+"-"+msg);
                    messageVO.setTo(friendName);
                    try {
                        PrintStream out = new PrintStream(connect2Server.getOut(),true,"UTF-8");
                        out.println(CommUtil.object2Json(messageVO));
                        // 3.刷新自己的信息读取框
                        readFromServer.append(myName+"说:"+msg+"\n");
                        // 4.清空输入框
                        send2Server.setText("");
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    //给信息接收方使用
    public void readFromServer(String msg) {
        readFromServer.append(msg+"\n");
    }

    public JFrame getFrame() {
        return frame;
    }
}


