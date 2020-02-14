package com.renxin.client.service;

import com.renxin.util.CommUtil;
import com.vo.MessageVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName: com.renxin.client.service
 * @ClassName: FriendList
 * @Description:
 * @author: 呆呆
 * @date: 2019/12/28
 */
public class FriendList {
    private JPanel friendListPanel;
    private JScrollPane friendPanel;
    private JButton createGroupBtn;
    private JScrollPane groupPanel;
    private String myName;
    private Connect2Server connect2Server;
    private Set<String> names;

    // 缓存所有在线好友
    private Set<String> users;
    // 缓存所有私聊界面
    private Map<String,PrivateChatGUI> privateChatGUIMap = new ConcurrentHashMap<>();
    // 缓存当前客户端的群聊信息（所有群名称以及群好友）
    private Map<String,Set<String>> groupInfo = new ConcurrentHashMap<>();
    // 缓存所有群聊界面
    private Map<String,GroupChatGUI> groupChatGUIMap = new ConcurrentHashMap<>();

    private class DaemonTask implements Runnable {
        private Scanner scanner = new Scanner(connect2Server.getIn());
        @Override
        public void run() {
            while (true) {
                if (scanner.hasNextLine()) {
                    String strFromServer = scanner.nextLine();
                    if (strFromServer.startsWith("newLogin:")){
                        // 好友上线提醒
                        String newFriend = strFromServer.split(":")[1];
                        JOptionPane.showMessageDialog(null,
                                newFriend+"上线了!","上线提醒",
                                JOptionPane.INFORMATION_MESSAGE);
                        names.add(newFriend);
                        // 再次刷新好友列表
                        reloadFriendList();
                    }
                    if (strFromServer.startsWith("{")) {
                        // 此时是个json串
                        MessageVO messageVOFromClient = (MessageVO) CommUtil.json2Object(strFromServer,MessageVO.class);
                        if (messageVOFromClient.getType().equals(2)) {
                            // 服务器发来的私聊信息
                            String senderName = messageVOFromClient.getContent().split("-")[0];
                            String msg = messageVOFromClient.getContent().split("-")[1];
                            // 判断此私聊是否是第一次创建
                            if (privateChatGUIMap.containsKey(senderName)) {
                                PrivateChatGUI privateChatGUI = privateChatGUIMap.get(senderName);
                                privateChatGUI.getFrame().setVisible(true);
                                privateChatGUI.readFromServer(senderName+"说:"+msg);
                            }else {
                                PrivateChatGUI privateChatGUI = new PrivateChatGUI(senderName,
                                        myName,connect2Server);
                                privateChatGUIMap.put(senderName,privateChatGUI);
                                privateChatGUI.readFromServer(senderName+"说:"+msg);
                            }
                        }
                        else if (messageVOFromClient.getType().equals(4)) {
                            // 收到服务器发来的群聊信息
                            // type:4
                            // content:sender-msg
                            // to:groupName-[1,2,3,...]
                            String groupName = messageVOFromClient.getTo().split("-")[0];
                            String senderName = messageVOFromClient.getContent().split("-")[0];
                            String groupMsg = messageVOFromClient.getContent().split("-")[1];
                            // 若此群名称在群聊列表
                            if (groupInfo.containsKey(groupName)) {
                                if (groupChatGUIMap.containsKey(groupName)) {
                                    // 群聊界面弹出
                                    GroupChatGUI groupChatGUI = groupChatGUIMap.get(groupName);
                                    groupChatGUI.getFrame().setVisible(true);
                                    groupChatGUI.readFromServer(senderName + "说:" + groupMsg);
                                } else {
                                    Set<String> names = groupInfo.get(groupName);
                                    GroupChatGUI groupChatGUI = new GroupChatGUI(groupName,
                                            names, connect2Server,myName);
                                    groupChatGUIMap.put(groupName, groupChatGUI);
                                    groupChatGUI.readFromServer(senderName + "说:" + groupMsg);
                                }
                            } else {
                                // 若群成员第一次收到群聊信息
                                // 1.将群名称以及群成员保存到当前客户端群聊列表
                                Set<String> friends = (Set<String>) CommUtil.json2Object(messageVOFromClient.getTo().split("-")[1],
                                        Set.class);
                                groupInfo.put(groupName, friends);
                                reloadGroupList();
                                // 2.弹出群聊界面
                                GroupChatGUI groupChatGUI = new GroupChatGUI(groupName,
                                        friends,connect2Server,myName);
                                groupChatGUIMap.put(groupName, groupChatGUI);
                                groupChatGUI.readFromServer(senderName + "说:" + groupMsg);
                            }
                        }
                    }
                }
            }
        }
    }

