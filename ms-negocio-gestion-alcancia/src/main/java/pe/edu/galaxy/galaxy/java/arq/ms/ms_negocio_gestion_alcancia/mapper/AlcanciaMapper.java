package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.mapper;

import java.util.List;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.AlcanciaEntity;

public interface AlcanciaMapper {

	AlcanciaDTO toDTO(AlcanciaEntity e);
	
	AlcanciaEntity toEntity(AlcanciaDTO d);
	
	List<AlcanciaDTO> toLstDTO(List<AlcanciaEntity> lstE);

}
