package cn.wushi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.wushi.mapper"})
public class FoodServer10200 {
    public static void main(String[] args) {
        SpringApplication.run(FoodServer10200.class, args);
    }
}