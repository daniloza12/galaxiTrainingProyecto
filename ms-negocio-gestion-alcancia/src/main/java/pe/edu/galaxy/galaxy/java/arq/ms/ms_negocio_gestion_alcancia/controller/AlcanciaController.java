package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.controller;

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
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.service.AlcanciaException;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.service.AlcanciaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/alcancias")
public class AlcanciaController {

	private final AlcanciaService alcanciaService;

	@GetMapping
	public ResponseEntity<List<AlcanciaDTO>> findAll() {
		try {
			List<AlcanciaDTO> lstAlcanciaDTO = alcanciaService.findAll();
			if (isNull(lstAlcanciaDTO) || lstAlcanciaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstAlcanciaDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlcanciaDTO> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<AlcanciaDTO> optAlcanciaDTO = alcanciaService.findById(id);

			if (isNull(optAlcanciaDTO) || optAlcanciaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optAlcanciaDTO.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AlcanciaDTO alcanciaDTO) {
		try {
			Long id = alcanciaService.save(alcanciaDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El alcancia fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/mover")
	public ResponseEntity<?> moverDineroAlcancia(@RequestBody MovimientoDTO movimientoDTO) throws AlcanciaException {
		try {
			alcanciaService.moverDineroAlcancia(movimientoDTO);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "Se realizo movimiento de alcancia con exito.");
 			return ResponseEntity.ok(resp);
		} catch (AlcanciaException e) {
			throw e;
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
