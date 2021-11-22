package com.trd.letstalk.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trd.letstalk.entity.Message;
import com.trd.letstalk.service.MessageService;
import com.trd.letstalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Resource
    private MessageService messageService;
    @Resource
    private UserService userService;


    @PostMapping("/getMessageRecord")
    @ResponseBody
    public Map<String, Object> getMessageRecord(int ida, int idb) {
        List<Message> messages = messageService.findByType(0);
        List<Message> messageList = new ArrayList<>();
        for (Message m : messages) {
            if ((m.getFrom() == ida && m.getTo() == idb) || (m.getTo() == ida && m.getFrom() == idb))
                messageList.add(m);
        }
        String messageListString = JSONArray.toJSONString(messageList);
        Map<String, Object> record = new HashMap<>();
        record.put("record", messageListString);
        return record;
    }

    @PostMapping("/setReceived")
    @ResponseBody
    public void setReceived(String jsonMessage) {
        Message message = JSONObject.parseObject(jsonMessage, Message.class);
        message.setIsReceived(1);
        messageService.save(message);
    }

    @PostMapping("/getGroupMessageRecord")
    @ResponseBody
    public Map<String, Object> getGroupMessageRecord(int groupId) {
        List<Message> messages = messageService.findGroupType(groupId);
        List<String> usernames = new ArrayList<>();
        for (Message m : messages) {
            usernames.add(userService.findByUserId(m.getFrom()).getUserName());
        }
        String jsonMessages = JSONArray.toJSONString(messages);
        String jsonUsernames = JSONArray.toJSONString(usernames);
        Map<String, Object> record = new HashMap<>();
        record.put("messages", jsonMessages);
        record.put("usernames", jsonUsernames);
        return record;
    }

    @PostMapping("/cleanGroupReceived")
    @ResponseBody
    public void cleanGroupReceived(int groupId, int userId) {
        List<Message> messages = messageService.findByFromAndTypeAndTo(groupId,userId,3);
        for (Message m : messages) {
            m.setIsReceived(1);
            messageService.save(m);
        }
    }
}
