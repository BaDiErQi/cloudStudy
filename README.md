# cloudStudy
对SpringCloud的学习总结

## eureka-server
> 导入spring-cloud-starter-netflix-eureka-server
>
> eureka-serve启动包集成了web启动包、eureka-client启动包、ribbon启动包等

* 启动类添加@EnableEurekaServer
* 配置端口、eureka实例和客户端
* 实例配置hostname，客户端配置serviceURL、根据情况配置fetch和register


## eureka-client
> 导入eureka-client启动包、web启动包
>
> eureka-client启动包集成了ribbon启动包等，但是没有web启动包，需要额外添加

* 启动类添加@EnableEurekaClient
* 配置端口、应用名、客户端注册的serviceURL

参考：https://blog.csdn.net/forezp/article/details/69696915  
@EnableEurekaServer和@EnableDiscoveryClient的区别：
https://www.jianshu.com/p/f6db3117864f