package com.main.letstalk.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRelationKey implements Serializable {
    private int userIdA;
    private int userIdB;

    public int getUserIdA() {
        return userIdA;
    }

    public void setUserIdA(int userIdA) {
        this.userIdA = userIdA;
    }

    public int getUserIdB() {
        return userIdB;
    }

    public void setUserIdB(int userIdB) {
        this.userIdB = userIdB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRelationKey that = (UserRelationKey) o;
        return userIdA == that.userIdA &&
                userIdB == that.userIdB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIdA, userIdB);
    }
}
