package com.main.letstalk.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserGroupRelationKey implements Serializable {
    private int userId;
    private int groupId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupRelationKey that = (UserGroupRelationKey) o;
        return userId == that.userId &&
                groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
}
