
package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.feing;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.ClienteNotificacionDTO;

@Component
public class FeingClienteServiceClient{

	private final FeingClienteService clienteFeingService;
	
	private final FeingClienteServiceBackupOne clienteFeingServiceBackupOne;

	private Long id;

	public FeingClienteServiceClient(FeingClienteService clienteFeingService,
			final FeingClienteServiceBackupOne clienteFeingServiceBackupOne) {
		this.clienteFeingService = clienteFeingService;
		this.clienteFeingServiceBackupOne = clienteFeingServiceBackupOne;
	}

	@CircuitBreaker(name = "findById", fallbackMethod = "findByIdFallback")
	public ClienteNotificacionDTO findById(@PathVariable(value = "id") Long id) {
		this.id = id;
		return clienteFeingService.findByIdToNoyify(id).getBody();
	};


	@CircuitBreaker(name = "findByIdFallback", fallbackMethod = "findByIdFallbackMemory")
	public ClienteNotificacionDTO findByIdFallback(Exception exception) { 
		return clienteFeingServiceBackupOne.findByIdToNoyify(id);
	}
	
	public ClienteNotificacionDTO findByIdFallbackMemory(Exception exception) { 
		return ClienteNotificacionDTO.builder()
										.nombres("")
										.apellidos("")
										.correo("")
									.build();
	}

}
