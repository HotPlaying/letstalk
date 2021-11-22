package com.trd.letstalk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_group_relation")
@IdClass(UserGroupRelationKey.class)
public class UserGroupRelation implements Serializable {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Id
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "user_gname")
    private String userGName;
    @Column(name = "user_entime")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime userEnTime;

    public UserGroupRelation(int userId, int groupId, LocalDateTime userEnTime) {
        this.userId = userId;
        this.groupId = groupId;
        this.userEnTime = userEnTime;
    }

}
