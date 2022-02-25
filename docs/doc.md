# TANSCI 系统

## 介绍

TANSCI 是基于 SpringBoot + Vue3.2 + Element Plus 的后台管理系统。带你快速掌握SpringBoot核心知识 + Vue全家桶全栈技能。在此过程中，从0到1经历开发全流程，掌握前后端分离开发模式，搭建一个专属自己的、内容可灵活配置的知识库系统。

**项目地址：**

Gitee: [https://gitee.com/typ1805/tansci](https://gitee.com/typ1805/tansci)

GitHub: [https://github.com/typ1805/tansci](https://github.com/typ1805/tansci)

#### 知识点

- SpringBoot 核心知识体系
- Vue3 全家桶实战落地
- 标准的前后端开发模式
- 企业级编码风格
- 规范化Git操作、发布流程
- 配套通用的前后端组件+工具

#### 开发计划

- 开发内容：我们主要从安装开发环境、搭建前后端项目、实现功能模块、部署上线的开发全流程。
- 项目目录：
<pre>
├─docs                         # 文档相关
├─sql                          # 数据库SQL脚本
├─src                          # 后端项目目录
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─tansci
│  │  │          ├─common       # 公共包
│  │  │          ├─config       # 配置
│  │  │          ├─controller   # controller 层
│  │  │          ├─domain       # 映射实体
│  │  │          │  ├─dto       # DTO 实体
│  │  │          │  └─vo        # VO 实体
│  │  │          ├─mapper       # mapper 层
│  │  │          ├─security     # 安全认证相关
│  │  │          ├─service      # service 层
│  │  │          │  └─impl
│  │  │          └─utils        # 工具包
│  │  └─resources
│  │      └─mapper              # mapper XML 映射
│  └─test                       # 测试
└─tansci-view                   # 前端项目目录
    ├─public
    └─src
        ├─api                   # 服务AIP配置
        ├─assets                # 静态资源（包含 公共样式、图片）
        │  ├─css                
        │  └─image
        ├─components            # 公共组件
        ├─layout                # 整体布局
        ├─router                # 路由
        ├─store                 # axios 封装
        ├─utils                 # 工具包
        └─views                 # 功能模块
            └─system            # 基础功能模块
</pre>

## 系统概述

#### 项目介绍

主框架是基于 SpringBoot + Vue3.2 + Element Plus 的管理系统，包含基础权限、安全认证、以及常用的一些组件功能。项目易上手，技术更综合，能力更全面。

全面的技术运用，细致的知识讲解：

- 解决前后端分离难题：前后端功能整合，集成HTTP组件Axios，解决前后端分离架构中常见的问题，如跨域、参数传递、多环境配置等；
- 落地实践工程化项目：涵盖日志、配置文件、部署、Git、Maven，后端接口统一返回参数设计、封装统一请求返回参数、工具类封装；
- SpringBoot核心技能：Spring AOP、Spring Security、异常处理、日志优化、多环境等。

#### 项目架构

- 后端架构

    - SpringBoot 2.6.1.RELEASE
    - Mybatis Plus 3.4.3.1
    - Spring Security
    - JJWT 0.9.0
    - Druid 1.2.6
    - Fastjson 1.2.75
    - knife4j 3.0.3  
    - Lombok

- 前端架构

    - vue 3.2.16
    - element-plus 1.3.0-beta.1
    - vue-router 4.0.12
    - vuex 4.0.2
    - axios 0.24.0
    - less 4.1.2
    - nprogress 0.2.0
    - echarts 5.2.2

#### 开发环境

开发环境大版本必须保持一致，以避免不必要的集成问题。
  
- JDK 1.8
- mysql 5.7
- node 14.16
- npm 6.14
- Nginx 1.16
- Maven 3.8
- Git 2.14

#### 开发工具

作为一名Java程序开发人员，可以的选择集成开发环境IDE非常多，有开源免费的、有商用收费的。如何选择一款适合自己的集成开发环境，亦或说选择一款符合自己项目开发需要的集成开发环境。如果选择得当，那么就能够使得开发工作事半功倍，否则事倍而功半。

- 后端开发工具：ideaIU-2020.3.2
- 前端开发工具：VSCode-x64-1.57.1
- 接口测试工具：Postman（可使用谷歌浏览器插件）
- 数据库操作工具：Navicat Premium 15

> 注意：开发工具版本不一致，可能会造成项目无法开发、运行等问题。

## 搭建开发环境

#### 后端环境

**1、安装JDK 1.8**

1.1、下载地址

[https://www.oracle.com/java/technologies/downloads](https://www.oracle.com/java/technologies/downloads)

> 如下载需要登录，可使用Java账号网址: http://bugmenot.com/view/oracle.com

1.2、配置环境变量

```
# 配置路径信息

JAVA_HOME: C:\Program Files\Java\jdk1.8.0_112

# 配置 CLASSPATH

.;%JAVA_HOME%\lib;

# 配置 Path

%JAVA_HOME%\bin
```

1.3、验证是否安装成功

```
C:\Users\acer>java -version
java version "1.8.0_112"
Java(TM) SE Runtime Environment (build 1.8.0_112-b15)
Java HotSpot(TM) 64-Bit Server VM (build 25.112-b15, mixed mode)
```

**2、安装 MySQL**

2.1、下载地址

[https://www.mysql.com/downloads](https://www.mysql.com/downloads/)

解压安装包，根据自己的喜好选择路径，我选择的路径是```D:\MySQL\```，因此MySQL的完整路径为：```D:\MySQL\mysql-5.7.21-winx64```。

2.2、配置环境变量

```
# 新增系统环境变量

MYSQL_HOME: D:\MySQL\mysql-5.7.21-winx64

# 配置 Path

%MYSQL_HOME%\bin
```

> 在Path中添加：%MYSQL_HOME%\bin，注意Path中不同值之间的“；”符号不能省略

2.3、创建my.ini文件

可以先新建一个my.txt文件，然后通过重命名修改文件后缀为.ini，以前的版本解压后或许会存在my-default.ini文件，但是5.7.21版本没有，因此要自己手动创建该文件，文件的内容如下：

```
[mysqld]  
port = 3306  
basedir= D:/MySQL/mysql-5.7.21-winx64  
datadir= D:/MySQL/mysql-5.7.21-winx64/data   
max_connections=200  
character-set-server=utf8  
default-storage-engine=INNODB  
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES  
[mysql]  
default-character-set=utf8  
```

> 此步骤要注意：安装路径的文件夹之间用“/”而不是“\”,否则在下面的操作中可能会出错。编辑好my.ini文件之后，将my.ini文件放D:\MySQL\mysql-5.7.21-winx64

2.4、执行安装

以管理员身份打开cmd命令窗口，将目录切换到MySQL的安装目录的bin目录下，执行以下语句进行MySQL的安装：

```
mysqld -install
```

执行命令后提示：Service successfully installed. 表示安装成功。

> 如果报错： "无法启动此程序，因为计算机中丢失 MSVCR120.dll。尝试重新安装该程序以解决此问题。" 运行vcredist_x64.exe后在执行就不报错了。

2.5、MySQL初始化

```
mysqld --initialize-insecure --user=mysql
```

> 执行命令后会在MySQL的安装目录下生成data目录并创建root用户。

2.6、启动MySQL服务

```
net start mysql

# 执行后会有如下提示：

MySQL服务正在启动..
MySQL服务已经启动成功。
```

2.7、启动MySQL之后，root用户的密码为空，设置密码，命令如下:

```
mysqladmin -u root -p password 新密码 

Enter password: 旧密码
```

需要输入旧密码时，由于旧密码为空，所以直接回车即可。

**3、安装Maven**

3.1、下载地址

[https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

我的解压文件为 ```D:\apache-maven-3.5.4```

3.2、配置环境变量

```
# 配置MAVEN_HOME

MAVEN_HOME: D:\apache-maven-3.5.4

# 编辑环境变量Path

%MAVEN_HOME%\bin;
```

3.3、验证是否安装成功

```
C:\Users\acer>mvn -v
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-18T02:33:14+08:00)
Maven home: D:\apache-maven-3.5.4\bin\..
Java version: 1.8.0_112, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_112\jre

Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "6.3", arch: "amd64", family: "windows"
```

**4、安装Git**

4.1、下载地址

[https://www.git-scm.com/downloads](https://www.git-scm.com/downloads)

在Windows平台上安装Git非常轻松，运行exe文件即可。

完成安装之后，就可以使用命令行的 git 工具（已经自带了 ssh 客户端）了，另外还有一个图形界面的 Git 项目管理工具。

在开始菜单里找到"Git"->"Git Bash"，会弹出 Git 命令窗口，你可以在该窗口进行 Git 操作。

4.2、配置个人的用户名称和电子邮件地址

```
git config --global user.name "tansci"

git config --global user.email tansci@qq.com
```

4.3、验证是否安装成功

```
C:\Users\acer>git --version
git version 2.14.1.windows.1
```

#### 前端环境

**1、安装Node和npm**

1.1、下载地址

[https://nodejs.org/en/download](https://nodejs.org/en/download)

1.2、执行安装

运行msi文件傻瓜式安装即可。

安装成功，并且安装过程中已自动配置了环境变量和安装好了npm包，此时可以执行 node -v 和 npm -v 分别查看node和npm的版本号：

```
# 查看node版本

C:\Users\acer>node -v
v12.14.1

# 查看npm版本

C:\Users\acer>npm -v
6.13.4
```

1.3、配置npm在安装全局模块时的路径和缓存cache的路径

因为在执行例如npm install webpack -g等命令全局安装的时候，默认会将模块安装在C:\Users\用户名\AppData\Roaming路径下的npm和npm_cache中，不方便管理且占用C盘空间。

所以这里配置自定义的全局模块安装目录，在node.js安装目录下新建两个文件夹 node_global和node_cache，然后在cmd命令下执行如下两个命令：

```
npm config set prefix "D:\nodejs\node_global"

npm config set cache "D:\nodejs\node_cache"
```

> 注：执行成功后，要配置环境变量 -> 系统变量中新建一个变量名为: ```NODE_PATH```， 值为```D:\nodejs\node_modules``` 最后编辑用户变量里的Path: 将相应npm的路径改为：```D:\nodejs\node_global```。

## 数据库设计

我们在设计数据库、表时，按照一下两个规范设计即可：

#### 按照规范设计的方法，考虑数据库及其应用系统开发全过程，将数据库设计分为以下6个阶段

- 需求分析阶段：需求收集和分析，得到数据字典和数据流图。
- 概念结构设计阶段：对用户需求综合、归纳与抽象，形成概念模型，用E-R图表示。
- 逻辑结构设计阶段：将概念结构转换为某个DBMS所支持的数据模型。
- 数据库物理设计阶段：为逻辑数据模型选取一个最适合应用环境的物理结构。
- 数据库实施阶段：建立数据库，编制与调试应用程序，组织数据入库，程序试运行。
- 数据库运行和维护阶段：对数据库系统进行评价、调整与修改。

#### 数据库表结构的设计

- 二维表：二维即指行和列
- 四个范式：不可再分、属性完全依赖于某个候选键、属性不依赖于其它非主属性、禁止非主键列和其他非主键列一对多关系
- 业务相关的问题

使用Navicat连接安装好的mysql服务，创建名为tansci数据库。

- 字典表（sys_dic）
- 菜单表（sys_menu）
- 菜单角色表（sys_menu_role）
- 权限表（sys_role）
- 组织表（sys_org）
- 组织角色表（sys_org_role）
- 用户表（sys_user）
- 用户组织表（sys_user_org）
- 用户角色表（sys_user_role）
- 操作日志表（log_info）
- 异常日志表（log_error_info）
- 消息模板表（template）
- 消息模板详情（template_details）
- 任务配置表（task_config）

创建表语句及基础数据可执行项目目录下```tansci\docs\sql\tansci.sql```的SQL脚本。

## 搭建后端项目

#### 创建SpringBoot项目

**1、创建项目**

创建SpringBoot项目，选择版本为 2.6.1即可。创建如下目录：

<pre>
├─src                          
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─tansci
│  │  │          ├─common       # 公共包
│  │  │          ├─config       # 配置
│  │  │          ├─controller   # controller 层
│  │  │          ├─domain       # 映射实体
│  │  │          │  ├─dto       # DTO 实体
│  │  │          │  └─vo        # VO 实体
│  │  │          ├─mapper       # mapper 层
│  │  │          ├─security     # 安全认证相关
│  │  │          ├─service      # service 层
│  │  │          │  └─impl
│  │  │          └─utils        # 工具包
│  │  └─resources
│  │      └─mapper              # mapper XML 映射
│  └─test                       # 测试
</pre>

**2、添加依赖（pom.xml）**

```xml
<properties>
    <java.version>1.8</java.version>
    <fastjson.version>1.2.75</fastjson.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>${fastjson.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

**3、添加配置**

在resources目录下将```application.properties```文件修改为```application.yml```格式，配置数据库连接信息。

```yml
server:
  port: 8005
  servlet:
    context-path: /tansci/

spring:
  jackson:
    time-zone: GMT+8
    date-format: yyyy-MM-dd HH:mm:ss
```

**4、多环境**

新建不同环境的配置文件 ```application-dev.yml```、``application-pro.yml``，默认设置为测试环境（dev）。

- 修改application.yml

```yml
server:
  port: 8005
  servlet:
    context-path: /tansci/

spring:
  profiles:
    active: dev # 测试环境
  jackson:
    time-zone: GMT+8
    date-format: yyyy-MM-dd HH:mm:ss
```

- 创建application-dev.yml

```yml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/tansci?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&autoReconnect=true
    username: root
    password: admin
    initialSize: 10
    minIdle: 10
    maxActive: 100
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 300000
    minEvictableIdleTimeMillis: 3600000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
```

- 创建application-pro.yml

```yml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 配置线上环境地址
    url: jdbc:mysql://127.0.0.1:3306/tansci?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&autoReconnect=true
    username: root
    password: root
    initialSize: 10
    minIdle: 10
    maxActive: 100
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 300000
    minEvictableIdleTimeMillis: 3600000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
```

至此基础的SpringBoot项目搭建配置已完成，启动体验一下。

```java
"C:\Program Files\Java\jdk1.8.0_102\bin\java.exe" -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:56652,suspend=y,server=n -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -javaagent:C:\Users\admin\AppData\Local\JetBrains\IntelliJIdea2021.1\captureAgent\debugger-agent.jar -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_102\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_102\jre\lib\rt.jar;F:\privatespace\tansci\target\classes;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter\2.6.1\spring-boot-starter-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot\2.6.1\spring-boot-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-context\5.3.13\spring-context-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-autoconfigure\2.6.1\spring-boot-autoconfigure-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-logging\2.6.1\spring-boot-starter-logging-2.6.1.jar;D:\apache-maven-3.8.1\repository\ch\qos\logback\logback-classic\1.2.7\logback-classic-1.2.7.jar;D:\apache-maven-3.8.1\repository\ch\qos\logback\logback-core\1.2.7\logback-core-1.2.7.jar;D:\apache-maven-3.8.1\repository\org\apache\logging\log4j\log4j-to-slf4j\2.14.1\log4j-to-slf4j-2.14.1.jar;D:\apache-maven-3.8.1\repository\org\apache\logging\log4j\log4j-api\2.14.1\log4j-api-2.14.1.jar;D:\apache-maven-3.8.1\repository\org\slf4j\jul-to-slf4j\1.7.32\jul-to-slf4j-1.7.32.jar;D:\apache-maven-3.8.1\repository\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-core\5.3.13\spring-core-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-jcl\5.3.13\spring-jcl-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\yaml\snakeyaml\1.29\snakeyaml-1.29.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-security\2.6.1\spring-boot-starter-security-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-aop\5.3.13\spring-aop-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-beans\5.3.13\spring-beans-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\security\spring-security-config\5.6.0\spring-security-config-5.6.0.jar;D:\apache-maven-3.8.1\repository\org\springframework\security\spring-security-core\5.6.0\spring-security-core-5.6.0.jar;D:\apache-maven-3.8.1\repository\org\springframework\security\spring-security-crypto\5.6.0\spring-security-crypto-5.6.0.jar;D:\apache-maven-3.8.1\repository\org\springframework\security\spring-security-web\5.6.0\spring-security-web-5.6.0.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-expression\5.3.13\spring-expression-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-web\2.6.1\spring-boot-starter-web-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-json\2.6.1\spring-boot-starter-json-2.6.1.jar;D:\apache-maven-3.8.1\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.13.0\jackson-datatype-jdk8-2.13.0.jar;D:\apache-maven-3.8.1\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.13.0\jackson-datatype-jsr310-2.13.0.jar;D:\apache-maven-3.8.1\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.13.0\jackson-module-parameter-names-2.13.0.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-tomcat\2.6.1\spring-boot-starter-tomcat-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\apache\tomcat\embed\tomcat-embed-core\9.0.55\tomcat-embed-core-9.0.55.jar;D:\apache-maven-3.8.1\repository\org\apache\tomcat\embed\tomcat-embed-el\9.0.55\tomcat-embed-el-9.0.55.jar;D:\apache-maven-3.8.1\repository\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.55\tomcat-embed-websocket-9.0.55.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-web\5.3.13\spring-web-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-webmvc\5.3.13\spring-webmvc-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-aop\2.6.1\spring-boot-starter-aop-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\aspectj\aspectjweaver\1.9.7\aspectjweaver-1.9.7.jar;D:\apache-maven-3.8.1\repository\mysql\mysql-connector-java\8.0.27\mysql-connector-java-8.0.27.jar;D:\apache-maven-3.8.1\repository\com\baomidou\mybatis-plus-boot-starter\3.4.3.1\mybatis-plus-boot-starter-3.4.3.1.jar;D:\apache-maven-3.8.1\repository\com\baomidou\mybatis-plus\3.4.3.1\mybatis-plus-3.4.3.1.jar;D:\apache-maven-3.8.1\repository\com\baomidou\mybatis-plus-extension\3.4.3.1\mybatis-plus-extension-3.4.3.1.jar;D:\apache-maven-3.8.1\repository\com\baomidou\mybatis-plus-core\3.4.3.1\mybatis-plus-core-3.4.3.1.jar;D:\apache-maven-3.8.1\repository\com\baomidou\mybatis-plus-annotation\3.4.3.1\mybatis-plus-annotation-3.4.3.1.jar;D:\apache-maven-3.8.1\repository\com\github\jsqlparser\jsqlparser\4.0\jsqlparser-4.0.jar;D:\apache-maven-3.8.1\repository\org\mybatis\mybatis\3.5.7\mybatis-3.5.7.jar;D:\apache-maven-3.8.1\repository\org\mybatis\mybatis-spring\2.0.6\mybatis-spring-2.0.6.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-jdbc\2.6.1\spring-boot-starter-jdbc-2.6.1.jar;D:\apache-maven-3.8.1\repository\com\zaxxer\HikariCP\4.0.3\HikariCP-4.0.3.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-jdbc\5.3.13\spring-jdbc-5.3.13.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-tx\5.3.13\spring-tx-5.3.13.jar;D:\apache-maven-3.8.1\repository\com\alibaba\druid-spring-boot-starter\1.2.6\druid-spring-boot-starter-1.2.6.jar;D:\apache-maven-3.8.1\repository\com\alibaba\druid\1.2.6\druid-1.2.6.jar;D:\apache-maven-3.8.1\repository\org\slf4j\slf4j-api\1.7.32\slf4j-api-1.7.32.jar;D:\apache-maven-3.8.1\repository\org\projectlombok\lombok\1.18.22\lombok-1.18.22.jar;D:\apache-maven-3.8.1\repository\com\alibaba\fastjson\1.2.75\fastjson-1.2.75.jar;D:\apache-maven-3.8.1\repository\com\jcraft\jsch\0.1.55\jsch-0.1.55.jar;D:\apache-maven-3.8.1\repository\io\jsonwebtoken\jjwt\0.9.0\jjwt-0.9.0.jar;D:\apache-maven-3.8.1\repository\com\fasterxml\jackson\core\jackson-databind\2.13.0\jackson-databind-2.13.0.jar;D:\apache-maven-3.8.1\repository\com\fasterxml\jackson\core\jackson-annotations\2.13.0\jackson-annotations-2.13.0.jar;D:\apache-maven-3.8.1\repository\com\fasterxml\jackson\core\jackson-core\2.13.0\jackson-core-2.13.0.jar;D:\apache-maven-3.8.1\repository\org\springframework\boot\spring-boot-starter-mail\2.6.1\spring-boot-starter-mail-2.6.1.jar;D:\apache-maven-3.8.1\repository\org\springframework\spring-context-support\5.3.13\spring-context-support-5.3.13.jar;D:\apache-maven-3.8.1\repository\com\sun\mail\jakarta.mail\1.6.7\jakarta.mail-1.6.7.jar;D:\apache-maven-3.8.1\repository\com\sun\activation\jakarta.activation\1.2.2\jakarta.activation-1.2.2.jar;D:\apache-maven-3.8.1\repository\com\aliyun\dysmsapi20170525\2.0.4\dysmsapi20170525-2.0.4.jar;D:\apache-maven-3.8.1\repository\com\aliyun\tea-util\0.2.11\tea-util-0.2.11.jar;D:\apache-maven-3.8.1\repository\com\google\code\gson\gson\2.8.9\gson-2.8.9.jar;D:\apache-maven-3.8.1\repository\com\aliyun\endpoint-util\0.0.6\endpoint-util-0.0.6.jar;D:\apache-maven-3.8.1\repository\com\aliyun\tea\1.1.15\tea-1.1.15.jar;D:\apache-maven-3.8.1\repository\com\squareup\okhttp3\okhttp\3.14.9\okhttp-3.14.9.jar;D:\apache-maven-3.8.1\repository\com\squareup\okio\okio\1.17.2\okio-1.17.2.jar;D:\apache-maven-3.8.1\repository\org\jacoco\org.jacoco.agent\0.8.4\org.jacoco.agent-0.8.4-runtime.jar;D:\apache-maven-3.8.1\repository\com\aliyun\tea-openapi\0.0.16\tea-openapi-0.0.16.jar;D:\apache-maven-3.8.1\repository\com\aliyun\credentials-java\0.2.4\credentials-java-0.2.4.jar;D:\apache-maven-3.8.1\repository\org\ini4j\ini4j\0.5.4\ini4j-0.5.4.jar;D:\apache-maven-3.8.1\repository\javax\xml\bind\jaxb-api\2.3.1\jaxb-api-2.3.1.jar;D:\apache-maven-3.8.1\repository\javax\activation\javax.activation-api\1.2.0\javax.activation-api-1.2.0.jar;D:\apache-maven-3.8.1\repository\com\sun\xml\bind\jaxb-core\2.3.0\jaxb-core-2.3.0.jar;D:\apache-maven-3.8.1\repository\com\sun\xml\bind\jaxb-impl\2.3.0\jaxb-impl-2.3.0.jar;D:\apache-maven-3.8.1\repository\com\aliyun\openapiutil\0.1.6\openapiutil-0.1.6.jar;D:\apache-maven-3.8.1\repository\org\bouncycastle\bcpkix-jdk15on\1.65\bcpkix-jdk15on-1.65.jar;D:\apache-maven-3.8.1\repository\org\bouncycastle\bcprov-jdk15on\1.65\bcprov-jdk15on-1.65.jar;D:\JetBrains\IntelliJ IDEA 2021.1.1\lib\idea_rt.jar" com.tansci.TansciApplication
Connected to the target VM, address: '127.0.0.1:56652', transport: 'socket'

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.6.1)

2022-01-19 12:41:36,667  [main] INFO  com.tansci.TansciApplication.logStarting 55 - Starting TansciApplication using Java 1.8.0_102 on DESKTOP-B7F1RDS with PID 26116 (F:\privatespace\tansci\target\classes started by admin in F:\privatespace\tansci)
2022-01-19 12:41:36,670  [main] INFO  com.tansci.TansciApplication.logStartupProfileInfo 639 - The following profiles are active: dev
2022-01-19 12:41:38,374  [main] INFO  org.springframework.boot.web.embedded.tomcat.TomcatWebServer.initialize 108 - Tomcat initialized with port(s): 8005 (http)
2022-01-19 12:41:38,382  [main] INFO  org.apache.coyote.http11.Http11NioProtocol.log 173 - Initializing ProtocolHandler ["http-nio-8005"]
2022-01-19 12:41:38,383  [main] INFO  org.apache.catalina.core.StandardService.log 173 - Starting service [Tomcat]
2022-01-19 12:41:38,383  [main] INFO  org.apache.catalina.core.StandardEngine.log 173 - Starting Servlet engine: [Apache Tomcat/9.0.55]
2022-01-19 12:41:38,479  [main] INFO  org.apache.catalina.core.ContainerBase.[Tomcat].[localhost].[/tansci].log 173 - Initializing Spring embedded WebApplicationContext
2022-01-19 12:41:38,480  [main] INFO  org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.prepareWebApplicationContext 290 - Root WebApplicationContext: initialization completed in 1772 ms
2022-01-19 12:41:39,843  [main] INFO  org.springframework.security.web.DefaultSecurityFilterChain.<init> 51 - Will secure Ant [pattern='/api/**'] with []
2022-01-19 12:41:39,884  [main] INFO  org.springframework.security.web.DefaultSecurityFilterChain.<init> 51 - Will secure any request with [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@2eebce87, org.springframework.security.web.context.SecurityContextPersistenceFilter@13004dd8, org.springframework.security.web.header.HeaderWriterFilter@52bba91a, org.springframework.web.filter.CorsFilter@6ee37ca7, org.springframework.security.web.authentication.logout.LogoutFilter@288728e, com.tansci.security.JWTAuthenticationFilter@6775c0d1, com.tansci.security.JWTAuthorizationFilter@38f617f4, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@65d9e72a, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@64440065, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@4899799b, org.springframework.security.web.session.SessionManagementFilter@21516c88, org.springframework.security.web.access.ExceptionTranslationFilter@28554ac8, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@5fb7ab9c]
2022-01-19 12:41:40,368  [main] INFO  org.apache.coyote.http11.Http11NioProtocol.log 173 - Starting ProtocolHandler ["http-nio-8005"]
2022-01-19 12:41:40,384  [main] INFO  org.springframework.boot.web.embedded.tomcat.TomcatWebServer.start 220 - Tomcat started on port(s): 8005 (http) with context path '/tansci'
2022-01-19 12:41:40,392  [main] INFO  com.tansci.TansciApplication.logStarted 61 - Started TansciApplication in 4.267 seconds (JVM running for 5.295)

```

#### 公共组件封装

创建项目中常用的常量、枚举、全局异常处理、封装统一请求返回参数、工具类封装等基础公共类包。

**1、在common目录下创建常量、枚举公共信息**

常量 Constants.java

```java
package com.tansci.common.constant;

/**
 * @path：com.tansci.common.constant.Constants.java
 * @className：Constants.java
 * @description：常量
 * @author：tanyp
 * @dateTime：2021/10/22 16:14
 * @editNote：
 */
public class Constants {

    /**
     * 系统状态
     */
    public final static Integer SUCCESS = 200;

    public final static String SUCCESS_MESSAGE = "操作成功！";

    public final static Integer ERROR = 500;

    public final static String ERROR_MESSAGE = "操作失败！";

    public final static String PARAMETER_ERROR = "请求参数有误，请核查！";

    /**
     * 未删除状态
     */
    public final static Integer NOT_DEL_FALG = 0;
    /**
     * 已删除状态
     */
    public final static Integer IS_DEL_FALG = 1;

}

```

枚举 Enums.java

```java
package com.tansci.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @path：com.tansci.common.constant.Enums.java
 * @className：Enums.java
 * @description：常用枚举
 * @author：tanyp
 * @dateTime：2021/10/22 16:14
 * @editNote：
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Enums {

    /**
     * 系统字典类型
     */
    SYS_DIC_TYPE(0, "dic_type", "系统类"),
    SERVE_DIC_TYPE(1, "dic_type", "业务类"),

    /**
     * 认证相关
     */
    AUTH_NO_TOKEN(401, "auth_type", "用户凭证已过期，请重新登录！"),
    AUTH_NO_ACCESS(403, "auth_type", "无访问权限，请核实!"),
    AUTH_NONEXISTENT(404, "auth_type", "请求路径不存在"),

    /**
     * 公共
     */
    NOT_DEL_FALG(0, "del_falg", "正常"),
    IS_DEL_FALG(1, "del_falg", "已删除"),

    /**
     * 角色类型
     */
    ROLE_SYSTEM(0, "role_type", "平台角色"),
    ROLE_NOT_SYSTEM(1, "role_type", "非平台角色"),

    /**
     * 用户信息相关
     */
    USER_GENDER_MALE(0, "user_gender", "男"),
    USER_GENDER_GIRL(1, "user_gender", "女"),

    /**
     * 消息状态
     */
    MESSAGE_UNTREATED(0, "message_status", "未处理"),
    MESSAGE_PROCESSED(1, "message_status", "已处理"),

    /**
     * 菜单类型
     */
    MENU_TYPE_0(0, "menu_type", "按钮"),
    MENU_TYPE_1(1, "menu_type", "菜单"),

    /**
     * 状态
     */
    STATUS_0(0, "status", "未启用"),
    STATUS_1(1, "status", "启用"),

    ;

    private Integer key;
    private String group;
    private String value;

    /**
     * @methodName：getValue
     * @description：根据key获取value
     * @author：tanyp
     * @dateTime：2021/7/18 13:09
     * @Params： [key]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getValue(Integer key) {
        for (Enums item : Enums.values()) {
            if (Objects.equals(key, item.key)) {
                return item.getValue();
            }
        }
        return null;
    }

    /**
     * @methodName：getVlaueByGroup
     * @description：根据key和group获取value
     * @author：tanyp
     * @dateTime：2021/7/18 13:09
     * @Params： [key, group]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getVlaueByGroup(Integer key, String group) {
        for (Enums item : Enums.values()) {
            if (Objects.equals(key, item.key) && Objects.equals(group, item.group)) {
                return item.getValue();
            }
        }
        return null;
    }

}

```

**2、在common目录下创建全局异常处理类**

全局异常统一处理 ExceptionHandle.java

```java
package com.tansci.common.exception;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName： ExceptionHandle.java
 * @ClassPath： com.tansci.common.exception.ExceptionHandle.java
 * @Description： 全局异常统一处理
 * @Author： tanyp
 * @Date： 2021/10/22 17:05
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    /**
     * @MonthName： handleException
     * @Description： 统一的异常处理方法
     * @Author： tanyp
     * @Date： 2021/10/22 17:16
     * @Param： [e]
     * @return： com.kuiper.qms.common.Wrapper
     **/
    @ExceptionHandler(value = Exception.class)
    public Wrapper handleException(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException ex = (BusinessException) e;
            log.error("系统自定义业务异常：{}", ex.getMessage());
            return WrapMapper.wrap(ex.getCode(), ex.getMessage(), null);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            log.error("参数校验异常：{}", ex.getBindingResult().getFieldError().getDefaultMessage());
            return WrapMapper.wrap(Wrapper.ILLEGAL_ARGUMENT_CODE_, "参数有误：" + ex.getBindingResult().getFieldError().getDefaultMessage(), null);
        } else {
            log.error("统一系统异常：{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE, null);
        }
    }

}

```

自定义业务异常处理 BusinessException.java

```java
package com.tansci.common.exception;

import lombok.Getter;

/**
 * @path：com.tansci.common.exception.BusinessException.java
 * @className：BusinessException.java
 * @description：业务异常处理
 * @author：tanyp
 * @dateTime：2021/10/22 17:27
 * @editNote：
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    private int code = 500;

    /**
     * 异常描述
     */
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

}

```

**3、在common目录下创建封装统一请求返回参数公共类**

包装类 Wrapper.java

```java
package com.tansci.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @path：com.tansci.common.Wrapper.java
 * @className：Wrapper.java
 * @description：包装类
 * @author：tanyp
 * @dateTime：2021/12/02 15:38
 * @editNote：
 */
public class Wrapper<T> implements Serializable {

    /**
     * 成功码.
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 成功信息.
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 错误码.
     */
    public static final int ERROR_CODE = 500;

    /**
     * 错误信息.
     */
    public static final String ERROR_MESSAGE = "系统异常，请稍后重试！";

    /**
     * 错误码：参数非法
     */
    public static final int ILLEGAL_ARGUMENT_CODE_ = 400;

    /**
     * 错误信息：参数非法
     */
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "请求参数非法，请核查！";

    /**
     * 错误码：参数非法
     */
    public static final int AUTHORIZATION_CODE = 403;

    /**
     * 错误信息：参数非法
     */
    public static final String AUTHORIZATION_MESSAGE = "用户凭证已过期，请重新登录！";

    /**
     * 编号.
     */
    private int code;

    /**
     * 信息.
     */
    private String message;

    /**
     * 结果数据
     */
    private T result;

    /**
     * Instantiates a new wrapper. default code=200
     */
    public Wrapper() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     */
    public Wrapper(int code, String message) {
        this.code(code).message(message);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     * @param result  the result
     */
    public Wrapper(int code, String message, T result) {
        super();
        this.code(code).message(message).result(result);
    }

    /**
     * Gets the 编号.
     *
     * @return the 编号
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the 编号.
     *
     * @param code the new 编号
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the 信息.
     *
     * @return the 信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the 信息.
     *
     * @param message the new 信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the 结果数据.
     *
     * @return the 结果数据
     */
    public T getResult() {
        return result;
    }

    /**
     * Sets the 结果数据.
     *
     * @param result the new 结果数据
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * Sets the 编号 ，返回自身的引用.
     *
     * @param code the new 编号
     * @return the wrapper
     */
    public Wrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    /**
     * Sets the 信息 ，返回自身的引用.
     *
     * @param message the new 信息
     * @return the wrapper
     */
    public Wrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * Sets the 结果数据 ，返回自身的引用.
     *
     * @param result the new 结果数据
     * @return the wrapper
     */
    public Wrapper<T> result(T result) {
        this.setResult(result);
        return this;
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.getCode() == Wrapper.SUCCESS_CODE;
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

}

```

返回包装类 WrapMapper.java

```java
package com.tansci.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @path：com.tansci.common.WrapMapper.java
 * @className：WrapMapper.java
 * @description：返回包装类
 * @author：tanyp
 * @dateTime：2021/12/02 15:38
 * @editNote：
 */
public class WrapMapper {

    private WrapMapper() {
    }

    public static <E> Wrapper<E> wrap(int code, String message, E o) {
        return new Wrapper<E>(code, message, o);
    }

    public static <E> Wrapper<E> wrap(int code, String message) {
        return new Wrapper<E>(code, message);
    }

    public static <E> Wrapper<E> wrap(int code) {
        return wrap(code, null);
    }

    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper<E>(Wrapper.ERROR_CODE, e.getMessage());
    }

    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getResult();
    }

    public static <E> Wrapper<E> illegalArgument() {
        return wrap(Wrapper.ILLEGAL_ARGUMENT_CODE_, Wrapper.ILLEGAL_ARGUMENT_MESSAGE);
    }

    public static <E> Wrapper<E> error() {
        return wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
    }

    public static <E> Wrapper<E> error(String message) {
        return wrap(Wrapper.ERROR_CODE, StringUtils.isBlank(message) ? Wrapper.ERROR_MESSAGE : message);
    }

    public static <E> Wrapper<E> error(int code, String message) {
        return wrap(code, StringUtils.isBlank(message) ? Wrapper.ERROR_MESSAGE : message);
    }

    public static <E> Wrapper<E> ok() {
        return new Wrapper<E>();
    }

    public static <E> Wrapper<E> wrap(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }

    public static <E> Wrapper<E> ok(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }

    public static <E> Wrapper<E> success() {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
    }

}

```

#### 整合Mybatis Plus

**1、添加依赖**

```java
<properties>
    <mybatis-plus-boot.version>3.4.3.1</mybatis-plus-boot.version>
</properties>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatis-plus-boot.version}</version>
</dependency>
```

**2、添加配置**

```yml
mybatis-plus:
  mapper-locations: classpath:mapper/**/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

**3、分页配置**

在```src/main/java/com/tansci/config/```目录下创建 ``MybatisPlusConfig.java`` 的分页配置。

```java
package com.tansci.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @path：com.tansci.config.MybatisPlusConfig.java
 * @className：MybatisPlusConfig.java
 * @description：MybatisPlus分页配置
 * @author：tanyp
 * @dateTime：2021/10/22 15:28
 * @editNote：
 */
@Configuration
@MapperScan("com.tansci.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }

}
```

**4、自定义日志**

为了在开发测试中方便定位问题，我们可以自定义打印日志格式、保存目录、日志级别等。在此项目中选择logback作为日志框架，并配置myBatis输出SQL语句。

在resources目录下新建 logback-spring.xml 文件，配置如下：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration scan="true" dudug="fasle" scanPeriod="5 minutes">

    <!-- ConsoleAppender 控制台输出日志 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- 对日志进行格式化 -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d %X{mdcData} [%thread] %-5level %c.%method %line - %msg%n</pattern>
        </layout>
    </appender>

    <!-- 配置myBatis输出SQL语句 -->
    <logger name="com.tansci.mapper" level="debug"/>

    <!-- 定义日志的根目录 -->
    <property name="LOG_HOME" value="logs" />

    <!-- ERROR级别日志 -->
    <!-- 滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件 RollingFileAppender-->
    <appender name="ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 过滤器，只记录WARN级别的日志 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <!-- 最常用的滚动策略，它根据时间来制定滚动策略.既负责滚动也负责出发滚动 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志输出位置  可相对、和绝对路径 -->
            <fileNamePattern>${LOG_HOME}/error-log-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <!-- 除按日志记录之外，还配置了日志文件不能超过5M，若超过5M，日志文件会以索引0开始，命名日志文件，例如log-error-2013-12-21.0.log -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>5MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <!-- 追加方式记录日志 -->
        <append>true</append>
        <!-- 日志文件的格式 -->
        <encoder>
            <pattern>%d %X{mdcData} [%thread] %-5level %c.%method %line - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <!-- INFO级别日志 appender -->
    <appender name="INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/info-log-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>5MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <append>true</append>
        <encoder>
            <pattern>%d %X{mdcData} [%thread] %-5level %c.%method %line - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <!-- DEBUG级别日志 appender -->
    <appender name="DEBUG" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>DEBUG</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/debug-log-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>5MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <append>true</append>
        <encoder>
            <pattern>%d %X{mdcData} [%thread] %-5level %c.%method %line - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <!-- root级别   DEBUG -->
    <root level="INFO">
        <!-- 控制台输出 -->
        <appender-ref ref="STDOUT" />

        <!-- 日志文件 -->
<!--        <appender-ref ref="ERROR" />-->
<!--        <appender-ref ref="INFO" />-->
<!--        <appender-ref ref="DEBUG" />-->
    </root>
</configuration>
```

启动项目验证Mybatis-Plus整合是否有问题，也可创建测试类对Mybatis-Plus进行单元测试。

#### 整合Spring Security安全认证

Spring Security是一套安全框架，可以基于RBAC（基于角色的权限控制）对用户的访问权限进行控制。它的核心功能主要包括：

- 认证
- 授权
- 攻击防护 （防止伪造身份）

其核心就是一组过滤器链，项目启动后将会自动配置。最核心的就是 Basic Authentication Filter 用来认证用户的身份，一个在Spring Security中一种过滤器处理一种认证方式。

> 在整合Spring Security前我们需要先实现用户、权限、组织的基础服务支持。

**1、添加依赖**

```xml
<properties>
    <jsch.version>0.1.55</jsch.version>
    <jjwt.version>0.9.0</jjwt.version>
</properties>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>com.jcraft</groupId>
    <artifactId>jsch</artifactId>
    <version>${jsch.version}</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>${jjwt.version}</version>
</dependency>
```

**2、创建Security配置**

在SpringBoot应用中使用Spring Security，用到 @EnableWebSecurity注解，该注解和@Configuration注解一起使用。利用@EnableWebSecurity注解继承WebSecurityConfigurerAdapter类，这样就构成了 Spring Security的配置。

WebSecurityConfigurerAdapter提供了一种便利的方式去创建WebSecurityConfigurer的实例，只需要重写 WebSecurityConfigurerAdapter的方法，即可配置拦截什么URL、设置什么权限等安全控制。

- configure(WebSecurity)：通过重载该方法，可配置Spring Security的Filter链。

| 方法 | 说明 |
| ---- | ---- |
| access(String) | 如果给定的SpEL表达式计算结果为true，则允许访问 |
| anonymous() | 允许匿名用户访问 |
| authenticated() | 允许认证过的用户访问 |
| denyAll()	| 无条件拒绝所有访问 |
| fullyAuthenticated() | 如果用户是完整认证的话（不是通过Remember-me功能认证的），则允许访问 |
| hasAnyAuthority(String…) | 如果用户具备给定权限中的某一个的话，则允许访问 |
| hasAnyRole(String…) | 如果用户具备给定角色中的某一个的话，则允许访问 |
| hasAuthority(String) | 如果用户具备给定权限的话，则允许访问 |
| hasIpAddress(String) | 如果请求来自给定IP地址的话，则允许访问 |
| hasRole(String) | 如果用户具备给定角色的话，则允许访问 |
| not()	| 对其他访问方法的结果求反 |
| permitAll() | 无条件允许访问 |
| rememberMe() | 如果用户是通过Remember-me功能认证的，则允许访问 |

- configure(HttpSecurity)：通过重载该方法，可配置如何通过拦截器保护请求。

| 方法 | 说明 |
| ---- | ---- |
| authentication | 用户认证对象 |
| denyAll | 结果始终为false |
| hasAnyRole(list of roles) | 如果用户被授权指定的任意权限，结果为true |
| hasRole(role)	| 如果用户被授予了指定的权限，结果 为true |
| hasIpAddress(IP Adress) | 用户地址 |
| isAnonymous() | 是否为匿名用户 |
| isAuthenticated() | 不是匿名用户 |
| isFullyAuthenticated | 不是匿名也不是remember-me认证 |
| isRemberMe() | remember-me认证 |
| permitAll	| 始终true |
| principal	| 用户主要信息对象 |

- configure(AuthenticationManagerBuilder)：通过重载该方法，可配置user-detail（用户详细信息）服务。

| 方法 | 说明 |
| ---- | ---- |
| accountExpired(boolean) | 定义账号是否已经过期 |
| accountLocked(boolean) | 定义账号是否已经锁定 |
| and()	| 用来连接配置 |
| authorities(GrantedAuthority…) | 授予某个用户一项或多项权限 |
| authorities(List)	| 授予某个用户一项或多项权限 |
| authorities(String…) | 授予某个用户一项或多项权限 |
| credentialsExpired(boolean) | 定义凭证是否已经过期 |
| disabled(boolean) | 定义账号是否已被禁用 |
| password(String) | 定义用户的密码 |
| roles(String…) | 授予某个用户一项或多项角色 |

如下安全配置，使得只有认证过的用户才可以正常访问业务服务。

```java
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * @MonthName： passwordEncoder
     * @Description： 加密密码
     * @Author： tanyp
     * @Date： 2021/10/22 16:14
     * @Param： []
     * @return： org.springframework.security.crypto.password.PasswordEncoder
     **/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler())
                .and()
                // 配置登出路径
                .logout().logoutUrl("/user/logout").logoutSuccessHandler(new JWTLogoutSuccessHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
```

**3、实现身份认证**

在DaoAuthenticationProvider中包含了一个UserDetailsService实例，它负责根据用户名提取用户信息UserDetailService实例，根据用户名提取用户信息UserDetails(含密码)。 

DaoAuthenticationProvider会去对比UserDetailsService提取的用户密码与用户提交的密码是否匹配作为认证成功的关键依据，因此可以通过自定义UserDetailsService的实现类来实现自定义的身份认证。

创建UserDetailsServiceImpl文件需要实现UserDetailsService接口，重写loadUserByUsername方法实现用户登录认证。

```java
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = sysUserService.login(username);
        if (Objects.isNull(user)) {
            throw new BusinessException("用户名获取密码有误！");
        }
        return new SecurityUtils(user);
    }

}
```

SysUserService创建login方法实现用户是否存在验证、用户权限验证、用户归属组织验证，验证通过后将用户信息、权限、组织存储在session中。

```java
public SysUser login(String username) {
    SysUser user = this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
    if (Objects.nonNull(user)) {
        // 获取角色
        SysUserRole role = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, user.getId()));
        log.error("====={}=======无权限================", username);
        if (Objects.isNull(role) || Objects.isNull(role.getRoleId())) {
            throw new BusinessException("暂无登录权限，请联系管理员！");
        }
        user.setRole(String.valueOf(role.getRoleId()));

        // 获取组织
        SysUserOrg org = sysUserOrgService.getOne(Wrappers.<SysUserOrg>lambdaQuery().eq(SysUserOrg::getUserId, user.getId()));
        if (Objects.isNull(org) || Objects.isNull(org.getOrgId())) {
            log.error("====={}=======无组织================", username);
            throw new BusinessException("暂无登录权限，请联系管理员！");
        }

        // 获取组织权限
        List<SysOrg> orgs = sysOrgService.getOrgChildrens(org.getOrgId());
        if (Objects.nonNull(orgs) && orgs.size() > 0) {
            List<Integer> orgIds = orgs.stream().map(SysOrg::getId).collect(Collectors.toList());
            orgIds.add(org.getOrgId());
            user.setOrgIds(orgIds);
        } else {
            user.setOrgIds(Arrays.asList(org.getOrgId()));
        }
    }
    return user;
}
```

**4、创建SecurityUtils**

UserDetails和Authentication接口很类似，它们都拥有username，authorities。

Authentication的getCredentials()与UserDetails中的getPassword()需要被区分对待，前者是用户提交的密码凭证，后者是用户实际存储的密码，认证其实就是对这两者的比对。Authentication中的getAuthorities()实际是由UserDetails的getAuthorities()传递而形成的。

UserDetails中用户详细信息(Details)便是经过了AuthenticationProvider认证之后被填充的。通过实现UserDetailsService和UserDetails，我们可以完成对用户信息获取方式以及用户信息字段的自定义。

```java
@Data
public class SecurityUtils implements UserDetails {

    private String id;

    private String username;

    private String nickname;

    private String password;

    private Integer type;

    private Integer orgId;

    private List<Integer> orgIds;

    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUtils() {
    }

    public SecurityUtils(SysUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.type = user.getType();
        this.orgId = user.getOrgId();
        this.orgIds = user.getOrgIds();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 账号是否未过期，默认是false
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账号是否未锁定，默认是false
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 账号凭证是否未过期，默认是false
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
```

**5、创建JwtTokenUtils工具类**

通过JWT进行安全验证，需要封装工具类。主要包含：

- 创建token方法：createToken(SysUser user, boolean isRememberMe)
- 从token中获取用户名：getUsername(String token)
- 获取用户角色：getUserRole(String token)
- 获取用户详细信息：getUser(String token)
- 验证token是否过期：isExpiration(String token)

生产的token格式为 ```Authorization:Bearer + 字符串```，例如：

```java
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJqZWN0IiwicGF5bG9hZCI6IntcImFjY291bnROb25FeHBpcmVkXCI6dHJ1ZSxcImFjY291bnROb25Mb2NrZWRcIjp0cnVlLFwiYXV0aG9yaXRpZXNcIjpbe1wiYXV0aG9yaXR5XCI6XCJcL21zcy11cG1zXC9vcmRlclwvbGlzdFwifSx7XCJhdXRob3JpdHlcIjpcIlwvbXNzLXVwbXNcL29yZGVyXC9kZXRhaWxcIn0se1wiYXV0aG9yaXR5XCI6XCJST0xFX2FkbWluXCJ9XSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJlbmFibGVkXCI6dHJ1ZSxcImlkXCI6NDgsXCJwYXNzd29yZFwiOlwiJDJhJDEwJHZtcC56V1duWDNMRnhTczZJMDBpMGV1cmxIUjd5bWNmVVE1SHRYdzcxdzlRSi4ySlVmOFVhXCIsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIn0iLCJleHAiOjE2MDYxMzA4MDd9.Wb-2UkAcVrj4KbQteT6D9RbktXgkPLI-tB5ymMkqsjI

````

```java
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtsecretdemo";

    private static final String ISS = "echisan";

    // 角色的key
    private static final String ROLE_CLAIMS = "rol";

    // 用户信息key
    private static final String USER_CLAIMS = "user";

    // 过期时间是3600*4秒，既4个小时
    private static final long EXPIRATION = 14400L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * @MonthName： createToken
     * @Description： 创建token
     * @Author： tanyp
     * @Date： 2021/12/02 10:40
     * @Param： [username, isRememberMe]
     * @return： java.lang.String
     **/
    public static String createToken(SysUser user, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, user.getRole());
        map.put(USER_CLAIMS, user);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * @MonthName： getUsername
     * @Description： 从token中获取用户名
     * @Author： tanyp
     * @Date： 2021/12/02 10:41
     * @Param： [token]
     * @return： java.lang.String
     **/
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * @MonthName： getUserRole
     * @Description： 获取用户角色
     * @Author： tanyp
     * @Date： 2021/12/02 13:00
     * @Param： [token]
     * @return： java.lang.String
     **/
    public static String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    /**
     * @MonthName： getUser
     * @Description： 获取用户信息
     * @Author： tanyp
     * @Date： 2021/12/02 13:00
     * @Param： [token]
     * @return： java.lang.String
     **/
    public static SysUser getUser(String token) {
        Object obj = getTokenBody(token).get(USER_CLAIMS);
        return JSONObject.parseObject(JSONObject.toJSONBytes(obj), SysUser.class);
    }

    /**
     * @MonthName： isExpiration
     * @Description： 是否已过期
     * @Author： tanyp
     * @Date： 2021/12/02 10:41
     * @Param： [token]
     * @return： boolean
     **/
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }

    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
```

**6、配置拦截器, 用户权限验证**

当进来一个请求时，从Authorization中获取参数，进而解析获取到其中的账户和密码信息，进行认证。认证成功后返回的Authtication对象信息，会进入下一个filter，由于已经认证成功了会直接进入FilterSecurityInterceptor进行权限验证。

doFilterInternal方法从Header中获取Authorization参数信息，然后调用认证，认证成功后最后直接访问接口。

创建JWTAuthorizationFilter继承BasicAuthenticationFilter父类。

```java
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (token == null || !token.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            onSuccessfulAuthentication(request, response, authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            onUnsuccessfulAuthentication(request, response, new AccountExpiredException(Enums.AUTH_NO_TOKEN.getValue()));
        }
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        log.info("=============Token 验证成功=================");
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.error("================token校验失败=======================");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(WrapMapper.error(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage())));
    }


    /**
     * @MonthName： getAuthentication
     * @Description： 从token中获取用户信息并新建一个token
     * @Author： tanyp
     * @Date： 2021/10/22 17:55
     * @Param： [tokenHeader]
     * @return： org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     **/
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new BusinessException(Enums.AUTH_NO_TOKEN.getValue());
        } else {
            String username = JwtTokenUtils.getUsername(token);
            String role = JwtTokenUtils.getUserRole(token);
            SysUser user = JwtTokenUtils.getUser(token);
            if (username != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
                authenticationToken.setDetails(user);
                return authenticationToken;
            }
        }
        return null;
    }

}
```

**7、配置拦截器, 用户账号验证**

通过UsernamePasswordAuthenticationFilter获取请求中的账户密码来进行授权的filter，这个filter的主要职责为：

- 通过 requiresAuthentication()判断是否以POST方式请求/login;
- 调用 attemptAuthentication()方法进行认证，内部创建了authenticated属性为false（即未授权）的UsernamePasswordAuthenticationToken 对象，并传递给 AuthenticationManager().authenticate() 方法进行认证，认证成功后 返回一个authenticated=true（即授权成功的)UsernamePasswordAuthenticationToken对象;
- 通过 sessionStrategy.onAuthentication()将Authentication放入Session中;
- 通过 successfulAuthentication()调用AuthenticationSuccessHandler.onAuthenticationSuccess()接口进行成功处理（可以通过继承AuthenticationSuccessHandler自行编写成功处理逻辑）successfulAuthentication(request, response, chain, authResult);
- 通过 unsuccessfulAuthentication()调用AuthenticationFailureHandler.onAuthenticationFailure接口进行失败处理（可以通过继承AuthenticationFailureHandler自行编写失败处理逻辑）。

在构造方法中可设置登录服务路径，默认是"/login"

```java
public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    /**
    * 设置登录路径
    */
    super.setFilterProcessesUrl("/user/login");
}
```

```java
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        /**
         * 默认登录是 ‘/login’
         */
        super.setFilterProcessesUrl("/user/login");
    }

    /**
     * @MonthName： attemptAuthentication
     * @Description： 获取登录信息
     * @Author： tanyp
     * @Date： 2021/10/22 17:46
     * @Param： [request, response]
     * @return： org.springframework.security.core.Authentication
     **/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        SysUser user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
        } catch (IOException e) {
            log.error("=======获取登录信息异常：{}", e);
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    /**
     * @MonthName： successfulAuthentication
     * @Description： 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     * @Author： tanyp
     * @Date： 2021/10/22 17:46
     * @Param： [request, response, chain, authResult]
     * @return： void
     **/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        SecurityUtils user = (SecurityUtils) authResult.getPrincipal();
        String role = "";
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }

        // 创建token
        String token = JwtTokenUtils.createToken(
                SysUser.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .type(user.getType())
                        .orgId(user.getOrgId())
                        .orgIds(user.getOrgIds())
                        .role(role)
                        .build(), false);

        // 创建成功的token, 请求的格式应该是 `Bearer token`
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(WrapMapper.success().result(
                SysUserVo.builder()
                        .username(user.getUsername())
                        .nickname(user.getNickname())
                        .token(token)
                        .loginTime(LocalDateTime.now()).build()
        )));
    }

    /**
     * @MonthName： unsuccessfulAuthentication
     * @Description： 这是验证失败时候调用的方法
     * @Author： tanyp
     * @Date： 2021/10/22 17:46
     * @Param： [request, response, failed]
     * @return： void
     **/
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.info("===========登录认证失败：{}=============", failed.getMessage());
        Wrapper result = null;
        if (failed instanceof UsernameNotFoundException) {
            result = WrapMapper.error(Enums.AUTH_NONEXISTENT.getKey(), Enums.AUTH_NONEXISTENT.getValue());
        } else if (failed instanceof BadCredentialsException) {
            result = WrapMapper.error(Enums.AUTH_NO_TOKEN.getKey(), Enums.AUTH_NO_TOKEN.getValue());
        } else if (failed instanceof InternalAuthenticationServiceException) {
            String message = failed.getMessage() != null ? failed.getMessage() : Wrapper.ERROR_MESSAGE;
            result = WrapMapper.error(Enums.AUTH_NO_ACCESS.getKey(), message);
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }

}
```

**8、没有访问权限时处理**

```java
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(WrapMapper.wrap(Wrapper.AUTHORIZATION_CODE, Wrapper.AUTHORIZATION_MESSAGE, null)));
    }

}
```

**9、没有携带token或者token无效时处理**

```java
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(WrapMapper.wrap(Wrapper.AUTHORIZATION_CODE, Wrapper.AUTHORIZATION_MESSAGE, null)));
    }

}
```

**10、登出处理**

```java
@Slf4j
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("========退出成功===========");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, null)));
    }

}
``` 

**11、封装获取已登录的用户信息**

```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUserUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static SysUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SysUser) authentication.getDetails();
    }

}
```

在项目中使用示例

```java
SysUser user = SecurityUserUtils.getUser();
```

#### 整合Spring AOP 记录操作、异常日志

我们在做项目时经常需要对一些重要功能操作记录日志，方便以后跟踪是谁在操作此功能。

在操作某些功能时也有可能会发生异常，但是每次发生异常要定位原因都要到服务器去查询日志才能找到，这样也不能对发生的异常进行统计。

我们可以在需要的方法中增加记录日志的代码，和在每个方法中增加记录异常的代码，最终把记录的日志存到数据库中。

Spring AOP 的主要功能就是将日志记录，性能统计，安全控制，事务处理，异常处理等代码从业务逻辑代码中划分出来。

**1、添加依赖**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

**2、自定义操作日志注解**

自定义定义记录日志的注解，在需要记录日志的方法上添加该注解就可以实现记录日志的功能。

```java
package com.demo.utils.annotation;

