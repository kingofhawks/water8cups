1.	��װJava JDK 1.7�����ϰ汾
���ص�ַ����������Ҫע���oracle�ʺŲ������أ�
http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
���غ���֮��װ
Ȼ���ǻ����������ã����ò�������

1.���ȣ������û����������棬���²������ҵĵ���---����---�߼�---�������� 
2.ϵͳ������S�����������£� 
  2.1,�½�ϵͳ������ 
  ��������JAVA_HOME 
  ����ֵ��C:\Program Files\Java\jdk1.5.0_17����Ŀ¼ΪJDK��װ��Ŀ¼�� 
  2.2,�༭CLASSPATH�����û�иñ������½��� 
  ��������CLASSPATH 
  ����ֵ��%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;��ע���ñ���ֵ����CLASSPATH���ɣ� 
  ���У�����ֵ���Ҫ�ǵüӡ������� 
  2.3,�༭PATH�����û�����½� 
  ��������PATH 
  ����ֵ��.;%JAVA_HOME%\bin; ��ע���ñ���ֵ����PATHǰ�棬�����ֻ��һ��JDK�汾�����Բ��ñ༭PATH�����Ϊ������汾���ǻ�������PATH����ֵ����

���Խ�����¿�һ��cmd������java -version
����javac -version �������������
E:\codes\water8cups>javac -version
javac 1.7.0_45


2.	��װGit
�����й������أ�http://git-scm.com/download/
��װ��֮��������������  ��Ϊ���������������У�����Ҫ��git��binĿ¼���ڻ�������path���棩
E:\codes> git clone https://aaronhuang@bitbucket.org/aaronhuang/water8cups.git
�ͻ���ʾ������bitbucket�ʺ�/���� �� �����pull������E:\codes\water8cupsĿ¼
ע�⣺���������ssl��֤��������⣬��Ҫ����������
E:\codes> git config --global http.sslverify false
�˲�����ͨ���༭.gitconfig�ļ��������C:\Users\aaronhuang\.gitconfig�ļ���������£�
[http]
	sslVerify = false
���.gitconfig�ļ����Բο��������ã�
[user]
	name = huangzhengyu
	email = aaron.yu.huang@gmail.com
[http]
	sslVerify = false
[credential]
	helper = store

3.	��װMaven
����Maven : http://maven.apache.org/download.cgi
ֱ�ӽ�ѹ����ĳĿ¼�£���E:\opensource\maven
���û�����������E:\opensource\maven\apache-maven-3.2.1\binĿ¼��ӵ�Path�����У�
Ȼ���´�һ��cmd������mvn -version��������½��˵���ɹ���
E:\codes\water8cups>mvn -version
Apache Maven 3.2.1 (ea8b2b07643dbb1b84b6d16e1f08391b666bc1e9; 2014-02-15T01:37:52+08:00)
Maven home: E:\opensource\maven\apache-maven-3.2.1\bin\..
Java version: 1.7.0_45, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.7.0_45\jre
Default locale: en_US, platform encoding: GBK
OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"

4.	���н���8��ˮ��
E:\codes\water8cups>mvn clean tomcat7:run

ע�����I:\qiezilife\codes\water8cups\src\main\resources\META-INF\persistence.xml
��I:\qiezilife\codes\water8cups\src\main\webapp\WEB-INF\applicationContext.xml
�������ļ������ݿ����ӵ�����:�ֳɼ���ģʽproduct mode����Ʒ��/online test mode����Ʒ���ԣ�/local test mode�����ز��ԣ�
���ز��Կ���ѡ���ڴ����ݿ����MySQL���ݿ�

5. ����
��Ʒģʽ����Ӧ�ڡ������˶������΢�Ź��ں��룬���ݿ�ʹ��qz_wechat , Ӧ������Ϊ"healthmanager"
��Ʒ����ģʽ����Ӧ�ڡ������˿��ܡ����΢�Ź��ں��룬���ݿ�ʹ��qz_wechat_test,Ӧ������Ϊ"healthmanager2"

��ʹ�ò�Ʒ����ģʽʱ����Ҫ���ĵ��ļ�������
1. /pom.xml �е�<project.name>healthmanager</project.name> --> <project.name>healthmanager2</project.name>
2. /src/main/webapp/WEB-INF/applicationContext.xml �� ʹ��online test mode�����ǽ���mode��ע��ȥ����������modeע�ͼ��ϣ�
3. /src/main/resources/META-INF/persistence.xml ��ʹ�� online test mode �����ǽ���mode��ע��ȥ����������modeע�ͼ��ϣ�

ע�⣬������ĳɲ�Ʒ����ģʽ��eclipse�����ʲô���⣬����mvn clean tomcat7:run �����ܲ���������������mvn eclipse:eclipse����




