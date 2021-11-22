package com.trd.letstalk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @Column(name = "is_received")
    private int isReceived;

}
