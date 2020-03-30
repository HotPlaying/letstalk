package com.main.letstalk.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.main.letstalk.entity.Message;
import com.main.letstalk.entity.User;
import com.main.letstalk.entity.UserGroupRelation;
import com.main.letstalk.service.MessageService;
import com.main.letstalk.service.UserGroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ServerEndpoint(value = "/{userId}")
public class Server {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //在线人数列表
    private static Map<String, Server> onLineList = new HashMap<>();

    private Session session;

    //private MessageService messageService = (MessageService)ContextLoader.getCurrentWebApplicationContext().getBean("messageService");

    private static MessageService messageService;

    private static UserGroupRelationService uggr;

    @Autowired
    public void setServerMessageService(MessageService messageService) {
        Server.messageService = messageService;
    }
    @Autowired
    public void setServerUserGroupRelationService(UserGroupRelationService uggr) {
        Server.uggr = uggr;
    }


    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.session = session;
        onLineList.put(userId, this);     //加入map中
        addOnlineCount();                 //在线数加1
//        User currentUser = (User)httpSession.getAttribute("user");
        List<Message> offLineMessages = messageService.findOffLineMessages(Integer.parseInt(userId));
        //用户登陆后检查离线消息，存储到线性表，逐条发送到前端
        if (!offLineMessages.isEmpty()){
            for (Message m : offLineMessages) {
                sendMessage(m);
            }
        }
        System.out.println("有新连接 " + userId + " 加入！当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        onLineList.remove(userId);        //从map中删除
        subOnlineCount();                   //在线数减1
        System.out.println("有一连接" + userId + "关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String JsonMessage) throws IOException {
        Message message = JSON.parseObject(JsonMessage, Message.class);
        messageService.save(message);
        int messageType = message.getType();
        //类型为 0 表示一对一消息
        if (messageType == 0 || messageType == 2 || messageType == 3) {
            //不管收到什么，先发给用户自己，显示到用户界面
            sendMessage(message);
            //若对方在在线表中，发送给对方
            if (onLineList.containsKey(String.valueOf(message.getTo()))) {
                onLineList.get(String.valueOf(message.getTo())).sendMessage(message);
                System.out.println("Type 0 sent!");
            }
            //若对方不在线，则不作操作，将消息发送任务交给离线消息推送机制
        }
        else {
            //获取群成员列表
            List<UserGroupRelation> ugrList = uggr.findByGroupId(message.getTo());
            //对每个群成员推送消息
            for (UserGroupRelation ugr : ugrList) {
                //对在线的群成员即时推送消息
                if (onLineList.containsKey(String.valueOf(ugr.getUserId()))) {
                    onLineList.get(String.valueOf(message.getTo())).sendMessage(message);
                    System.out.println("Type 1 sent!");
                }
                //离线的群成员存储单独的群消息，用于离线消息推送
                else {
                    message.setType(3);
                    message.setFrom(ugr.getGroupId());
                    message.setTo(ugr.getUserId());
                    messageService.save(message);
                }
            }

        }
    }
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("error....");
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void send(Message message) throws IOException {
        this.session.getBasicRemote().sendText(JSONObject.toJSONString(message));
    }

    public void sendMessage(Message message) {
        try {
            send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message getMessageFromJson(String jsonMessage, Message message) {
        JSONObject jsonObjectMessage = JSON.parseObject(jsonMessage);
        message.setFrom(jsonObjectMessage.getIntValue("from"));
        message.setTo(jsonObjectMessage.getIntValue("to"));
        message.setContent(jsonObjectMessage.getString("content"));
        message.setType(jsonObjectMessage.getIntValue("type"));
        return message;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        Server.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        Server.onlineCount--;
    }
}
