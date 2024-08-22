package pe.edu.galaxy.galaxy.java.arq.ms.ms_infraestructure_server_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsInfraestructureServerConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInfraestructureServerConfigApplication.class, args);
	}

}
