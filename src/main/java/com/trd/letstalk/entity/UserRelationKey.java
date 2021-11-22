package com.trd.letstalk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserRelationKey implements Serializable {
    private int userIdA;
    private int userIdB;

}
