package com.main.letstalk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_Psw")
    private String userPsw;

    @Column(name = "user_regtime")
    private LocalDate userRegtime;

    @Column(name = "user_is_ol")
    private int userIsOl;

    @Column(name = "user_role")
    private int userRole;

}
