package com.example.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication(scanBasePackages = {"com.example.server"})
@MapperScan("com.example.server.mapper")
@EnableScheduling       // 开启定时任务
@EnableWebSocketMessageBroker
public class YsbgApplication {
    public static void main(String[] args) {
        SpringApplication.run(YsbgApplication.class, args);
    }
}
