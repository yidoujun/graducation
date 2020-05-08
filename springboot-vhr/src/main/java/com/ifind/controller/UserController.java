package com.ifind.controller;

import com.ifind.common.response.JsonResult;
import com.ifind.entity.User;
import com.ifind.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping("/test")
    @ResponseBody
    public JsonResult test() {
        Long id = 1L;
        User user = userService.getUser(id);
        try {
            return new JsonResult(user);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return JsonResult.buildBizError("-4000", "失败");
        }

    }

}
