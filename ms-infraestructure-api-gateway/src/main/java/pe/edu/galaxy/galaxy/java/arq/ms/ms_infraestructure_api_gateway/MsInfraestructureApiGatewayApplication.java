package pe.edu.galaxy.galaxy.java.arq.ms.ms_infraestructure_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsInfraestructureApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInfraestructureApiGatewayApplication.class, args);
	}

}
