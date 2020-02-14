package com.renxin.client.service;

import com.renxin.util.CommUtil;
import com.sun.javaws.util.JfxHelper;
import com.vo.MessageVO;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;

/**
 * @PackageName: com.renxin.client.service
 * @ClassName: GroupChatGUI
 * @Description:
 * @author: 呆呆
 * @date: 2020/1/1
 */
public class GroupChatGUI {
    private JTextArea readFromServer;
    private JPanel groupChatGUIPanel;
    private JTextField send2Server;
    private JPanel friendPanel;
    private JFrame frame;

    private String groupName;
    private Set<String> friends;
    private Connect2Server connect2Server;
    private String myName;

    public GroupChatGUI(String groupName,Set<String> friends,Connect2Server connect2Server,String myName){
        this.groupName = groupName;
        this.friends = friends;
        this.connect2Server = connect2Server;
        this.myName = myName;

        frame = new JFrame(groupName);
        frame.setContentPane(groupChatGUIPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 1.好友列表的展示
        friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.Y_AXIS));
        Iterator<String> iterator = friends.iterator();
        while (iterator.hasNext()) {
            String friendName = iterator.next();
            JLabel label = new JLabel(friendName);
            friendPanel.add(label);
        }

        // 2.文本框发送事件
        send2Server.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append(send2Server.getText());
                // 捕捉回车按键
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String str2Server = sb.toString();
                    // type:4
                    // content:senderName-msg
                    // to:groupName
                    MessageVO messageVO = new MessageVO();
                    messageVO.setType(4);
                    messageVO.setContent(myName+"-"+str2Server);
                    messageVO.setTo(groupName);
                    try {
                        PrintStream out = new PrintStream(connect2Server.getOut(),
                                true,"UTF-8");
                        out.println(CommUtil.object2Json(messageVO));
                        System.out.println("客户端发送的群聊信息为:"+messageVO);
                        send2Server.setText("");
                    } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void readFromServer(String msg) {
        readFromServer.append(msg+"\n");
    }

    public JFrame getFrame() {
        return frame;
    }
}

