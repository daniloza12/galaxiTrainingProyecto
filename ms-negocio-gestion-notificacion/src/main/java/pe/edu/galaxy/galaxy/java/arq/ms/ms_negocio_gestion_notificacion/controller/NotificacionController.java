package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.message.ListenerBusService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notificacion")
public class NotificacionController {

	private final ListenerBusService listenerBusService;
	
	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody MovimientoDTO alcanciaDTO) {
		listenerBusService.sendEmail(alcanciaDTO);
		Map<String, Object> resp = new HashMap<>();
		resp.put("message", "Correo enviado con exito");
		return ResponseEntity.ok(resp); 
	}
 

}
