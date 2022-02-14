package com.young.safedrive.service;

import com.young.safedrive.model.UserModel;
import com.young.safedrive.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ Author : autumn
 * @ Date   : created in 17:42 2022/2/10
 */
public interface UserService {
    BaseResponse<Integer> login(String account,String password);
    BaseResponse<Integer> register(String username,String account,String password);
    //TODO:resetPassword&changePassword
    BaseResponse<UserModel> getSelfInfo(String account);
    BaseResponse<Integer> updateSelfInfo(MultipartFile[] picture,
                                         String username,
                                         String account) throws IOException;

}
