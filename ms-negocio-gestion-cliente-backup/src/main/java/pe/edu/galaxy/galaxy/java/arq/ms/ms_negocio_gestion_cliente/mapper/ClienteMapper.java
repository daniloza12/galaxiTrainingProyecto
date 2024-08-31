package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.mapper;

import java.util.List;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.ClienteEntity;

public interface ClienteMapper {

	ClienteDTO toDTO(ClienteEntity e);
	
	ClienteEntity toEntity(ClienteDTO d);
	
	List<ClienteDTO> toLstDTO(List<ClienteEntity> lstE);

}
