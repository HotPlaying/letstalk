package com.main.letstalk.service.impl;

import com.main.letstalk.entity.Message;
import com.main.letstalk.repository.MessageRepository;
import com.main.letstalk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        if (message.getTime() == null) {
            message.setTime(LocalDateTime.now());
            message.setIsReceived(0);
        }
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findByFrom(int from) {
        return messageRepository.findByFrom(from);
    }

    @Override
    public List<Message> findGroupType(int groupId) {
        return messageRepository.findByToAndType(groupId, 1);
    }

    @Override
    public List<Message> findByType(int type) {
        return messageRepository.findByType(type);
    }

    @Override
    public List<Message> findOffLineMessages(int toId) {
        return messageRepository.findOffLineMessages(toId);
    }

    @Override
    public void deleteGroupMessageReceived(int groupId, int userId) {
        messageRepository.deleteMessagesByTypeAndFromAndTo(3, groupId, userId);
    }

    @Override
    public List<Message> findByFromAndTypeAndTo(int from, int to, int type) {
        return messageRepository.findByFromAndToAndType(from, to, type);
    }

    @Override
    public void saveAll(List<Message> messages) {
        messageRepository.saveAll(messages);
    }

}
