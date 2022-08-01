package com.dong.mapper;

import com.dong.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    int insertUser(User user);
//    int insertUser(Map<String,Object> map);
//    int insertUser(String name,String password);

    int deleteUser(int userId);

    User selectUser(int userId);

    List<User> selectUsers();

    int updateUser(String name,int userId);
}
