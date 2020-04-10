package com.main.letstalk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "from_id")
    private int from;

    @Column(name = "to_id")
    private int to;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private int type;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "is_received")
    private int isReceived;

    public Message() {}
    public Message(int from, int to, int type, String content) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(int isReceived) {
        this.isReceived = isReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                from == message.from &&
                to == message.to &&
                type == message.type &&
                isReceived == message.isReceived &&
                Objects.equals(content, message.content) &&
                Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, content, type, time, isReceived);
    }
}
