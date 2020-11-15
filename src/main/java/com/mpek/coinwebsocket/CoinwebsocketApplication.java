package com.mpek.coinwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableJpaRepositories
public class CoinwebsocketApplication {

	public static void main(String[] args) throws URISyntaxException, IOException, DeploymentException {
		SpringApplication.run(CoinwebsocketApplication.class, args);
	}

}
