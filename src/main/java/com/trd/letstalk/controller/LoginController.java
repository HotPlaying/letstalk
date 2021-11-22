package com.trd.letstalk.controller;


import com.trd.letstalk.entity.User;
import com.trd.letstalk.entity.UserDetail;
import com.trd.letstalk.service.UserDetailService;
import com.trd.letstalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private UserDetailService userDetailService;

    @GetMapping({"/", "/LetsTalk"})
    public String getLoginModel(Model model) {
        model.addAttribute("user", new User());
        return "LetsTalk";
    }

    @GetMapping("/register")
    public String getNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/LetsTalk")
    public String login(Model model, User user, HttpSession session)  {
        user = userService.checkUser(user);
        if (user == null) {
            return "redirect:/LetsTalk";
        }
        else {
            session.setAttribute("user", user);
            return "main";
        }
    }

    @PostMapping("/save")
    public String register(User user) {
        userService.save(user);
        user = userService.checkUser(user);
        //为新用户录入个人详细信息记录
        UserDetail userDetail = new UserDetail(user.getUserId());
        userDetailService.save(userDetail);
        return "LetsTalk";
    }

    @GetMapping("/exit")
    public String exit(HttpSession session) {  //退出登录
        session.removeAttribute("user");
        return "redirect:/LetsTalk";
    }

}
