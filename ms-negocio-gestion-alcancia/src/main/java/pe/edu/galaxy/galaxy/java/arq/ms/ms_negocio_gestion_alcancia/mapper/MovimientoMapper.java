package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.mapper;

import java.util.List;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaMovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.MovimientoEntity;

public interface MovimientoMapper {

	AlcanciaMovimientoDTO toDTO(MovimientoEntity e);
	
	MovimientoEntity toEntity(MovimientoDTO d);
	
	List<AlcanciaMovimientoDTO> toLstDTO(List<MovimientoEntity> lstE);

}
