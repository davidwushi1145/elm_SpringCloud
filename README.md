# 基于Spring Cloud 的饿了么外卖平台

![sp](https://raw.githubusercontent.com/davidwushi1145/photo2/main/sp.jpg)

Elm SpringCloud 是一个基于 Spring Cloud 的微服务架构项目，旨在提供餐饮行业解决方案。本项目包括多个服务，如用户服务、食品服务、订单服务等，每个服务都负责处理特定的业务逻辑。

## 项目架构

本项目采用微服务架构，主要包括以下服务：

- **Eureka Server**: 服务注册与发现。
- **Gateway Server**: API 网关处理路由和权限。
- **Config Server**: 配置服务，集中管理各服务配置。
- **User Server**: 用户相关操作的服务。
- **Food Server**: 食品相关操作的服务。
- **Cart Server**: 购物车相关操作的服务。
- **Order Server**: 订单处理服务。
- **File Server**: 文件上传和管理服务。
- **Delivery Address Server**: 配送地址管理服务。

## 技术栈

- **Spring Boot**: 微服务的基础框架。
- **Spring Cloud**: 微服务间通信。
- **Spring Cloud Gateway**: API 网关。
- **Spring Data JPA/MyBatis**: 数据持久化。
- **Spring Security**: 安全框架。
- **Eureka**: 服务发现与注册。
- **Redis**: 缓存技术。
- **RabbitMQ**: 消息队列。
- **Docker**: 容器化和集群管理。
- **Knife4J**: API 文档生成。
- **XXL-job**: 分布式任务调度。
- **Nginx**: 反向代理和负载均衡。
- **MySQL**: Mysql主从复制。
- **MinIO**: 分布式文件系统。
- **ELK（Elasticsearch、Logstash、Kibana）**: 日志收集和分析。

## 快速开始

确保您的机器上已安装 Maven 和 JDK 18 或更高版本。

### 获取代码

```bash
git clone https://github.com/davidwushi1145/elm_SpringCloud.git
cd elm_SpringCloud
```

### 构建项目

在项目根目录下执行以下命令来构建所有微服务：

```bash
mvn clean install
```

### 运行服务

启动 Eureka Server:

```bash
cd eureka_server_13000
mvn spring-boot:run
```

按照类似的方式启动其他服务，确保所有依赖服务已经启动。

### 验证服务

访问 Eureka 服务注册中心查看服务状态：

```
http://localhost:13000/
```

## 开发指南

访问 Swagger API 文档：

```
http://localhost:<port>/doc.html
```