    // 私聊点击事件
    private class PrivateLabelAction implements MouseListener {
        // 标签名字
        private String labelName;

        public PrivateLabelAction(String labelName) {
            this.labelName = labelName;
        }

        // 鼠标点击执行事件
        @Override
        public void mouseClicked(MouseEvent e) {
            // 判断缓存中是否有指定的私聊界面
            if (privateChatGUIMap.containsKey(labelName)) {
                PrivateChatGUI privateChatGUI = privateChatGUIMap.get(labelName);
                privateChatGUI.getFrame().setVisible(true);
            }else {
                // 第一次点击，创建私聊界面
                PrivateChatGUI privateChatGUI = new PrivateChatGUI(labelName,myName,connect2Server);
                privateChatGUIMap.put(labelName,privateChatGUI);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    // 群聊点击事件
    private class GroupLabelAction implements MouseListener {
        private String groupName;

        public GroupLabelAction(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (groupChatGUIMap.containsKey(groupName)) {
                GroupChatGUI groupChatGUI = groupChatGUIMap.get(groupName);
                groupChatGUI.getFrame().setVisible(true);
            }else {
                Set<String> friends = groupInfo.get(groupName);
                GroupChatGUI groupChatGUI = new GroupChatGUI(groupName,friends,connect2Server,myName);
                groupChatGUIMap.put(groupName,groupChatGUI);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public FriendList(String myName,
                      Connect2Server connect2Server,
                      Set<String> names) {
        this.myName = myName;
        this.connect2Server = connect2Server;
        this.names = names;
        JFrame frame = new JFrame(myName);
        frame.setContentPane(friendListPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口大小
        frame.setSize(400,400);
        // 设置窗口居中显示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        reloadFriendList();
        // 新启动一个后台线程不断监听服务器发来的信息
        Thread daemonThread = new Thread(new DaemonTask());
        daemonThread.setDaemon(true);
        daemonThread.start();
        // 点击创建群组弹出创建界面
        createGroupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateGroupGUI(myName,names,connect2Server,FriendList.this);
            }
        });
    }

    // 标签动态显示
    public void reloadFriendList() {
        JPanel friendLabelPanel = new JPanel();
        JLabel[] labels = new JLabel [names.size()];
        // 迭代遍历set集合
        Iterator<String> iterator = names.iterator();
        // 设置标签为纵向对齐
        friendLabelPanel.setLayout(new BoxLayout(friendLabelPanel,
                BoxLayout.Y_AXIS));
        int i = 0;
        while (iterator.hasNext()) {
            String labelName = iterator.next();
            labels[i] = new JLabel(labelName);
            // 给每个标签附加按钮点击事件
            labels[i].addMouseListener(new PrivateLabelAction(labelName));
            friendLabelPanel.add(labels[i]);
            i++;
        }
        this.friendPanel.setViewportView(friendLabelPanel);
        // 设置滚动条为垂直滚动条
        this.friendPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.friendPanel.revalidate();
    }

    // 刷新群聊列表的群聊信息
    public void reloadGroupList() {
        // 存储所有群名称标签Jpanel
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        JLabel[] labels = new JLabel[groupInfo.size()];
        // 遍历群名
        Set<String> groupNames = groupInfo.keySet();
        Iterator<String> iterator = groupNames.iterator();
        while (iterator.hasNext()) {
            String groupName = iterator.next();
            JLabel label = new JLabel(groupName);
            label.addMouseListener(new GroupLabelAction(groupName));
            jPanel.add(label);
        }
        groupPanel.setViewportView(jPanel);
        groupPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        groupPanel.revalidate();
    }

    // 将新群加到群列表中
    public void addGroupInfo(String groupName,Set<String> friends) {
        groupInfo.put(groupName,friends);
    }
}
