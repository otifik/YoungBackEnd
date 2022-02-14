package com.young.safedrive.service;

import com.young.safedrive.model.ContactModel;
import com.young.safedrive.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @ Author : autumn
 * @ Date   : created in 21:38 2022/2/10
 */
public interface ContactService {
    BaseResponse<Integer> addContact(String account,
                                     String remark,
                                     String phone_number,
                                     MultipartFile[] head,
                                     Boolean isEmergencyContact) throws IOException;

    BaseResponse<Integer> deleteContact(String account,String remark);

    BaseResponse<Integer> updateContact(String account,
                                        String remark,
                                        String phone_number,
                                        MultipartFile[] head,
                                        Boolean isEmergencyContact) throws IOException;

    BaseResponse<List<ContactModel>> getContact(String account);
}
