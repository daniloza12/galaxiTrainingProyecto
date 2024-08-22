package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.mapper;

import java.util.List;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.DetalleCuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.DetalleCuentaEntity;

public interface DetalleCuentaMapper {

	DetalleCuentaDTO toDTO(DetalleCuentaEntity e);
	
	DetalleCuentaEntity toEntity(DetalleCuentaDTO d);
	
	List<DetalleCuentaDTO> toLstDTO(List<DetalleCuentaEntity> lstE);

}
