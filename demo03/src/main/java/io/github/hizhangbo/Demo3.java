package io.github.hizhangbo;

import cn.shuibo.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RSA 前后端加解密测试
 */
@EnableSecurity
@SpringBootApplication
public class Demo3 {

    public static void main(String[] args) {
        SpringApplication.run(Demo3.class, args);
    }
}
