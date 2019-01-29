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

## ribbon
> 导入eureka-client启动包、web启动包即可，导入eureka-client启动包包含ribbon启动包

* 启动类添加@EnableDiscoveryClient
* 注册@Bean实例RestTemplate，方法上添加@LoadBalanced
* 配置端口、应用名、客户端注册的serviceURL

参考：https://blog.csdn.net/forezp/article/details/69788938

## feign
> 导入openfeign启动包、eureka-client启动包、web启动包

* 启动类添加@EnableDiscoveryClient
* 启动类添加@EnableFeignClients
* 编写接口和调用的controller对应，接口上添加@FeignClient，并指定应用名
* 配置端口、应用名、客户端注册的serviceURL

## hystrix
> 导入netflix-hystrix启动包

**Ribbon:**
* 启动类上添加@EnableHystrix
* 方法上添加@HystrixCommand，指定失败后回调方法
* 根据返回值、形参编写回调方法

**Feign:**
* yml文件开启feign和hystrix的整合
* @FeignClient指定fallback属性，Class类型
* 编写fallback类，实现@FeignClient所在接口，类上添加@Component

## Hystrix Dashboard
> 导入启动包hystrix-dashboard即可

* 启动类上添加@EnableHystrixDashboard注解
* 注册ServletRegistrationBean的实例
* 访问　http://localhost:8765/hystrix
* 端口填写　http://localhost:8765/hystrix.stream
* 标题随意

## zuul
> 导入启动包netflix-zuul，其中包含了web启动包，所以无需重复导入

* 配置routes路由，设置拦截路径和对应服务id
* 启动类配置@EnableZuulProxy注解
* 访问不同的路径会调用对应的服务，比如：http://localhost:8769/feign/hi?name=feng

**过滤器**
* 创建过滤器继承ZuulFilter类
* @Component注解注册过滤器
* 创建失败回调类，实现FallbackProvider，并注册
* getRoute方法中可以指定为哪个微服务回退，*为所有服务

参考：https://blog.csdn.net/forezp/article/details/69939114
https://www.jianshu.com/p/865ad9360787

## config server
> 导入config-serve包

* 启动类加@EnableConfigServer注解
* yml配置git地址、分支、配置文件目录层级，非公开项目需要账号密码
* 访问 http://localhost:8888/feng-config/dev 查看 propertySources 中是否包含配置文件feng-config-dev.yml的属性

## config client
> 导入config启动包，启动包包含config-client包

* yml配置启动发现，配置config server的服务id
* 指定配置文件的前半部分、配置文件的后半部分

## Bus
> 导入bus-amqp启动包，包含rabbit的启动包和bus的启动包  
> 还要导入actuator的启动包，bus-amqp中有依赖此包的地方

* 检查rabbit服务是否开启，默认访问链接 http://localhost:15672/#/
* spring整合rabbitMQ，地址、端口、账号、密码
* 对需要重新获取实例的组件和实体类加上@RefreshScope注解
* yml文件暴露bus-refresh端口
* 修改配置文件后，post请求访问：http://localhost:8881/actuator/bus-refresh  用另一个配置客户端查看读取是否更新

参考：https://cloud.spring.io/spring-cloud-static/Greenwich.RELEASE/multi/multi__bus_endpoints.html

## zipkin

**服务端**
> 导入zipkin-server包和zipkin-autoconfigure-ui包

* yml文件只配置端口
* 启动类添加@EnableZipkinServer注解
* 访问 http://localhost:9000/zipkin/

**客户端**
> 导入zipkin启动包

* 配置zipkin基础路径，如果注册到了发现，可以用serviceId代替

参考：https://www.jianshu.com/p/da9079a7656c   
日志冲突解决：https://stackoverflow.com/questions/14024756/slf4j-class-path-contains-multiple-slf4j-bindings

## high-availability
> 导入eureka-server启动包

* 启动类添加@EnableEurekaServer、@EnableEurekaClient
* 写两份配置文件
* 修改/etc/hosts后重启网络：sudo /etc/init.d/networking restart

参考：https://blog.csdn.net/fantasywm/article/details/44493449
高可用：https://blog.csdn.net/forezp/article/details/70183572