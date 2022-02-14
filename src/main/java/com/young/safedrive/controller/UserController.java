package com.young.safedrive.controller;

import com.young.safedrive.model.UserModel;
import com.young.safedrive.response.BaseResponse;
import com.young.safedrive.serviceImpl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ Author : autumn
 * @ Date   : created in 17:48 2022/2/10
 */

@RestController
@Scope("prototype")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public BaseResponse<Integer> login(@RequestParam("account") String account, @RequestParam("password") String password){
        return userServiceImpl.login(account, password);
    }

    @PostMapping("/register")
    public BaseResponse<Integer> register(@RequestParam("username") String username,@RequestParam("account") String account,@RequestParam("password") String password){
        return userServiceImpl.register(username, account, password);
    }

    @GetMapping("/getSelfInfo")
    public BaseResponse<UserModel> getSelfInfo(@RequestParam("account") String account){
        return userServiceImpl.getSelfInfo(account);
    }

    @PostMapping("/updateSelfInfo")
    public BaseResponse<Integer> updateSelfInfo(@RequestParam("picture") MultipartFile[] picture,
                                                @RequestParam("username") String username,
                                                @RequestParam("account") String account) throws IOException {
        return userServiceImpl.updateSelfInfo(picture, username, account);
    }
}
