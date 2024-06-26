package cn.wushi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan({"cn.wushi.mapper"})
public class BusinessServer10301 {
    public static void main(String[] args) {
        SpringApplication.run(BusinessServer10301.class, args);
    }

    //向容器中添加RestTemplate实例
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}