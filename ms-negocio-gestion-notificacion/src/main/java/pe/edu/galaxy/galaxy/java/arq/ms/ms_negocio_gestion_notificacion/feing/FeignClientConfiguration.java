package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.feing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignClientConfiguration {
	
	@Value("${spSecurity.apiClient.username}")
	private String username;
	
	@Value("${spSecurity.apiClient.password}")
	private String password;
	
	@Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String auth = username + ":" + password;
                String encodedAuth = Base64Utils.encodeToString(auth.getBytes());
                template.header("Authorization", "Basic " + encodedAuth);
            }
        };
    }
}