package com.main.letstalk.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.main.letstalk.entity.Group;
import com.main.letstalk.entity.User;
import com.main.letstalk.entity.UserGroupRelation;
import com.main.letstalk.service.GroupService;
import com.main.letstalk.service.UserGroupRelationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Controller
public class GroupController {
    @Resource
    private UserGroupRelationService ugrs;
    @Resource
    private GroupService groupService;

    @PostMapping("/saveGroup")
    @ResponseBody
    public String saveGroup(String jsonGroup) {
        Group group = JSONObject.parseObject(jsonGroup, Group.class);
        group = groupService.save(group);
        System.out.println(JSONObject.toJSONString(group));
        ugrs.save(new UserGroupRelation(group.getCreatorId(), group.getGroupId(), LocalDateTime.now()));
        return JSONObject.toJSONString(group);
    }

    @PostMapping("/joinGroup")
    @ResponseBody
    public String joinGroup(int userId, int groupId) {
        if (userId != 0) {
            Group group = groupService.findByGroupId(groupId);
            StringJoiner sj = new StringJoiner(",");
            group.setGroupMembers(sj.add(group.getGroupMembers()).add(String.valueOf(userId)).toString());
            group.setMembersCount(group.getMembersCount()+1);
            groupService.save(group);
            ugrs.save(new UserGroupRelation(userId, groupId, LocalDateTime.now()));
            return "success";
        } return "fail";
    }

    @PostMapping("/getGroupsByUserId")
    @ResponseBody
    public Map<String, Object> getGroupsByUserId(HttpSession session) {
        User user = (User)session.getAttribute("user");
        List<Group> groupList = ugrs.findByUserId(user.getUserId());
        String jsonGroupList = JSONArray.toJSONString(groupList);
        Map<String, Object> groups = new HashMap<>();
        groups.put("groupList", jsonGroupList);
        return groups;
    }

    @PostMapping("getGroupByGroupId")
    @ResponseBody
    public String getGroupByGroupId(int groupId) {
        Group group = groupService.findByGroupId(groupId);
        return JSONObject.toJSONString(group);
    }

}
