package com.main.letstalk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_group_relation")
@IdClass(UserGroupRelationKey.class)
public class UserGroupRelation implements Serializable {
    public UserGroupRelation(){}

    public UserGroupRelation(int userId, int groupId, LocalDateTime userEnTime) {
        this.userId = userId;
        this.groupId = groupId;
        this.userEnTime = userEnTime;
    }

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "user_gname")
    private String userGName;

    @Column(name = "user_entime")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime userEnTime;

}
