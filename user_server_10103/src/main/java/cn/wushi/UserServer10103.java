package cn.wushi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.wushi.mapper"})
public class UserServer10103 {
    public static void main(String[] args) {
        SpringApplication.run(UserServer10103.class, args);
    }
}