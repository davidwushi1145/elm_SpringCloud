package cn.wushi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("饿了么SpringCloud项目")
                        .version("1.0")
                        .description("饿了么API接口文档")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("david.wushi@foxmail.com")
                                .name("David Lee"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")));

    }
}

