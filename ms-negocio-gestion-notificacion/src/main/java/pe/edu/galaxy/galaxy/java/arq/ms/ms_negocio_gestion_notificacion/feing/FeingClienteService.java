package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.ClienteNotificacionDTO;

@FeignClient(name = "clienteFeingService", url = "${ms-negocio-gestion-cliente.url}")
public interface FeingClienteService {

	@GetMapping(value = "/id-notificacion/{id}")
	ResponseEntity<ClienteNotificacionDTO> findByIdToNoyify(@PathVariable(value = "id") Long id);
	
}
