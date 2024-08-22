package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.mapper;

import java.util.List;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.CuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.CuentaEntity;

public interface CuentaMapper {

	CuentaDTO toDTO(CuentaEntity e);
	
	CuentaEntity toEntity(CuentaDTO d);
	
	List<CuentaDTO> toLstDTO(List<CuentaEntity> lstE);

}
