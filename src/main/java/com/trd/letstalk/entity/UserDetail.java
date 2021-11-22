package com.trd.letstalk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "user_detail")
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

    public UserDetail(int userId) {
        this.userId = userId;
    }

}
