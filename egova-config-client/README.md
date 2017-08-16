# Config Client
示例目标：从配置中心读取配置文件

启动方法：
1、在egova-config-repo添加本工作的配置文件egova-config-client.yml,并填写配置内容，如下：
info:
  profile: default
  from: git1
test: bbb
2、访问http://localhost:8888/egova-config-client/master 验证读取git配置文件信息.
访问配置信息的URL与配置文件的映射关系如下：

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties

{label}：对应Git上不同的分支，默认为master

3、在本工程resources下添加bootstrap.yml文件，并作如下配置：
spring:
  application:
    name: egova-config-client
  cloud:
    config:
      uri: http://localhost:8888/
      profile: default
      label: master
server:
  port: 2001
  
  上述配置参数与Git中存储的配置文件中各个部分的对应关系如下：
  
  spring.application.name：对应配置文件规则中的{application}部分
  spring.cloud.config.profile：对应配置文件规则中的{profile}部分
  spring.cloud.config.label：对应配置文件规则中的{label}部分
  spring.cloud.config.uri：配置中心config-server的地址
  
  4、先后启动egova-config-server,egova-config-client工程
  
  5、在浏览器中输入http://localhost:2001/info 读取在配置中心的egova-config-client的信息。
  若正常返回结果，说明该工程配置文件加载正常