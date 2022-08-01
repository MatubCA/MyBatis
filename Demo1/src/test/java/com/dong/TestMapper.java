package com.dong;

import com.dong.entity.User;
import com.dong.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestMapper {


    @Test
    public void test() throws IOException {

        // 读取配置文件获得输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 通过SqlSessionFactoryBuilder,传入输入流,获得SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象,传入true表示自动事务提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 通过代理模式创建UserMapper的代理类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 调用UserMapper接口中的方法,寻找相应的映射文件,执行SQL语句,返回结果集

        /*Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","龙傲天");
        map.put("password","2020");*/
        User user1 = new User(null, "叶良辰", "888");
        userMapper.insertUser(user1);
        List<User> users = userMapper.selectUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
