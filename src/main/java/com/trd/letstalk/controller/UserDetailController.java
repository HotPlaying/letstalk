package com.trd.letstalk.controller;

import com.alibaba.fastjson.JSONObject;
import com.trd.letstalk.entity.UserDetail;
import com.trd.letstalk.service.UserDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserDetailController {
    @Resource
    private UserDetailService userDetailService;

    @PostMapping("/getUserDetailByUserId")
    @ResponseBody
    public String getUserDetailByUserId(int userId) {
        UserDetail userDetail = userDetailService.findByUserId(userId);
        return JSONObject.toJSONString(userDetail);
    }

    @PostMapping("/saveUserDetail")
    @ResponseBody
    public String saveUserDetail(String jsonUserDetail) {
        System.out.println("save:  "+jsonUserDetail);
        UserDetail userDetail = JSONObject.parseObject(jsonUserDetail, UserDetail.class);
        System.out.println("save1::  "+userDetail.getUserId());
        userDetailService.save(userDetail);
        return "success";
    }
}
