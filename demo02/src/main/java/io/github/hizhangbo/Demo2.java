package io.github.hizhangbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "io.github.hizhangbo.mapper")
@SpringBootApplication
public class Demo2 {
    public static void main(String[] args) {
        SpringApplication.run(Demo2.class, args);
    }
}
