package com.wizard.mapper;

import com.wizard.model.User;
import com.wizard.mybatis.annotations.Select;

import java.util.List;

public interface IUserMapper {
//    List<User> getAllUsers();

    @Select("select * from tab_user")
    List<User> getAllUsers();
}
