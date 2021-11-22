package com.trd.letstalk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
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

    public UserRelation(int userIdA, int userIdB, int relationStatus) {
        this.userIdA = userIdA;
        this.userIdB = userIdB;
        this.relationStatus = relationStatus;
    }

}
