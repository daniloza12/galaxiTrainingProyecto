package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsNegocioGestionProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNegocioGestionProductoApplication.class, args);
	}

}
