package com.trd.letstalk.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.trd.letstalk.entity.User;
import com.trd.letstalk.entity.UserRelation;
import com.trd.letstalk.service.UserRelationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserRelationController {
    @Resource
    private UserRelationService userRelationService;

    @PostMapping("/getFriends")
    @ResponseBody
    public Map<String, Object> getFriendList(HttpSession session) {
        User user = (User)session.getAttribute("user");
        List<User> userList = userRelationService.findByUserId(user.getUserId());
        String friendList = JSONArray.toJSONString(userList);
        Map<String, Object> friends = new HashMap<>();
        friends.put("friendList", friendList);
        return friends;
    }

    @PostMapping("/addFriend")
    @ResponseBody
    public String addFriend(int ida, int idb) {
        //User user = (User) httpSession.getAttribute("user");
        UserRelation userRelation = new UserRelation(ida, idb, 0);
        System.out.println(JSONObject.toJSONString(userRelation));
        userRelationService.save(userRelation);
        return "success";
    }
}
