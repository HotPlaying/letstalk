package com.main.letstalk.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.main.letstalk.component.RequestLogAspect;
import com.main.letstalk.entity.Message;
import com.main.letstalk.entity.UserGroupRelation;
import com.main.letstalk.service.MessageService;
import com.main.letstalk.service.UserGroupRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/{userId}")
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);;
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //在线人数列表,设计为线程安全的
    private static final Map<String, Server> onLineList = new ConcurrentHashMap<>();

    private Session session;    // 用于即时通讯

    //另一种注入Service层的方法，然而没能注入。
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


    /**
     * 用户登陆后检查离线消息，存储到线性表，逐条发送到前端
     *
     * @param userId  登录用户的编号
     * @param session 获取用户前端独一的会话session，用于即时通讯
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.session = session;
        onLineList.put(userId, this);     //加入map中
        addOnlineCount();                 //在线数加1

        //获取用户所有未查看的消息
        List<Message> offLineMessages =
                messageService.findOffLineMessages(Integer.parseInt(userId));
        // 逐条发送至前端
        if (!CollectionUtils.isEmpty(offLineMessages)) {
            for (Message m : offLineMessages) {
                sendMessage(m);
            }
        }
        logger.info(LocalDateTime.now() + "---有新连接 " +
                userId + " 加入！当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        onLineList.remove(userId);        //从map中删除
        subOnlineCount();                   //在线数减1
        logger.info(LocalDateTime.now() + "---有一连接 " +
                userId + " 关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String JsonMessage) {
        Message message = JSON.parseObject(JsonMessage, Message.class);
        int messageType = message.getType();
        //类型为 0 表示一对一消息
        if (messageType == 0 || messageType == 2) {
            messageService.save(message);
            if (messageType == 0)//一对一消息，先发给用户自己，显示到用户界面
                sendMessage(message);
            //若对方在在线表中，发送给对方
            if (onLineList.containsKey(String.valueOf(message.getTo()))) {
                onLineList.get(String.valueOf(message.getTo())).sendMessage(message);
            }
            //若对方不在线，则不作操作，将消息发送任务交给离线消息推送机制
        } else {    //类型为 1 则表示群消息
            //不管如何先把接收状态调整为1，尽可能避免会出现的推送问题
            message.setIsReceived(1);
            messageService.save(message);
            //获取群成员列表
            System.out.println(JSON.toJSON(message));
            List<UserGroupRelation> ugrList = uggr.findByGroupId(message.getTo());
            //对每个群成员推送消息
            for (UserGroupRelation ugr : ugrList) {
                //对在线的群成员即时推送消息
                String userId = String.valueOf(ugr.getUserId());
                if (onLineList.containsKey(userId)) {
                    onLineList.get(userId).sendMessage(message);
                }
                //离线的群成员存储单独的群消息，用于离线消息推送
                else {
                    Message m = Message.builder()
                            .from(message.getTo())
                            .to(Integer.parseInt(userId))
                            .type(3)
                            .content("GROUP NOTICE")
                            .build();
                    messageService.save(m);
                }
            }
        }
    }

//    @OnError
//    public void onError(Session session, Throwable throwable){
//        System.out.println("error....");
//    }

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


    /*
        本来想把下面几个方法一起封装到一个类中类里，不知道为什么
        就没办法创建serverEndPoint bean了，索性保持原样
     */
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
