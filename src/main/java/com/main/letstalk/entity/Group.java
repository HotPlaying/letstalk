package com.main.letstalk.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDate getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDate createdTime) {
        this.createdTime = createdTime;
    }

    public String getGroupIntro() {
        return groupIntro;
    }

    public void setGroupIntro(String groupIntro) {
        this.groupIntro = groupIntro;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }

    public String getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(String groupMembers) {
        this.groupMembers = groupMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId &&
                creatorId == group.creatorId &&
                membersCount == group.membersCount &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(createdTime, group.createdTime) &&
                Objects.equals(groupIntro, group.groupIntro) &&
                Objects.equals(groupMembers, group.groupMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, creatorId, createdTime, groupIntro, membersCount, groupMembers);
    }
}
