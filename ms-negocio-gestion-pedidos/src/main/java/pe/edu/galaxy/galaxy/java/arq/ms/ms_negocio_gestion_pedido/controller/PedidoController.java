package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.controller;

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
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.PedidoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.PedidoService;

import static java.util.Objects.isNull;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

	private final PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {

		try {
			List<PedidoDTO> lstPedidoDTO = pedidoService.findAll();

			if (isNull(lstPedidoDTO) || lstPedidoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstPedidoDTO);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<PedidoDTO> optPedidoDTO = pedidoService.findById(id);

			if (isNull(optPedidoDTO) || optPedidoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optPedidoDTO.get());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PedidoDTO pedidoDTO) {
		try {
			Long id = pedidoService.save(pedidoDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El pedido fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
