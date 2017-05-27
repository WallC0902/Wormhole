package com.mytijian.wormhole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.mytijian.wormhole.dao")
public class WormholeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WormholeApplication.class, args);
    }
}
