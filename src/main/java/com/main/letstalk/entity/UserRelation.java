package com.main.letstalk.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_relation")
@IdClass(UserRelationKey.class)
public class UserRelation implements Serializable {
    public UserRelation(){}
    public UserRelation(int userIdA, int userIdB, int relationStatus) {
        this.userIdA = userIdA;
        this.userIdB = userIdB;
        this.relationStatus = relationStatus;
    }

    @Id
    @Column(name = "user_id_a")
    private int userIdA;

    @Id
    @Column(name = "user_id_b")
    private int userIdB;

    @Column(name = "relation_status")
    private int relationStatus;

}