import java.lang.annotation.*;

/**
 * @path：com.demo.utils.annotation.Log.java
 * @className：Log.java
 * @description：自定义操作日志注解
 * @author：tanyp
 * @dateTime：2021/11/18 13:53 
 * @editNote：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 操作模块
     * @return
     */
    String modul() default "";

    /**
     * 操作类型
     * @return
     */
    String type() default "";

    /**
     * 操作说明
     * @return
     */
    String desc() default "";

}
```

**3、切面处理，记录日志**

记录日志有几个主要的字段需要注意下，操作类型、耗时、操作用户信息、请求IP、版本号这几个字段需要提前处理下。

- 操作类型：一般对数据操作有新增、删除、更新、查询四种方式，可以配置在之前创建好的常量工具类中，方便我们使用。
  
```java
/**
* 接口类型
*/
public final static String SELECT = "SELECT";

public final static String INSERT = "INSERT";

public final static String UPDATE = "INSERT";

public final static String DELETE = "DELETE";

```

- 耗时：需要定义一个ThreadLocal，来记录请求的开始时间startTime，在请求结束后与当前时间求差即可。

```java
/**
* 统计请求的处理时间
*/
ThreadLocal<Long> startTime = new ThreadLocal<>();

```

- 操作用户信息：可从session中获取，使用 SecurityUserUtils 工具即可获得。

```java
// 用户ID
String userId = SecurityUserUtils.getUser().getId();

