package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteNotificacionDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.service.ClienteService;
import static java.util.Objects.isNull;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		try {
			List<ClienteDTO> lstClienteDTO = clienteService.findAll();

			if (isNull(lstClienteDTO) || lstClienteDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstClienteDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<ClienteDTO> optClienteDTO = clienteService.findById(id);

			if (isNull(optClienteDTO) || optClienteDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optClienteDTO.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {
		try {
			Long id = clienteService.save(clienteDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El cliente fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
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
