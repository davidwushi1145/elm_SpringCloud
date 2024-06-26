package cn.wushi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"cn.wushi.mapper"})
public class UserServer10100 {
    public static void main(String[] args) {
        SpringApplication.run(UserServer10100.class, args);
    }
}