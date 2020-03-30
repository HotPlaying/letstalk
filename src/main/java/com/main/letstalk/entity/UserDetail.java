package com.main.letstalk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class UserDetail {
    @Id
    @Column(name = "user_id")
    private int userId;

//    private String userAvatar;

    @Column(name = "user_addr")
    private String userAddr;

    public enum Gender {
        Male, Female;   //枚举中常量结束位置要有分号
        public static List<String> toList() {
            Gender[] gender = Gender.values();
            List<String> datas = new ArrayList<>(); //定义一个列表容纳所有枚举数据
            for (Gender s : gender) {
                datas.add(s.name());
            }
            return datas;
        }
    }

    @Column(name = "user_gender")
    private Gender userGender;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_intro")
    private String userIntro;

    public UserDetail(){}


    public UserDetail(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(Gender userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetail that = (UserDetail) o;
        return userId == that.userId &&
                Objects.equals(userAddr, that.userAddr) &&
                userGender == that.userGender &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userIntro, that.userIntro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userAddr, userGender, userEmail, userIntro);
    }
}
