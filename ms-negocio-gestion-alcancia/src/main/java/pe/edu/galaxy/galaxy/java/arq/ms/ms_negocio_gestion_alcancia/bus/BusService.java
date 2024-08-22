package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.bus;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;

public interface BusService {
	
	void sendMessage(MovimientoDTO movimientoDTO);

}
