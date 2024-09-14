package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion"})
public class MsNegocioGestionNotificacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNegocioGestionNotificacionApplication.class, args);
	}

}
