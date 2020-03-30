package com.main.letstalk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public LocalDate getUserRegtime() {
        return userRegtime;
    }

    public void setUserRegtime(LocalDate userRegtime) {
        this.userRegtime = userRegtime;
    }

    public int getUserIsOl() {
        return userIsOl;
    }

    public void setUserIsOl(int userIsOl) {
        this.userIsOl = userIsOl;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                userIsOl == user.userIsOl &&
                userRole == user.userRole &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPsw, user.userPsw) &&
                Objects.equals(userRegtime, user.userRegtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPsw, userRegtime, userIsOl, userRole);
    }
}
