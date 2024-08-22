
package  pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.ClienteNotificacionDTO;

@FeignClient(name = "clienteFeingServiceBackupOne", url ="${ms-negocio-gestion-cliente_backup.url}")
public interface FeingClienteServiceBackupOne {

	@GetMapping(value = "/id-notificacion/{id}")
	ClienteNotificacionDTO findByIdToNoyify(@PathVariable(value = "id") Long id);

}
