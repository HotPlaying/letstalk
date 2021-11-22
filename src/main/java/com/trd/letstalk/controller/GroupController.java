package com.trd.letstalk.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trd.letstalk.entity.Group;
import com.trd.letstalk.entity.User;
import com.trd.letstalk.entity.UserGroupRelation;
import com.trd.letstalk.service.GroupService;
import com.trd.letstalk.service.UserGroupRelationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@RestController
public class GroupController {
    @Resource
    private UserGroupRelationService ugrs;
    @Resource
    private GroupService groupService;

    @PostMapping("/saveGroup")
    public String saveGroup(String jsonGroup) {
        Group group = JSONObject.parseObject(jsonGroup, Group.class);
        group = groupService.save(group);
        System.out.println(JSONObject.toJSONString(group));
        ugrs.save(new UserGroupRelation(group.getCreatorId(), group.getGroupId(), LocalDateTime.now()));
        return JSONObject.toJSONString(group);
    }

    @PostMapping("/joinGroup")
    public String joinGroup(int userId, int groupId) {
        if (userId != 0) {
            Group group = groupService.findByGroupId(groupId);
            StringJoiner sj = new StringJoiner(",");
            group.setGroupMembers(sj.add(group.getGroupMembers()).add(String.valueOf(userId)).toString());
            group.setMembersCount(group.getMembersCount() + 1);
            groupService.save(group);
            ugrs.save(new UserGroupRelation(userId, groupId, LocalDateTime.now()));
            return "success";
        }
        return "fail";
    }

    @PostMapping("/getGroupsByUserId")
    public Map<String, Object> getGroupsByUserId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Group> groupList = ugrs.findByUserId(user.getUserId());
        String jsonGroupList = JSONArray.toJSONString(groupList);
        Map<String, Object> groups = new HashMap<>();
        groups.put("groupList", jsonGroupList);
        return groups;
    }

    @PostMapping("getGroupByGroupId")
    public String getGroupByGroupId(int groupId) {
        Group group = groupService.findByGroupId(groupId);
        return JSONObject.toJSONString(group);
    }

}
