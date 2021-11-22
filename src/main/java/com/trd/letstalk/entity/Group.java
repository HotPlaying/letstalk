package com.trd.letstalk.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "group_detail")
public class Group {
    @Id
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "created_time")
    private LocalDate createdTime;

    @Column(name = "group_intro")
    private String groupIntro;

    @Column(name = "members_count")
    private int membersCount;

    @Column(name = "group_members")
    private String groupMembers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getGroupId() {
        return groupId;
    }
}
