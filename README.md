# MyBatis



## 😀Demo1

### 搭建 MyBatis

#### 依赖

```xml
<!--数据库驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.29</version>
</dependency>
<!--MyBatis-->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.7</version>
</dependency>
<!--单元测试-->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```



#### 配置

1. 在 ***resources*** 目录下创建 ***MyBatis*** 核心配置文件 (***mybatis-config.xml***) , 用于连接数据库以及全局配置
2. 创建 ***mapper*** 接口, 接口里定义操作数据库的方法, 相当于以前的 DAO 层, 只是我们不再创建实现类
3. 创建映射文件, 映射文件一般放在 ***resources*** 目录下的 ***mappers*** 包里, 映射文件中编写 ***SQL*** 语句, 一个映射文件对应一张表, 也对应着一个实体类, 命名 : 实体类名 + ***Mapper.xml***
4. 将映射文件添加到核心配置文件的 ***mappers*** 标签中
4. 创建 junit 测试

#### 细节

- ***mapper*** 接口的全类名和映射文件中的命名空间保持一致
- ***mapper*** 接口中的方法名和映射文件中的 ***SQL*** 标签的 ***id*** 属性保持一致



#### 加入 log4j 日志功能

1. 添加依赖

   ```xml
   <!--log4j-->
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.12</version>
   </dependency>
   ```

2. 在 ***resources*** 目录下创建 ***log4j.xml*** 配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
   <log4j:configuration>
       <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
           <param name="Encoding" value="UTF-8" />
           <layout class="org.apache.log4j.PatternLayout">
               <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m (%F:%L) \n" />
           </layout>
       </appender>
       <logger name="java.sql">
           <level value="debug" />
       </logger>
       <logger name="org.apache.ibatis">
           <level value="info" />
       </logger>
       <root>
           <level value="debug" />
           <appender-ref ref="STDOUT" />
       </root>
   </log4j:configuration>
   ```

   

**注意**

- 也可以配置 ***properties*** 类型的配置文件，方式不同



### 配置文件详解

**配置文件的顺序**

> 配置文件的一些标签可以不写, 但顺序一定不能乱
>
> ```xml
> properties、settings、typeAliases、typeHandlers、objectFactory、objectWrapperFactory、reflectorFactory、plugins、environments、databaseIdProvider、mappers
> ```



**详解**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--标签顺序不能乱-->
<configuration>
    <!--引入properties文件，此时就可以${属性名}的方式访问属性值-->
    <!--<properties resource="jdbc.properties"></properties>-->

    <!--<settings>
        &lt;!&ndash;将表中字段的下划线自动转换为驼峰&ndash;&gt;
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        &lt;!&ndash;开启延迟加载&ndash;&gt;
        <setting name="lazyLoadingEnabled" value="true"/>
    </settings>-->

    <!--<typeAliases>
        &lt;!&ndash;
         typeAlias：设置某个具体的类型的别名
         属性:
         type：需要设置别名的类型的全类名
         alias：设置此类型的别名，且别名不区分大小写。若不设置此属性，该类型拥有默认的别名，即类名
        &ndash;&gt;
        &lt;!&ndash;<typeAlias type="com.atguigu.mybatis.bean.User"></typeAlias>&ndash;&gt;
        &lt;!&ndash;<typeAlias type="com.atguigu.mybatis.bean.User" alias="user"></typeAlias>&ndash;&gt;
        &lt;!&ndash;以包为单位，设置改包下所有的类型都拥有默认的别名，即类名且不区分大小写&ndash;&gt;
        &lt;!&ndash;<package name="com.atguigu.mybatis.bean"/>&ndash;&gt;
    </typeAliases>-->

    <!--
    environments：设置多个连接数据库的环境
    属性:
    default：设置默认使用的环境的id
    -->
    <!--数据库连接基本信息-->
    <environments default="development">
        <!--
         environment：设置具体的连接数据库的环境信息
         属性:
         id：设置环境的唯一标识，可通过environments标签中的default设置某一个环境的id，表示默认使用的环境
        -->
        <environment id="development">
            <!--
            transactionManager：设置事务管理方式
            属性:
            type：设置事务管理方式，type="JDBC|MANAGED"
            type="JDBC"：设置当前环境的事务管理都必须手动处理
            type="MANAGED"：设置事务被管理，例如spring中的AOP
            -->
            <transactionManager type="JDBC"/>
            <!--
             dataSource：设置数据源
             属性:
             type：设置数据源的类型，type="POOLED|UNPOOLED|JNDI"
             type="POOLED"：使用数据库连接池，即会将创建的连接进行缓存，下次使用可以从缓存中直接获取，不需要重新创建
             type="UNPOOLED"：不使用数据库连接池，即每次使用连接都需要重新创建
             type="JNDI"：调用上下文中的数据源
            -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/test"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
  <!--映射文件-->
    <mappers>
        <mapper resource="mappers/UserMapper.xml"/>
        <!--以包为单位添加映射文件,映射文件和mapper接口必须在同一包下(resources和java合并后的同一包)-->
        <!--<package name="com.dong.mapper"/>-->
    </mappers>
</configuration>
```





