package com.main.letstalk.service;

import com.main.letstalk.entity.Message;

import java.util.List;

public interface MessageService {
    public void save(Message message);

    public List<Message> findByFrom(int from);

    public List<Message> findGroupType(int groupId);

    public List<Message> findByType(int type);

    /**
     * 获取所有类型的离线消息
     * @param to 接收者编号
     * @return 所有接收者编号和接收状态为0的消息
     */
    public List<Message> findOffLineMessages(int to);

    public void deleteGroupMessageReceived(int groupId, int userId);

    public List<Message> findByFromAndTypeAndTo(int from, int to, int type);

    public void saveAll(List<Message> messages);
}
