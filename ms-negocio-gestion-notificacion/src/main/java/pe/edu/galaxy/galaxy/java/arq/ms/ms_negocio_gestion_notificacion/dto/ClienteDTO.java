package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteDTO{
	
	private Long id;
	private Integer dni;	
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String correo;
	private String estado;
	
}


