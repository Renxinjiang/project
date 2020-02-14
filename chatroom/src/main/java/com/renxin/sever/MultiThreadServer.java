package com.renxin.sever;

import com.renxin.util.CommUtil;
import com.vo.MessageVO;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @PackageName: com.renxin.sever
 * @ClassName: MultiThreadServer
 * @Description:
 * 支持多线程的服务端
 * @author: 呆呆
 * @date: 2019/12/28
 */
public class MultiThreadServer {
    //从资源文件中获取PORT
    private static final Integer PORT;

    static {
        Properties pros = CommUtil.loadProperties("socket.properties");
        PORT = Integer.valueOf(pros.getProperty("PORT"));
    }

    // 服务端缓存所有连接的客户端对象
    private static Map<String, Socket> clients = new ConcurrentHashMap<>();  //线程安全的集合，如果有多个用户名重复的客户端与服务端进行连接，先来先put
    // 服务器缓存所有的群名称以及群中成员姓名
    private static Map<String,Set<String>> groupInfo = new ConcurrentHashMap<>();
    // 服务端具体处理客户端请求的任务
    private static class ExecuteClient implements Runnable {
        private Socket client;
        private Scanner in;
        private PrintStream out;

        public ExecuteClient(Socket client) {
            this.client = client;
            try {
                this.in = new Scanner(client.getInputStream());
                this.out = new PrintStream(client.getOutputStream(), true, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                if (in.hasNextLine()) {
                    String strFromClient = in.nextLine();
                    MessageVO msgFromClient = (MessageVO) CommUtil.json2Object(strFromClient,
                            MessageVO.class);
                    if (msgFromClient.getType().equals(1)) {
                        // 新用户注册
                        String userName = msgFromClient.getContent();
                        // 将当前聊天室在线好友信息发回给新用户
                        Set<String> names = clients.keySet();
                        MessageVO msg2Client = new MessageVO();
                        msg2Client.setType(1);
                        msg2Client.setContent(CommUtil.object2Json(names));
                        out.println(CommUtil.object2Json(msg2Client));
                        // 将新用户上线信息发给其他在线用户
                        String loginMsg = "newLogin:" + userName;
                        for (Socket socket : clients.values()) {
                            try {
                                PrintStream out = new PrintStream(socket.getOutputStream(),
                                        true, "UTF-8");
                                out.println(loginMsg);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        // 将新用户信息保存到当前服务端缓存
                        System.out.println(userName + "上线了!");
                        clients.put(userName, client);
                        System.out.println("当前聊天室在线人数为:" + clients.size());
                    } else if (msgFromClient.getType().equals(2)) {
                        // 用户私聊
                        // type:2
                        //  Content:myName-msg
                        //  to:friendName
                        String friendName = msgFromClient.getTo();
                        Socket socket = clients.get(friendName);
                        try {
                            PrintStream out = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                            MessageVO msg2Client = new MessageVO();
                            msg2Client.setType(2);
                            msg2Client.setContent(msgFromClient.getContent());
                            System.out.println("收到私聊信息,内容为" + msgFromClient.getContent());
                            out.println(CommUtil.object2Json(msg2Client));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (msgFromClient.getType().equals(3)) {
                        // 注册群信息
                        String groupName = msgFromClient.getContent();
                        // 该群的所有群成员
                        Set<String> friends = (Set<String>) CommUtil.json2Object(
                                msgFromClient.getTo(),Set.class);
                        groupInfo.put(groupName,friends);
                        System.out.println("注册群成功,群名称为"+
                                groupName+",当前共有"+groupInfo.size() + "个群");
                    }else if (msgFromClient.getType().equals(4)) {
                        // 群聊信息
                        // type:4
                        // content:senderName-msg
                        // to:groupName
                        System.out.println("服务器收到的群聊信息为:"+msgFromClient);
                        String groupName = msgFromClient.getTo();
                        Set<String> friends = groupInfo.get(groupName);
                        // 将群聊信息转发到相应的客户端
                        Iterator<String> iterator = friends.iterator();
                        while (iterator.hasNext()) {
                            String clientName = iterator.next();
                            Socket client = clients.get(clientName);
                            try {
                                PrintStream out = new PrintStream(client.getOutputStream(),
                                        true,"UTF-8");
                                // type:4
                                // content:senderName-msg
                                // to:groupName-[群好友列表]
                                MessageVO messageVO = new MessageVO();
                                messageVO.setType(4);
                                messageVO.setContent(msgFromClient.getContent());
                                // 群名-[]
                                messageVO.setTo(groupInfo+"-"+CommUtil.object2Json(friends));
                                out.println(CommUtil.object2Json(messageVO));
                                System.out.println("服务端发送的群聊信息为:"+messageVO);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }


        public static void main(String[] args) throws IOException {
            // 1.建立基站
            ServerSocket server = new ServerSocket(PORT);  //传入需要绑定的本地端口号，在客户端也有，属于配置类信息
            // 2.新建固定大小的线程池，支持多个客户端连接
            ExecutorService executors = Executors.newFixedThreadPool(50);
            for (int i = 0; i < 50; i++) {
                System.out.println("等待客户端连接...");
                Socket client = server.accept();
                System.out.println("有新的连接，端口号为:" + client.getPort());
                executors.submit(new ExecuteClient(client));
            }
        }
    }
}
