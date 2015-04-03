1.	安装Java JDK 1.7或以上版本
下载地址：（好像需要注册个oracle帐号才能下载）
http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
下载好了之后安装
然后是环境变量设置，设置步骤如下

1.首先，打开配置环境变量界面，如下操作：我的电脑---属性---高级---环境变量 
2.系统变量（S）中配置如下： 
  2.1,新建系统变量： 
  变量名：JAVA_HOME 
  变量值：C:\Program Files\Java\jdk1.5.0_17（该目录为JDK安装的目录） 
  2.2,编辑CLASSPATH，如果没有该变量则新建， 
  变量名：CLASSPATH 
  变量值：%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;（注：该变量值置于CLASSPATH即可， 
  其中：变量值最后要记得加“；”） 
  2.3,编辑PATH，如果没有则新建 
  变量名：PATH 
  变量值：.;%JAVA_HOME%\bin; （注：该变量值置于PATH前面，如果你只有一个JDK版本，可以不用编辑PATH，如果为了区别版本，那还是配置PATH变量值）。

测试结果：新开一个cmd，运行java -version
运行javac -version 结果像下面这样
E:\codes\water8cups>javac -version
javac 1.7.0_45

2.	安装Maven
下载Maven : http://maven.apache.org/download.cgi
直接解压放在某目录下，如E:\opensource\maven
设置环境变量（将E:\opensource\maven\apache-maven-3.2.1\bin目录添加到Path变量中）
然后新打开一个cmd，运行mvn -version命令，有如下结果说明成功：
E:\codes\water8cups>mvn -version
Apache Maven 3.2.1 (ea8b2b07643dbb1b84b6d16e1f08391b666bc1e9; 2014-02-15T01:37:52+08:00)
Maven home: E:\opensource\maven\apache-maven-3.2.1\bin\..
Java version: 1.7.0_45, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.7.0_45\jre
Default locale: en_US, platform encoding: GBK
OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"

3. 配置数据库连接
3.1 water8cups\src\main\resources\META-INF\persistence.xml
找到SQL Server部分，将用户名和密码/IP设置成本地对应的SQL Server
3.2 water8cups\src\main\webapp\WEB-INF\applicationContext.xml
同样找到SQL Server部分，更改用户名，密码，IP等配置。

4. 在SQL Server上运行water8cups\sample.sql

5.运行程序
5.1 E:\codes\water8cups>mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar
5.2 E:\codes\water8cups>mvn clean tomcat7:run
运行如果没错，就可以通过浏览器访问：
http://localhost:8080/healthmanager/rest/appuser/users
http://localhost:8080/healthmanager/rest/appuser/users/1

6. 示例代码参考
water8cups\src\main\java\com\bigyellow\hm\entity\AppUser.java   实例类
water8cups\src\main\java\com\bigyellow\hm\dao\AppUserDao.java 数据库接口类
water8cups\src\main\java\com\bigyellow\hm\daoAppUserDaoImpl 数据库实现类
water8cups\src\main\java\com\bigyellow\hm\rs\AppUserResource.java REST接口实现类
water8cups\src\main\java\com\bigyellow\hm\rsconfig\MyApplication REST接口注册





