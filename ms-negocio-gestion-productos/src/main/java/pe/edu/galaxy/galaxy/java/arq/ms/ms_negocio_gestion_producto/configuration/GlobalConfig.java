package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	// Agregar Provider & Converters

}