// 用户名称
String username = SecurityUserUtils.getUser().getUsername();

```

- 请求IP：需要我们写一个工具类来获取请求的IP地址。

```java
package com.tansci.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @path：com.tansci.utils.IPUtils.java
 * @className：IPUtils.java
 * @description：IP工具类
 * @author：tanyp
 * @dateTime：2021/12/02 14:19
 * @editNote：
 */
public class IPUtils {

    /**
     * @methodName：getIpAddress
     * @description：获取IP地址
     * @author：tanyp
     * @dateTime：2021/12/02 14:20
     * @Params： [request]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

}

```
  
- 版本号：版本号在配置文件中配置一下即可。

```yml
# 版本号
version: 1.1.0
```

在编写切面处理类之前还需实现操作日志、异常日志的基本服务支持。

实现具体的切面处理类 ```LogAspect.java```

SpringBoot获取配置文件中配置好的版本号只需一个注解就可以轻松获取到。

```java
@Value("${version}")
private String version;

```

接收到请求，记录请求开始时间。

```java
@Before("logPoinCut()")
public void doBefore() {
    startTime.set(System.currentTimeMillis());
}
```

设置操作日志记录的切入点，在注解的位置切入代码，需要用到我们自定义的注解完整路径 ```com.tansci.common.annotation.Log```。

```java
/**
* @methodName：logPoinCut
* @description：设置操作日志记录的切入点
* @author：tanyp
* @dateTime：2021/12/02 14:22
* @Params： []
* @Return： void
* @editNote：
*/
@Pointcut("@annotation(com.tansci.common.annotation.Log)")
public void logPoinCut() {
}
```

设置操作异常日志记录的切入点，扫描所有controller包下相关操作。

```java
/**
* @methodName：exceptionLogPoinCut
* @description：设置操作异常日志记录的切入点
* @author：tanyp
* @dateTime：2021/12/02 14:22
* @Params： []
* @Return： void
* @editNote：
*/
@Pointcut("execution(* com.tansci.controller..*.*(..))")
public void exceptionLogPoinCut() {
}
```

请求正常返回通知，连接点正常执行完成后执行记录操作日志的相关代码逻辑。 如果连接点抛出异常，则不会执行。

```java
/**
* @methodName：doAfterReturning
* @description：正常返回通知
* @author：tanyp
* @dateTime：2021/12/02 14:21
* @Params： [joinPoint, keys]
* @Return： void
* @editNote：
*/
@AfterReturning(value = "logPoinCut()", returning = "keys")
public void doAfterReturning(JoinPoint joinPoint, Object keys) {
    // 获取RequestAttributes
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

    // 从获取RequestAttributes中获取HttpServletRequest的信息
    HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

    LogInfo logInfo = LogInfo.builder().build();
    try {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 获取切入点所在的方法
        Method method = signature.getMethod();

        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();

        // 获取操作
        Log log = method.getAnnotation(Log.class);
        if (Objects.nonNull(log)) {
            logInfo.setModule(log.modul());
            logInfo.setType(log.type());
            logInfo.setMessage(log.desc());
        }

        logInfo.setId(UUID.randomUUID().toString());
        logInfo.setMethod(className + "." + method.getName()); // 请求的方法名
        logInfo.setReqParam(JSON.toJSONString(converMap(request.getParameterMap()))); // 请求参数
        logInfo.setResParam(JSON.toJSONString(keys)); // 返回结果
        logInfo.setUserId(SecurityUserUtils.getUser().getId()); // 请求用户ID
        logInfo.setUserName(SecurityUserUtils.getUser().getUsername()); // 请求用户名称
        logInfo.setIp(IPUtils.getIpAddress(request)); // 请求IP
        logInfo.setUri(request.getRequestURI()); // 请求URI
        logInfo.setCreateTime(LocalDateTime.now()); // 创建时间
        logInfo.setVersion(version); // 操作版本
        logInfo.setTakeUpTime(System.currentTimeMillis() - startTime.get()); // 耗时
        logInfoService.save(logInfo);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

```

当请求异常返回通知，连接点抛出异常后执行记录异常日志相关代码逻辑。

```java
/**
* @methodName：doAfterThrowing
* @description：异常返回通知
* @author：tanyp
* @dateTime：2021/12/02 14:23
* @Params： [joinPoint, e]
* @Return： void
* @editNote：
*/
@AfterThrowing(pointcut = "exceptionLogPoinCut()", throwing = "e")
public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    // 获取RequestAttributes
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

    // 从获取RequestAttributes中获取HttpServletRequest的信息
    HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

    try {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 获取切入点所在的方法
        Method method = signature.getMethod();

        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();

        logErrorInfoService.save(
                LogErrorInfo.builder()
                        .id(UUID.randomUUID().toString())
                        .reqParam(JSON.toJSONString(converMap(request.getParameterMap()))) // 请求参数
                        .method(className + "." + method.getName()) // 请求方法名
                        .name(e.getClass().getName()) // 异常名称
                        .message(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())) // 异常信息
                        .userId(SecurityUserUtils.getUser().getId()) // 操作员ID
                        .userName(SecurityUserUtils.getUser().getUsername()) // 操作员名称
                        .uri(request.getRequestURI()) // 操作URI
                        .ip(IPUtils.getIpAddress(request)) // 操作员IP
                        .version(version) // 版本号
                        .createTime(LocalDateTime.now()) // 发生异常时间
                        .build()
        );
    } catch (Exception e2) {
        e2.printStackTrace();
    }
}

```

封装请求参数转换方法和异常信息转字符串方法。

```java
/**
* @methodName：converMap
* @description：转换request 请求参数
* @author：tanyp
* @dateTime：2021/12/02 14:12
* @Params： [paramMap]
* @Return： java.util.Map<java.lang.String, java.lang.String>
* @editNote：
*/
public Map<String, String> converMap(Map<String, String[]> paramMap) {
    Map<String, String> rtnMap = new HashMap<String, String>();
    for (String key : paramMap.keySet()) {
        rtnMap.put(key, paramMap.get(key)[0]);
    }
    return rtnMap;
}

/**
* @methodName：stackTraceToString
* @description：转换异常信息为字符串
* @author：tanyp
* @dateTime：2021/12/02 14:12
* @Params： [exceptionName, exceptionMessage, elements]
* @Return： java.lang.String
* @editNote：
*/
public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
    StringBuffer strbuff = new StringBuffer();
    for (StackTraceElement stet : elements) {
        strbuff.append(stet + "<br/>");
    }
    String message = exceptionName + ":" + exceptionMessage + "<br/>" + strbuff.toString();
    return message;
}

