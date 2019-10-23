# dubbo-demo
dubbo快速入门
# 项目结构
```text
 dubbo-demo
    dubbo-demo-java :采用编程方式使用dubbo
    dubbo-demo-xml : 采用注解的方式使用dubbo

```
# 快速启动
Dubbo 采用全 Spring 配置方式，透明化接入应用，对应用没有任何 API 侵入，只需用 Spring 加载 Dubbo 的配置即可，Dubbo 基于 Spring 的 Schema 扩展进行加载。
# 服务提供者
## 安装服务提供者
1. 克隆代码 git clone https://github.com/Jason-HuHu/dubbo-demo.git
2. 修改配置 resource/META-INFO.spring/dubbo-demo-provider.xml 中的dubbo:registery，替换成真实的注册中心地址，推荐使用zookeeper
3. 启动Provider 运行 dubbo-demo-provider中的com.alibaba.dubbo.demo.provider.Provider
## 定义服务接口
DemoService.java
```java
public interface DemoService {

    String sayHello(String name);

}
```
## 在服务提供方实现接口
DemoServiceImpl.java
```java
public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();
    }

}
```
## 用Spring配置声明暴露服务

dubbo-demo-provider.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
	http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!-- 提供方应用信息，用于计算依赖关系 -->
    <dubbo:application name="demo-provider"/>
    <!-- 服务注册地址 -->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>

    <!-- 用dubbo协议在20880端口暴露服务 -->
    <dubbo:protocol name="dubbo" port="20880"/>

    <!-- 和本地bean一样实现服务 -->
    <bean id="demoService" class="com.alibaba.dubbo.demo.impl.DemoServiceImpl"/>
    <bean id="demoServer" class="com.alibaba.dubbo.demo.impl.DemoServerImpl"/>

    <!-- 声明需要暴露的服务接口 -->
    <dubbo:service interface="com.alibaba.dubbo.demo.DemoService" ref="demoService"/>
    <dubbo:service interface="com.alibaba.dubbo.demo.DemoServer" ref="demoServer"/>

</beans>
```
## 加载Spring配置
Provider.java
```java
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-provider.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }

}
```
# 服务消费者
## 启动服务消费者
1. 下载代码 git clone https://github.com/Jason-HuHu/dubbo-demo.git
2. 修改配置 resource/META-INFO.spring/dubbo-demo-consumer.xml 中的 dubbo:registery，替换成Provider提供的注册中心地址
3. 确保已经启动Provider 运行dubbo-demo-consumer中的com.alibaba.dubbo.demo.consumer.Consumer
## 通过Spring配置引用远程服务
dubbo-demo-consumer.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
	http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
    <dubbo:application name="demo-consumer"/>

    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>

    <!-- 生成远程服务代理，可以和本地bean一样使用demoService -->
    <dubbo:reference id="demoService" check="false" interface="com.alibaba.dubbo.demo.DemoService"/>
    <dubbo:reference id="demoServer" check="false" interface="com.alibaba.dubbo.demo.DemoServer"/>


</beans>
```
## 加载Spring配置，并调用远程服务
Consumer.java
```java
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();


        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
        DemoServer demoServer = (DemoServer) context.getBean("demoServer");
        String hello = demoService.sayHello("hello hhhhworld"); // 执行远程方法
        hello += demoServer.sayGoodbye("baibai");

        System.out.println(hello); // 显示调用结果
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```