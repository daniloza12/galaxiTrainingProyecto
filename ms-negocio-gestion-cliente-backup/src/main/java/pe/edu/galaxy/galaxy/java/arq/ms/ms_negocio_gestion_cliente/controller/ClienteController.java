package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.controller;

import static java.util.Objects.isNull;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteNotificacionDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.service.ClienteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

	private final ClienteService clienteService;
 
	@GetMapping("/id-notificacion/{id}")
	public ResponseEntity<ClienteNotificacionDTO> findByIdToNoyify(@PathVariable(value = "id", required = true) Long id) {
		try {
			
			Optional<ClienteDTO> optClienteDTO = clienteService.findById(id);
			if (isNull(optClienteDTO) || optClienteDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			
			ClienteDTO clientNotify = optClienteDTO.get();
			ClienteNotificacionDTO clienteNotificacionDTO = ClienteNotificacionDTO.builder()
					.nombres(clientNotify.getNombres())
					.apellidos(clientNotify.getApellidos())
					.correo(clientNotify.getCorreo())
					.build();	
			
			return ResponseEntity.ok(clienteNotificacionDTO); // VO/Agregates/Projections		
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
