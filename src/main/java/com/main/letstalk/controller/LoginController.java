package com.main.letstalk.controller;


import com.main.letstalk.entity.User;
import com.main.letstalk.entity.UserDetail;
import com.main.letstalk.service.UserDetailService;
import com.main.letstalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private UserDetailService userDetailService;

    @GetMapping({"/login"})
    public String getLoginModel(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String getNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, RedirectAttributes attr)  {
        user = userService.checkUser(user);
        if (user == null) {
            attr.addFlashAttribute("fail", "账号或密码不正确");
            return "redirect:/login";
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
        return "login";
    }

    @GetMapping("/exit")
    public String exit(HttpSession session) {  //退出登录
        session.removeAttribute("user");
        return "login";
    }

}
