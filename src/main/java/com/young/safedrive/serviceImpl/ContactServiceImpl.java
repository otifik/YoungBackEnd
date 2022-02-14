package com.young.safedrive.serviceImpl;

import com.young.safedrive.dao.ContactMapper;
import com.young.safedrive.model.ContactModel;
import com.young.safedrive.response.BaseResponse;
import com.young.safedrive.service.ContactService;
import com.young.safedrive.util.Log;
import com.young.safedrive.util.QiNiuUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ Author : autumn
 * @ Date   : created in 9:44 2022/2/11
 */
@Service
public class ContactServiceImpl implements ContactService {

    private static final String TAG = "CONTACT";

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public BaseResponse<Integer> addContact(String account, String remark, String phone_number, MultipartFile[] head, Boolean isEmergencyContact) throws IOException {

        BaseResponse<Integer> response = new BaseResponse<>();

        for (MultipartFile file : head) {
            //保存图片到本地
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String[] strings = originalFilename.split("\\.");
            String fileFormat = strings[strings.length - 1];
            String filepath = "C:\\Users\\tyurin\\Desktop\\picture\\" + account + "_" + remark + "." + fileFormat;
            file.transferTo(new File(filepath));
            //上传图片至七牛云
            String key = QiNiuUploadUtil.upload(filepath);
            if (!key.equals("EMPTY")) {
                String head_address = "http://r70serzdg.hd-bkt.clouddn.com/" + key;
                int i = contactMapper.addContact(account, remark, phone_number, head_address, isEmergencyContact);
                if (i == 1) {
                    Log.d(TAG, "add contact success");
                    response.setStatus(200);
                    response.setMsg("success");
                } else {
                    Log.d(TAG, "add contact failure");
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

    @Override
    public BaseResponse<Integer> deleteContact(String account, String remark) {

        BaseResponse<Integer> response = new BaseResponse<>();

        int i = contactMapper.deleteContact(account, remark);

        if (i == 1) {
            Log.d(TAG, "delete success");
            response.setStatus(200);
            response.setMsg("success");
        } else {
            Log.d(TAG, "delete failure");
            response.setStatus(0);
            response.setMsg("failure");
        }
        response.setData(i);
        return response;

    }

    @Override
    public BaseResponse<Integer> updateContact(String account, String remark, String phone_number, MultipartFile[] head, Boolean isEmergencyContact) throws IOException {

        BaseResponse<Integer> response = new BaseResponse<>();

        for (MultipartFile file : head) {
            //保存图片到本地
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String[] strings = originalFilename.split("\\.");
            String fileFormat = strings[strings.length - 1];
            String filepath = "C:\\Users\\tyurin\\Desktop\\picture\\" + account + "_" + remark + "." + fileFormat;
            file.transferTo(new File(filepath));
            //上传图片至七牛云
            String key = QiNiuUploadUtil.upload(filepath);
            if (!key.equals("EMPTY")) {
                String head_address = "http://r70serzdg.hd-bkt.clouddn.com/" + key;
                int i = contactMapper.updateContact(account, remark, phone_number, head_address, isEmergencyContact);
                if (i == 1) {
                    Log.d(TAG, "update contact success");
                    response.setStatus(200);
                    response.setMsg("success");
                } else {
                    Log.d(TAG, "update contact failure");
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

    @Override
    public BaseResponse<List<ContactModel>> getContact(String account) {

        BaseResponse<List<ContactModel>> response = new BaseResponse<>();

        List<ContactModel> list = contactMapper.getContact(account);

        if (list != null){
            Log.d(TAG,"get contact success");
            response.setStatus(200);
            response.setMsg("success");
            response.setData(list);
        } else {
            Log.d(TAG,"get contact failure");
            response.setStatus(0);
            response.setMsg("failure");
            response.setData(null);
        }
        return response;
    }
}
