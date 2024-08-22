package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.controller;

import static java.util.Objects.isNull;

import java.util.HashMap;
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
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.DetalleCuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.service.DetalleCuentaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cuenta-detalles")
public class CuentaDetalleController {

	private final DetalleCuentaService detalleCuentaService;

	@GetMapping
	public ResponseEntity<List<DetalleCuentaDTO>> findAll() {

		try {
			List<DetalleCuentaDTO> lstDetalleCuentaDTO = detalleCuentaService.findAll();

			if (isNull(lstDetalleCuentaDTO) || lstDetalleCuentaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstDetalleCuentaDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalleCuentaDTO> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<DetalleCuentaDTO> optDetalleCuentaDTO = detalleCuentaService.findById(id);

			if (isNull(optDetalleCuentaDTO) || optDetalleCuentaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optDetalleCuentaDTO.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody DetalleCuentaDTO cuentaDTO) {
		try {
			Long id = detalleCuentaService.save(cuentaDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El cuenta fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	 
}
