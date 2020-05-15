package com.ifind.controller;

import com.ifind.common.response.JsonResult;
import com.ifind.entity.User;
import com.ifind.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param rUser                     用户信息
     * @param httpSession               session信息
     * @return
     */
    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody User rUser, HttpSession httpSession) {
        User user = userService.getUser(rUser);
        if (user == null) {
            return JsonResult.buildBizError("-40000", "用户名或密码错误");
        }
        httpSession.setAttribute("user", user);
        return new JsonResult(user);
    }

    /**
     * 登出
     *
     * @param httpSession            session信息
     * @return
     */
    @CrossOrigin
    @RequestMapping("/logout")
    @ResponseBody
    public JsonResult loginOut(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return JsonResult.buildSuccess("成功退出");
    }

    /**
     * 注册
     *
     * @param rUser                 用户信息
     * @return
     */
    @CrossOrigin
    @RequestMapping("/register")
    @ResponseBody
    public JsonResult register(@RequestBody User rUser) {
        User user = userService.getUser(rUser);
        if (user != null) {
            return JsonResult.buildBizError("-40000", "用户已存在！");
        }
        int count = userService.insert(rUser);
        if (count > 0) {
            return JsonResult.buildSuccess();
        } else {
            return JsonResult.buildBizError("-40000", "注册失败！");
        }

    }

}