### MyBatis的增删改查(直接写入参数)

**添加**

```xml
<!--int insertUser()        id(自增)   name  password-->
<insert id="insertUser">
	insert into user values (null,'大白','12345')
</insert>
```

**删除**

```xml
<!-- int deleteUser()-->
<delete id="deleteUser">
	delete from user where id=1
</delete>
```

**查询实体**

```xml
<!--User selectUser()-->
<select id="selectUser" resultType="com.dong.entity.User">
	select * from user where id=10
</select>
```

**查询集合**

````xml
<!--List<User> selectUsers()-->
<select id="selectUsers" resultType="com.dong.entity.User">
	select * from user
</select>
````

**修改**

```xml
<!--int updataUser()-->
<updata id="updataUser">
	updata user set name='张三' where id=5
</updata>
```



**细节**

- select标签必须设置属性resultType或者resultMap,用于设置实体类和数据库表的映射关系
  - resultType: 自动映射,用于属性名与表中字段一致的情况
  - resultMap: 自定义映射,用于一对多或多对一或属性名与表中字段不一致的情况
- 当查询的数据为多条的时候,不能使用实体类作为返回值,只能使用集合,否则抛出异常TooManyResultsException



### MyBatis获取参数的两种方式

- #{}
  - 本质是预编译处理
  - MyBatis在处理#{}的时候,会把#{}替换为?,调用PerparedStatement的set方法来赋值
  - 使用#{}可以有效防止SQL注入,SQL注入是发生在编译阶段,而预编译是提前对SQL语句进行编译,而后注入的参数将不再进行编译,预编译完成之后,SQL结构已经固定,如此可以有效防止SQL注入
  - 自动添加单引号
- ${}
  - 本质是字符串替换
  - 把${}替换成变量的值
  - 若为字符串或日期类型的字段进行赋值时,需手动添加单引号



### 参数类型

#### 单个字面量参数

若mapper接口中方法的参数是单个参数,可以使用#{}或${}以任意参数名名称获取

```xml
<!--int deleteUser(int userId);-->
<delete id="deleteUser">
    delete from user where id=#{userId}
</delete>
```



#### 多个字面量参数

- 若mapper接口中方法的参数是多个参数,MyBatis会自动的将这些参数放入map集合中
  - 以arg0,arg1,...为键,以参数为值
  - 以param1,param2,...为键,以参数为值
- 以#{}或${}访问map集合中的键,就可以拿到值
- arg是以arg0开始,param以param1开始

```xml
<!--int updateUser(String name,int userId);-->
<update id="updateUser">
    update user set name = #{arg0} where id=#{arg1}
</update>
```



#### map集合类型参数

若mapper接口的方法参数为多个时,可以手动创建一个map集合,将参数放入map集合中,使用#{}或${}访问map集合对应的键就可以拿到相应的值

```xml
<!--int insertUserMap(Map<String,Object> map);-->
<insert id="insertUserMap">
    insert into user values (null,#{name},#{password})
</insert>
```



#### 实体类型参数

若mapper接口方法参数为实体类型时,使用#{}或${}访问实体对象的属性名拿到属性值

```xml
<!--int insertUserUser(User user);-->
<insert id="insertUserUser">
    insert into user values (null,#{name},#{password})
</insert>
```



#### @Param

- 可以通过@Param注解标识mapper接口中的方法参数,会将这些参数放入map集合中
  - 以@Param注解的value属性值为键,以参数为值
  - 以param1,param2,...为键,以参数为值
- 只需通过#{}或${}访问集合中的键,将能拿到相应的参数值

```xml
<!--User selectUserAN(@Param("name") String name, @Param("password") String password);-->
<select id="selectUserAN" resultType="com.dong.entity.User">
    select * from user where name=#{name} and password=#{password}
</select>
```



### MyBatis的查询功能

#### 单个实体对象

```xml
<!--User selectUser(int userId);-->
<select id="selectUser" resultType="com.dong.entity.User">
    select * from user where id=#{userId}
</select>
```



#### 多个对象装入Lis集合

```xml
<!-- List<User> selectUsers();-->
<select id="selectUsers" resultType="com.dong.entity.User">
    select * from user
</select>
```



#### 查询单个对象为map集合

注意将resultType设置为map

```xml
<!--Map<String,Object> selectMap();-->
<select id="selectMap" resultType="map">
    select *from user where id=#{userId}
</select>
```



#### 查询多条语句为map的Liist集合

- 方法一

  ```xml
  <!--List<Map<String,Object>> selectUserListMap();-->
  <select id="selectUserListMap" resultType="map">
      select *from user
  </select>
  ```

- 方法二

  使用@MapKey

  ```xml
  <!--
  @MayKey("id")
  <Map<String,Object> selectUserListMap();-->
  <select id="selectUserListMap" resultType="map">
      select *from user
  </select>
  ```

  















































