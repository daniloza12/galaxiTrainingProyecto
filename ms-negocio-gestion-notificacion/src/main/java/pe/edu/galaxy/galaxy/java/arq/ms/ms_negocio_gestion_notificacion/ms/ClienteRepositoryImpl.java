package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.ms;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Objects;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.ClienteNotificacionDTO;

@Slf4j
@Service
public class ClienteRepositoryImpl implements ClienteRepository {

	private final DiscoveryClient discoveryClient;

	private final RestTemplate restTemplate;

	// Resillense
	private final CircuitBreaker circuitBreaker;

	private final String ms_name = "ms-negocio-gestion-cliente";

	private String url = "";
	
	@Value("${spSecurity.apiClient.username}")
	private String username;
	
	@Value("${spSecurity.apiClient.password}")
	private String password;


	public ClienteRepositoryImpl(RestTemplate restTemplate, DiscoveryClient discoveryClient,
			CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
		this.circuitBreaker = circuitBreakerFactory.create(ms_name);

	}

	@Override
	public ClienteNotificacionDTO consultarCliente(Long clienteId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(username, password);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ClienteNotificacionDTO result =

				circuitBreaker.run(() -> {

					try {
						this.url = this.getURI() + "/v1/clientes/id-notificacion/" + clienteId.toString();
						ResponseEntity<ClienteNotificacionDTO> clienteDTO = restTemplate.exchange(url, HttpMethod.GET,
								requestEntity, ClienteNotificacionDTO.class);

						if (!isNull(clienteDTO)) {
							return clienteDTO.getBody();
						}

					} catch (Exception e) {
						log.error(e.getMessage());
					}

					return null;
				},
						
				  throwable -> consultarClienteServiceAux(clienteId));
		//throwable -> getClienteDTO());


		return result;
	}

 
	public ClienteNotificacionDTO consultarClienteServiceAux(Long clienteId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(username, password);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ClienteNotificacionDTO result =

				circuitBreaker.run(() -> {

					try {
						this.url = this.getURI() + "/v1/clientes/id-notificacion/" + clienteId.toString();
						ResponseEntity<ClienteNotificacionDTO> clienteDTO = restTemplate.exchange(url, HttpMethod.GET,
								requestEntity, ClienteNotificacionDTO.class);

						if (!isNull(clienteDTO)) {
							return clienteDTO.getBody();
						}

					} catch (Exception e) {
						log.error(e.getMessage());
					}

					return null;
				},
						
				throwable -> getClienteDTO());

		return result;
	} 
	
	private ClienteNotificacionDTO getClienteDTO() {
		return ClienteNotificacionDTO.builder().nombres("").apellidos("").correo("").build();
	}

	private String getURI() throws RuntimeException {

		if (Objects.isNull(discoveryClient)) {
			log.info("discoveryClient is null");
			throw new RuntimeCryptoException("Error al obtener el DiscoveryClient");
		}

		List<ServiceInstance> instances = discoveryClient.getInstances(ms_name);

		if (Objects.isNull(instances) || instances.isEmpty()) {
			throw new RuntimeCryptoException("Error al obtener instancias");
		}

		log.info("instances.get(0).getHost() => {}", instances.get(0).getHost());

		String uri = instances.get(0).getUri().toString();
		log.info("uri => {}", uri);

		return uri;
	}

}
