package com.main.letstalk.service;

import com.main.letstalk.entity.Message;

import java.util.List;

public interface MessageService {
    public void save(Message message);

    public List<Message> findByFrom(int from);

    public List<Message> findGroupType(int groupId);

    public List<Message> findByType(int type);

    public List<Message> findOffLineMessages(int to);

    public void deleteGroupMessageReceived(int groupId, int userId);
}
