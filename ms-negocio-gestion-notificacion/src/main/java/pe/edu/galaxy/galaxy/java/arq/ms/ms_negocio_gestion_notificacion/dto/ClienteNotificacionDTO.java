package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class ClienteNotificacionDTO{
	
	private String nombres;
	private String apellidos;
	private String correo;

}


