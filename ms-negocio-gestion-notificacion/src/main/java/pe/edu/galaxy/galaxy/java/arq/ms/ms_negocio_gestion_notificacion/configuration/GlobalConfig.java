package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class GlobalConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public WebProperties.Resources resources() {
		return new WebProperties.Resources();
	} 

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}