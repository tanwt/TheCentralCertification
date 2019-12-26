package com.jike.certification.controller;

import com.jike.certification.model.Response;
import com.jike.certification.model.User;
import com.jike.certification.service.UserService;
import com.jike.certification.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wentong
 * @date 2019-12-26
 */
@RestController
@RequestMapping(path = "api/pc/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public Response<Long> addUser(@RequestBody User user){
        return ResponseUtil.makeSuccess(userService.addUser(user));
    }
}
