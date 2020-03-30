package com.main.letstalk.controller;

import com.alibaba.fastjson.JSONObject;
import com.main.letstalk.entity.User;
import com.main.letstalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/getCurrentUserId")
    @ResponseBody
    public String getCurrentUserId(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return JSONObject.toJSONString(user.getUserId());
    }

    @PostMapping("/getCurrentUser")
    @ResponseBody
    public String getCurrentUser(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return JSONObject.toJSONString(user);
    }

    @PostMapping("/getUserName")
    @ResponseBody
    public String getUserNameById(int userId) {
        return userService.findByUserId(userId).getUserName();
    }
}
