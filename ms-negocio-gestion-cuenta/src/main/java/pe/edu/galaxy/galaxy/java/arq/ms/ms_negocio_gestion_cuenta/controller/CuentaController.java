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
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.CuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.service.CuentaException;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.service.CuentaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cuentas")
public class CuentaController {

	private final CuentaService cuentaService;

	@GetMapping
	public ResponseEntity<List<CuentaDTO>> findAll() {

		try {
			List<CuentaDTO> lstCuentaDTO = cuentaService.findAll();

			if (isNull(lstCuentaDTO) || lstCuentaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstCuentaDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CuentaDTO> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<CuentaDTO> optCuentaDTO = cuentaService.findById(id);

			if (isNull(optCuentaDTO) || optCuentaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optCuentaDTO.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CuentaDTO cuentaDTO) {
		try {
			Long id = cuentaService.save(cuentaDTO);
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
	
	@PostMapping("/realizarMovimiento")
	public ResponseEntity<?> realizarMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
		try {
			cuentaService.realizarMovimiento(movimientoDTO);
		} catch (CuentaException e) {
			e.printStackTrace();
		}
		Map<String, Object> resp = new HashMap<>();
		resp.put("data", "Proceso del api realizarMovimiento realizado con exito.");
		return ResponseEntity.ok(resp);
	}

}
