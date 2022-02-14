package com.young.safedrive.dao;

import com.young.safedrive.model.UserModel;
import org.apache.ibatis.annotations.*;

/**
 * @ Author : autumn
 * @ Date   : created in 17:45 2022/2/10
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where account = '${account}' and password = '${password}'")
    UserModel login(@Param("account") String account, @Param("password") String password);

    @Insert("insert into user values (0,'${username}','${account}','${password}','NULL')")
    int register(@Param("username") String username,@Param("account") String account,@Param("password") String password);

    @Select("select * from user where account = '${account}'")
    UserModel getSelfInfo(@Param("account") String account);

    @Update("update user set username = '${username}',head_address = '${head_address}' where account = '${account}'")
    int updateSelfInfo(@Param("account") String account,@Param("head_address") String head_address,@Param("username") String username);

}
