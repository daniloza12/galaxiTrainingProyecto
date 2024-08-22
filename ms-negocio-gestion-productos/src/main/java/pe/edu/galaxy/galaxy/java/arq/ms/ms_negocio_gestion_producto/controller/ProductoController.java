package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.controller;

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
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.dto.ProductoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.service.ProductoService;

import static java.util.Objects.isNull;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/productos")
public class ProductoController {

	private final ProductoService productoService;

	@GetMapping
	public ResponseEntity<List<ProductoDTO>> findAll() {

		try {
			List<ProductoDTO> lstProductoDTO = productoService.findAll();

			if (isNull(lstProductoDTO) || lstProductoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstProductoDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<ProductoDTO> optProductoDTO = productoService.findById(id);

			if (isNull(optProductoDTO) || optProductoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optProductoDTO.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductoDTO productoDTO) {
		try {
			Long id = productoService.save(productoDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El producto fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
