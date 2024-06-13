package cn.wushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //激活Eureka Server
public class EurekaServer13000 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer13000.class, args);
    }
}