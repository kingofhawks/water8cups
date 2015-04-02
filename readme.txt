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


2.	安装Git
命令行工具下载：http://git-scm.com/download/
安装好之后在命令行运行  （为了在命令行下运行，你需要把git的bin目录放在环境变量path里面）
E:\codes> git clone https://aaronhuang@bitbucket.org/aaronhuang/water8cups.git
就会提示你输入bitbucket帐号/密码 ， 代码就pull下来到E:\codes\water8cups目录
注意：如果遇到有ssl验证错误的问题，需要做如下配置
E:\codes> git config --global http.sslverify false
此步可以通过编辑.gitconfig文件替代，在C:\Users\aaronhuang\.gitconfig文件中添加如下：
[http]
	sslVerify = false
你的.gitconfig文件可以参考如下配置：
[user]
	name = huangzhengyu
	email = aaron.yu.huang@gmail.com
[http]
	sslVerify = false
[credential]
	helper = store

3.	安装Maven
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

4.	运行健康8杯水：
E:\codes\water8cups>mvn clean tomcat7:run

注意更改I:\qiezilife\codes\water8cups\src\main\resources\META-INF\persistence.xml
跟I:\qiezilife\codes\water8cups\src\main\webapp\WEB-INF\applicationContext.xml
这两个文件中数据库连接的配置:分成几个模式product mode（产品）/online test mode（产品测试）/local test mode（本地测试）
本地测试可以选择内存数据库或者MySQL数据库

5. 部署
产品模式：对应于“茄子运动”这个微信公众号码，数据库使用qz_wechat , 应用名字为"healthmanager"
产品测试模式：对应于“茄星人快跑”这个微信公众号码，数据库使用qz_wechat_test,应用名字为"healthmanager2"

在使用产品测试模式时，需要更改的文件包括：
1. /pom.xml 中的<project.name>healthmanager</project.name> --> <project.name>healthmanager2</project.name>
2. /src/main/webapp/WEB-INF/applicationContext.xml 中 使用online test mode（就是将此mode的注释去掉，将其他mode注释加上）
3. /src/main/resources/META-INF/persistence.xml 中使用 online test mode （就是将此mode的注释去掉，将其他mode注释加上）

注意，如果更改成产品测试模式后，eclipse里出现什么问题，或者mvn clean tomcat7:run 命令跑不起来，可以先跑mvn eclipse:eclipse命令




