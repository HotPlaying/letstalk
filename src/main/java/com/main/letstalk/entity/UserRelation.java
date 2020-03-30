package com.main.letstalk.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_relation")
@IdClass(UserRelationKey.class)
public class UserRelation implements Serializable {
    @Id
    @Column(name = "user_id_a")
    private int userIdA;

    @Id
    @Column(name = "user_id_b")
    private int userIdB;

    @Column(name = "relation_status")
    private int relationStatus;

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

    public int getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(int relationStatus) {
        this.relationStatus = relationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRelation that = (UserRelation) o;
        return userIdA == that.userIdA &&
                userIdB == that.userIdB &&
                relationStatus == that.relationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIdA, userIdB, relationStatus);
    }
}
