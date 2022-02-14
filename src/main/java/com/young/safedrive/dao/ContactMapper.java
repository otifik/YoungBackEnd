package com.young.safedrive.dao;

import com.young.safedrive.model.ContactModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ Author : autumn
 * @ Date   : created in 9:45 2022/2/11
 */
@Mapper
public interface ContactMapper {

    @Insert("insert into contact values (0,'${account}','${remark}','${phone_number}','${head_address}',${isEmergencyContact})")
    int addContact(@Param("account") String account,
                   @Param("remark") String remark,
                   @Param("phone_number") String phone_number,
                   @Param("head_address") String head_address,
                   @Param("isEmergencyContact") Boolean isEmergencyContact);


    @Delete("delete from contact where account = '${account}' and remark = '${remark}'")
    int deleteContact(@Param("account") String account,@Param("remark") String remark);

    @Update("update contact set remark = '${remark}', phone_number = '${phone_number}', head_address = '${head_address}',isEmergencyContact = ${isEmergencyContact} where account = '${account}'")
    int updateContact(@Param("account") String account,
                      @Param("remark") String remark,
                      @Param("phone_number") String phone_number,
                      @Param("head_address") String head_address,
                      @Param("isEmergencyContact") Boolean isEmergencyContact);

    @Select("select * from contact where account = '${account}'")
    List<ContactModel> getContact(@Param("account") String account);
}
