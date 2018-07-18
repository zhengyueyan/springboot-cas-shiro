# springboot-cas-shiro
cas单点登录结合shiro

# CAS 使用4.1.10  
cas-server-webapp里deployerConfigContext.xml添加下列代码 自定义数据库查询用户名密码比对

添加pom依赖

	<dependency>
        <groupId>org.jasig.cas</groupId>
        <artifactId>cas-server-support-jdbc</artifactId>
        <version>4.1.10</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.35</version>
    </dependency>

将原代码注释  源代码使用 casuser  Mellon 进行登录
	<!--
    <bean id="primaryAuthenticationHandler"
          class="org.jasig.cas.authentication.AcceptUsersAuthenticationHandler">
        <property name="users">
            <map>
                <entry key="casuser" value="Mellon"/>
            </map>
        </property>
    </bean>
	-->

# 修改后连接数据库登录 使用MD5加密 需要pom添加对应jar包
	<!-- 数据库连接登录 -->
    <bean id="dataSource"
          class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <property name="url"
                  value="jdbc:mysql://172.16.1.204:3306/test2" />
        <property name="username" value="root" />
        <property name="password" value="root" />
    </bean>

    <bean id="primaryAuthenticationHandler"
          class="org.jasig.cas.adaptors.jdbc.QueryDatabaseAuthenticationHandler"
          p:dataSource-ref="dataSource"
          p:passwordEncoder-ref="MD5PasswordEncoder"
          p:sql="select password from user_info where user_name=?" />

    <bean id="MD5PasswordEncoder" class="org.jasig.cas.authentication.handler.DefaultPasswordEncoder">
        <constructor-arg index="0">
            <value>MD5</value>
        </constructor-arg>
    </bean>
	

# shiroDemo使用的是之前demo的代码，只修改了ShiroCasConfiguration以及 MyShiroCasRealm 注释掉了MyShiroRealm和ShiroConfig

# 需要使用到https，需要配置ssl，
application.yml 修改为：
server:
    tomcat:
      uri-encoding: utf-8
    port: 8083
    ssl:
      key-alias: tomcat_key
      keyStoreType: JKS
      key-store: server.keystore
      key-store-password: changeit

# tomcat部分查询相关资料	

# shiro在https下通过setUnauthorizedUrl("403");不好使，具体原因百度，需要自行添加一个异常跳转
 使用@ControllerAdvice做异常处理  添加的是GlobalExceptionHandler类


	