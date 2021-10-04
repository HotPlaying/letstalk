package com.main.letstalk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class UserGroupRelationKey implements Serializable {
    private int userId;
    private int groupId;

}
