package cn.wushi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.wushi.mapper"})
public class OrdersServer10601 {
    public static void main(String[] args) {
        SpringApplication.run(OrdersServer10601.class, args);
    }
}