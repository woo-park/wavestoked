package com.wavestoked.config;


import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
//@Profile("local")
public class H2ServerConfig {

//    @Bean
//    public Server h2TcpServer() throws SQLException {
//        return Server.createTcpServer().start();
//    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server h2Server1() throws SQLException {
//        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9080");
//    }

    @Bean
    public Server h2TcpServer() throws SQLException {
        Server h2Server = Server.createTcpServer().start();
        if (h2Server.isRunning(true)) {
            System.out.println("H2 server was started and is running.");
            return h2Server;
        } else {
            throw new RuntimeException("Could not start H2 server.");
        }
    }
}
