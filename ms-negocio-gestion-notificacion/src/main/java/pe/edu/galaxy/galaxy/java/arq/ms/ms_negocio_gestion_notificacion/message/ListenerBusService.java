package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.message;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.MovimientoDTO;

public interface ListenerBusService {   
     void sendEmail(MovimientoDTO movimientoDTO);
}
