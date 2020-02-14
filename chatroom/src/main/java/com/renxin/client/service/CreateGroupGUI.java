package com.renxin.client.service;

import com.renxin.util.CommUtil;
import com.vo.MessageVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @PackageName: com.renxin.client.service
 * @ClassName: CreateGroupGUI
 * @Description:
 * @author: 呆呆
 * @date: 2020/1/1
 */
public class CreateGroupGUI {
    private JPanel createGroupPanel;
    private JPanel checkBokPanel;
    private JTextField groupNameText;
    private JButton confromBtn;

    private String myName;
    private Set<String> friends;
    private Connect2Server connect2Server;
    private FriendList friendsList;

    public CreateGroupGUI(String myName, Set<String> friends,
                          Connect2Server connect2Server, FriendList friendsList) {
        this.myName = myName;
        this.friends = friends;
        this.connect2Server = connect2Server;
        this.friendsList = friendsList;
        JFrame frame = new JFrame("创建群组");
        frame.setContentPane(createGroupPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        //居中显示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // 1.动态的添加checkBox，长度由friends决定
        // 设置布局，让它垂直展示
        checkBokPanel.setLayout(new BoxLayout(checkBokPanel,BoxLayout.Y_AXIS));
        Iterator<String> iterator = friends.iterator();
        while (iterator.hasNext()) {
            String labelName = iterator.next();
            JCheckBox checkBox = new JCheckBox(labelName);
            checkBokPanel.add(checkBox);
        }
        //生效
        checkBokPanel.revalidate();
        // 2.提交信息按键，点击提交按钮提交信息到服务端
        confromBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. 获取群名输入框输入的群名称
                String groupName = groupNameText.getText();
                // 2.判断哪些好友选中加入群聊，获取选中的好友名称（多个）
                Set<String> selectedFriends = new HashSet<>();
                // 3.获取checkBoxPanel下的所有组件
                Component[] compoments = checkBokPanel.getComponents();
                for (Component compoment : compoments) {
                    // 向下转型
                    JCheckBox checkBox = (JCheckBox) compoment;
                    if (checkBox.isSelected()) {
                        String labelName = checkBox.getText();
                        selectedFriends.add(labelName);
                    }
                }
                // 将自己放入群聊中
                selectedFriends.add(myName);
                // 4.将群名 和 选中好友信息发送到服务端
                // type:3  注册群
                // content: groupName
                // to:[user1,user2,user3...]
                MessageVO messageVO = new MessageVO();
                messageVO.setType(3);
                messageVO.setContent(groupName);
                messageVO.setTo(CommUtil.object2Json(selectedFriends));
                try {
                    PrintStream out = new PrintStream(connect2Server.getOut(),
                            true,"UTF-8");
                    out.println(CommUtil.object2Json(messageVO));
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(false);
                // 5.将当前创建群界面隐藏,刷新好友列表界面，显示群列表
                friendsList.addGroupInfo(groupName,selectedFriends);
                friendsList.reloadGroupList();
            }
        });
    }
}
