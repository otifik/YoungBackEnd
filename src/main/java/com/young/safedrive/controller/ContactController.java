package com.young.safedrive.controller;

import com.young.safedrive.model.ContactModel;
import com.young.safedrive.response.BaseResponse;
import com.young.safedrive.serviceImpl.ContactServiceImpl;
import com.young.safedrive.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @ Author : autumn
 * @ Date   : created in 9:52 2022/2/11
 */
@RestController
@Scope("prototype")
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactServiceImpl contactServiceImpl;

    @PostMapping("/addContact")
    public BaseResponse<Integer> addContact(@RequestParam("account") String account,
                                            @RequestParam("remark") String remark,
                                            @RequestParam("phone_number") String phone_number,
                                            @RequestParam("head") MultipartFile[] head,
                                            @RequestParam("isEmergencyContact") Boolean isEmergencyContact) throws IOException {
        return contactServiceImpl.addContact(account, remark, phone_number, head, isEmergencyContact);
    }

    @DeleteMapping("/deleteContact")
    public BaseResponse<Integer> deleteContact(@RequestParam("account")String account, @RequestParam("remark") String remark){
        return contactServiceImpl.deleteContact(account, remark);
    }

    @PostMapping("/updateContact")
    public BaseResponse<Integer> updateContact(@RequestParam("account") String account,
                                               @RequestParam("remark") String remark,
                                               @RequestParam("phone_number") String phone_number,
                                               @RequestParam("head") MultipartFile[] head,
                                               @RequestParam("isEmergencyContact") Boolean isEmergencyContact) throws IOException {
        return contactServiceImpl.updateContact(account, remark, phone_number, head, isEmergencyContact);
    }

    @GetMapping("/getContact")
    public BaseResponse<List<ContactModel>> getContact(@RequestParam("account") String account){
        return contactServiceImpl.getContact(account);
    }
}
