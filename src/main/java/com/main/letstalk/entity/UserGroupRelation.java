package com.main.letstalk.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private LocalDateTime userEnTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUserGName() {
        return userGName;
    }

    public void setUserGName(String userGName) {
        this.userGName = userGName;
    }

    public LocalDateTime getUserEnTime() {
        return userEnTime;
    }

    public void setUserEnTime(LocalDateTime userEnTime) {
        this.userEnTime = userEnTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupRelation that = (UserGroupRelation) o;
        return userId == that.userId &&
                groupId == that.groupId &&
                Objects.equals(userGName, that.userGName) &&
                Objects.equals(userEnTime, that.userEnTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId, userGName, userEnTime);
    }
}
