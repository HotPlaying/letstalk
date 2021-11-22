package com.trd.letstalk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserGroupRelationKey implements Serializable {
    private int userId;
    private int groupId;
}
