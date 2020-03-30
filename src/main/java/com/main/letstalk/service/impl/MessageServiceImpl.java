package com.main.letstalk.service.impl;

import com.main.letstalk.entity.Message;
import com.main.letstalk.repository.MessageRepository;
import com.main.letstalk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        if (message.getTime() == null) {
            message.setTime(LocalDateTime.now());
            message.setIsReceived(0);
        }
        messageRepository.save(message);
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
}