```

> 至此后端项目安全认证、日志记录已全部整合完成。

## 任务调度

1. 实现 `TaskRegisterService` 接口，创建业务实现即可。

例如：

```java
/**
 * @ClassName： TaskTest1ServiceImpl.java
 * @ClassPath： com.tansci.service.impl.system.task.TaskTest1ServiceImpl.java
 * @Description： 自定义任务测试1
 * @Author： tanyp
 * @Date： 2022/2/25 10:08
 **/
@Slf4j
@Service("taskTest1Service")
public class TaskTest1ServiceImpl implements TaskRegisterService {

    @Override
    public void register() {
        log.info("===========自定义任务测试【TaskTest1ServiceImpl】====【1】=========");
    }
}
```
2. 在界面配置实现的任务服务即可。

**注意：**

- `@Service("taskTest1Service") ` 是唯一的，对应 `task_config` 表中的 code 字段；
- expression 的配置为 cron 表达式。

3. cron表达式

corn从左到右（用空格隔开）：秒 分 小时 月份中的日期 月份 星期中的日期 年份。

| 字段 | 允许值 | 允许的特殊字符 |
| ---- | ---- | ---- |
| 秒（Seconds） | 0~59的整数 | , - * / |
| 分（Minutes） | 0~59的整数 | , - * / |
| 小时（Hours） | 0~23的整数 | , - * / |
| 日期（DayofMonth） | 1~31的整数 | ,- * ? / L W C |
| 月份（Month） | 1~12的整数或者 JAN-DEC | , - * / |
| 星期（DayofWeek） | 1~7的整数或者 SUN-SAT （1=SUN） | , - * ? / L C # |
| 年(可选，留空)（Year） | 1970~2099 | , - * / |

- `*`：表示匹配该域的任意值。假如在Minutes域使用*, 即表示每分钟都会触发事件。
- `?`：只能用在DayofMonth和DayofWeek两个域。
- `-`：表示范围。例如在Minutes域使用5-20，表示从5分到20分钟每分钟触发一次
- `/`：表示起始时间开始触发，然后每隔固定时间触发一次。
- `,`：表示列出枚举值。例如：在Minutes域使用5,20，则意味着在5和20分每分钟触发一次。
- `L`：表示最后，只能出现在DayofWeek和DayofMonth域。
- `W`:表示有效工作日(周一到周五),只能出现在DayofMonth域，系统将在离指定日期的最近的有效工作日触发事件。
- `LW`:这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五。
- `#`:用于确定每个月第几个星期几，只能出现在DayofMonth域。例如在4#2，表示某月的第二个星期三。

