package com.main.letstalk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class UserRelationKey implements Serializable {
    private int userIdA;
    private int userIdB;

}
