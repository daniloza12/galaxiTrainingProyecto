package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteNotificacionDTO{
	
	private String nombres;
	private String apellidos;
	private String correo;

}


