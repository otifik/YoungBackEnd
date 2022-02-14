package com.young.safedrive.service;

import com.young.safedrive.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ Author : autumn
 * @ Date   : created in 14:12 2022/2/12
 */
public interface IdentifyService {

    BaseResponse<String> identifyDriver(MultipartFile[] video) throws IOException;

}
