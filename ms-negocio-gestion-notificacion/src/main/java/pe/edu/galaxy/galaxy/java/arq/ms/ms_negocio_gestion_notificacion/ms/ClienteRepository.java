package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.ms;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.ClienteNotificacionDTO;

public interface ClienteRepository {
	
	//void movimientoCuenta(MovimientoDTO movimientoDTO);
	ClienteNotificacionDTO consultarCliente(Long clienteId);

}
