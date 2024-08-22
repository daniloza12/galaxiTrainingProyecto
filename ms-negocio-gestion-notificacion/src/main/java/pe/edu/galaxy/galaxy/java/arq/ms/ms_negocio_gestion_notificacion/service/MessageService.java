package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.service;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.MovimientoDTO;

public interface MessageService {
	
	void envioCorreo(MovimientoDTO movimientoDTO);

}
