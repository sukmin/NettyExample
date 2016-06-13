package me.ujung.nettyexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyExampleApplication implements ApplicationRunner {

	@Autowired
	private EchoServer echoServer;
	
	public static void main(String[] args) {
		SpringApplication.run(NettyExampleApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		echoServer.start();
	}
}
