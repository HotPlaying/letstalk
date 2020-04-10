package com.main.letstalk.repository;

import com.main.letstalk.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    public List<Message> findByFrom(int from);

    public List<Message> findByToAndType(int to, int type);

    public List<Message> findByType(int type);

    @Query("select  m from Message m where to = ?1 and isReceived = 0")
    public List<Message> findOffLineMessages(int toId);

    public void deleteAllByTypeAndFromAndTo(int type, int from, int to);
}
