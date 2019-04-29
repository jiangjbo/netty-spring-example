package com.jbo.nettyspringexample;

import com.jbo.nettyspringexample.server.TCPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value= "classpath:/application.properties")
public class NettySpringExampleApplication {
	@Configuration
	@Profile("production")
	@PropertySource("classpath:/application.properties")
	static class Production{}

	@Configuration
	@Profile("local")
	@PropertySource({"classpath:/application.properties"})
	static class Local{}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(NettySpringExampleApplication.class, args);
		TCPServer tcpServer = context.getBean(TCPServer.class);
		tcpServer.start();
	}

}
