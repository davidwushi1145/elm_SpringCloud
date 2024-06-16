package cn.wushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;

@SpringBootApplication(exclude = {ReactiveSecurityAutoConfiguration.class})
public class GatewayServer14000 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServer14000.class, args);
    }
}
