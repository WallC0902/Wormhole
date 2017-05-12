package com.mytijian.wormhole.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mytijian.wormhole.dao")
public class WormholeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WormholeApplication.class, args);
	}
}
