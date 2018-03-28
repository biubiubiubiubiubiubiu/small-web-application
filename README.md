# small-web-application

## Currently only supports compilation in Intellij.

* Open Intellij through pom.xml
* Choose Tomcat local in Edit Configuration
* Click run and you should see the index page in chrome at your chosen port(default: 8080)

## War version:
* 请保证您的tomcat的java版本为1.8
* 在工程根目录下使用
	```
	mvn clean package
	```
* 将所得target的文件下的NeteaseDemo.war复制进入tomcat的webapps中
* 更改tomcat配置文件，在conf/server.xml文件中的<host> tag下面加上如下命令：
	```
	<Context path="" docBase="NeteaseDemo" debug="0" reloadable="true"></Context>
	```
* 启动tomcat start.sh，在localhost:8080下打开应用
