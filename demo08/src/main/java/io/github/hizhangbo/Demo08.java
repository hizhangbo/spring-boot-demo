package io.github.hizhangbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * GraalVm Native Compile
 *
 * mvn -Pnative -DskipTests=true clean package
 * native:compile-no-fork
 */
@SpringBootApplication
public class Demo08 {
    public static void main(String[] args) {
        SpringApplication.run(Demo08.class, args);
    }
}
