package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.controller;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaMovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.service.MovimientoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/movimientos")
public class MovimientoController {

	private final MovimientoService movimientoService;

	@GetMapping
	public ResponseEntity<List<AlcanciaMovimientoDTO>> findAll() {
		try {
			List<AlcanciaMovimientoDTO> lstMovimientoDTO = movimientoService.findAll();
			if (isNull(lstMovimientoDTO) || lstMovimientoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstMovimientoDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlcanciaMovimientoDTO> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<AlcanciaMovimientoDTO> optMovimientoDTO = movimientoService.findById(id);

			if (isNull(optMovimientoDTO) || optMovimientoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optMovimientoDTO.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	 
}