常用表达式例子

- `0 0 2 1 * ? *` 表示在每月的1日的凌晨2点调整任务
- `0 15 10 ? * MON-FRI` 表示周一到周五每天上午10:15执行作业
- `0 15 10 ? 6L 2002-2006` 表示2002-2006年的每个月的最后一个星期五上午10:15执行作
- `0 0 10,14,16 * * ?` 每天上午10点，下午2点，4点
- `0 0/30 9-17 * * ?` 朝九晚五工作时间内每半小时
- `0 0 12 ? * WED` 表示每个星期三中午12点
- `0 0 12 * * ?` 每天中午12点触发
- `0 15 10 ? * *` 每天上午10:15触发
- `0 15 10 * * ?`  每天上午10:15触发
- `0 15 10 * * ? *` 每天上午10:15触发
- `0 15 10 * * ? 2005` 2005年的每天上午10:15触发
- `0 * 14 * * ?` 在每天下午2点到下午2:59期间的每1分钟触发
- `0 0/5 14 * * ?` 在每天下午2点到下午2:55期间的每5分钟触发
- `0 0/5 14,18 * * ?` 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
- `0 0-5 14 * * ?` 在每天下午2点到下午2:05期间的每1分钟触发
- `0 10,44 14 ? 3 WED` 每年三月的星期三的下午2:10和2:44触发
- `0 15 10 ? * MON-FRI` 周一至周五的上午10:15触发
- `0 15 10 15 * ?` 每月15日上午10:15触发
- `0 15 10 L * ?` 每月最后一日的上午10:15触发
- `0 15 10 ? * 6L` 每月的最后一个星期五上午10:15触发
- `0 15 10 ? * 6L 2002-2005` 2002年至2005年的每月的最后一个星期五上午10:15触发
- `0 15 10 ? * 6#3` 每月的第三个星期五上午10:15触发