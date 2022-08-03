package com.dong.mapper;

import com.dong.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 实体类型参数
    int insertUserUser(User user);
    // 将多个参数放入map集合中
    int insertUserMap(Map<String,Object> map);
    int insertUser(String name,String password);

    int deleteUser(int userId);

    User selectUser(int userId);
    User selectUserAN(@Param("name") String name, @Param("password") String password);
    Map<String,Object> selectMap();
    List<Map<String,Object>> selectUserListMap();

    List<User> selectUsers();

    int updateUser(String name,int userId);
}
