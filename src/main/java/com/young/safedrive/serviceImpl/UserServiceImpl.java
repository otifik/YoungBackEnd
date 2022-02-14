package com.young.safedrive.serviceImpl;

import com.young.safedrive.dao.UserMapper;
import com.young.safedrive.model.UserModel;
import com.young.safedrive.response.BaseResponse;
import com.young.safedrive.service.UserService;
import com.young.safedrive.util.Log;
import com.young.safedrive.util.QiNiuUploadUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ Author : autumn
 * @ Date   : created in 17:45 2022/2/10
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String TAG = "USER";

    @Autowired
    private UserMapper userMapper;

    //登陆接口
    @Override
    public BaseResponse<Integer> login(String account, String password) {

        BaseResponse<Integer> response = new BaseResponse<>();

        UserModel userModel = userMapper.login(account, password);
        if (userModel != null) {
            Log.d(TAG, "login success , info : " + userModel.toString());
            response.setStatus(200);
            response.setMsg("success");
            response.setData(1);
        } else {
            Log.d(TAG, "login failure");
            response.setStatus(0);
            response.setMsg("failure");
            response.setData(0);
        }
        return response;
    }

    //注册接口
    @Override
    public BaseResponse<Integer> register(String username, String account, String password) {

        BaseResponse<Integer> response = new BaseResponse<>();

        int i = userMapper.register(username, account, password);
        if (i == 1) {
            Log.d(TAG, "register success");
            response.setStatus(200);
            response.setMsg("success");
        } else {
            Log.d(TAG, "register failure");
            response.setStatus(0);
            response.setMsg("failure");
        }
        response.setData(i);
        return response;
    }

    @Override
    public BaseResponse<UserModel> getSelfInfo(String account) {

        BaseResponse<UserModel> response = new BaseResponse<>();

        UserModel userModel = userMapper.getSelfInfo(account);
        if (userModel != null) {
            Log.d(TAG, "get self info success , info : " + userModel.toString());
            response.setStatus(200);
            response.setMsg("success");
            response.setData(userModel);
        } else {
            Log.d(TAG, "get self info failure");
            response.setStatus(0);
            response.setMsg("failure");
            response.setData(new UserModel());
        }
        return response;
    }

    @Override
    public BaseResponse<Integer> updateSelfInfo(MultipartFile[] picture, String username, String account) throws IOException {

        BaseResponse<Integer> response = new BaseResponse<>();

        for (MultipartFile file : picture) {
            //保存图片到本地
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String[] strings = originalFilename.split("\\.");
            String fileFormat = strings[strings.length - 1];
            String filepath = "C:\\Users\\tyurin\\Desktop\\picture\\" + account + "." + fileFormat;
            file.transferTo(new File(filepath));
            //上传图片至七牛云
            String key = QiNiuUploadUtil.upload(filepath);
            if (!key.equals("EMPTY")) {
                String head_address = "http://r70serzdg.hd-bkt.clouddn.com/" + key;
                int i = userMapper.updateSelfInfo(account, head_address, username);
                if (i == 1) {
                    Log.d(TAG, "update self info success");
                    response.setStatus(200);
                    response.setMsg("success");
                } else {
                    Log.d(TAG, "update self info failure");
                    response.setStatus(0);
                    response.setMsg("failure");
                }
                response.setData(i);
                return response;
            }
        }

        Log.d(TAG, "ERROR");
        response.setStatus(0);
        response.setMsg("failure");
        response.setData(0);
        return response;
    }
}