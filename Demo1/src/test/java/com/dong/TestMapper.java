package com.dong;


import com.dong.entity.User;
import com.dong.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.print.MultiDocPrintService;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class TestMapper {

    /*
        测试获取mapper代理对象的方法的流程
        以及基础的增删改查功能
     */
    @Test
    public void test() throws IOException {

        // 读取配置文件获得输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 通过SqlSessionFactoryBuilder,传入输入流,获得SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象,传入true表示自动事务提交
        // 不提交事务将导致数据库接受不到数据
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 通过代理模式创建UserMapper的代理类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 调用UserMapper接口中的方法,寻找相应的映射文件,执行SQL语句,返回结果集
        // 增加
        /*int i = userMapper.insertUser("小黑","0000");
        System.out.println(i);*/
        // 删除
        /*int i1 = userMapper.deleteUser(12);
        System.out.println(i1);*/
        // 修改
        /*int i3 = userMapper.updateUser("小黑", 10);
        System.out.println(i3);*/
        // 查找 单个
        /*User user = userMapper.selectUser(10);
        System.out.println(user);*/
        // 查找 所有
        List<User> users = userMapper.selectUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*
        测试MyBatis接受数据的方法
        测试 Map类型参数
     */
    @Test
    public void test1() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> map = new HashMap<>();
        map.put("name","白天");
        map.put("password","9900");
        int i = userMapper.insertUserMap(map);
        System.out.println(i);

    }

    /*
        测试 以实体类作为参数
     */
    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User(null,"奥特曼","1009");
        int i = userMapper.insertUserUser(user);
        System.out.println(i);
    }

    /*
        测试 注解@Param
     */
    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUserAN("奥特曼", "1009");
        System.out.println(user);
    }
    /*
        测试将多条Map查询语句装入List集合中
     */
    @Test
    public void test4() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<Map<String, Object>> maps = userMapper.selectUserListMap();
        /*for (Map<String, Object> map : maps) {
            System.out.println(map);
        }*/
        //lamda表达式
        maps.forEach(a->{
            Iterator<Map.Entry<String,Object>> iterator=a.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
        });
    }
    /*
        测试查询单个对象装入map集合
     */
    @Test
    public void test5() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = userMapper.selectMap();
        for (String key : map.keySet()) {
            System.out.print(key+":"+map.get(key)+" ");
        }
    }
}
