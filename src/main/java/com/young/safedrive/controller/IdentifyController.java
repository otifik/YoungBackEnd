package com.young.safedrive.controller;

import com.young.safedrive.response.BaseResponse;
import com.young.safedrive.serviceImpl.IdentifyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ Author : autumn
 * @ Date   : created in 14:11 2022/2/12
 */
@RestController
@Scope("prototype")
@RequestMapping("/identify")
public class IdentifyController {

    @Autowired
    private IdentifyServiceImpl identifyServiceImpl;

    @PostMapping("/identifyDriver")
    public BaseResponse<String> identifyDriver(@RequestParam("video") MultipartFile[] video) throws IOException {
        return identifyServiceImpl.identifyDriver(video);
    }
}
