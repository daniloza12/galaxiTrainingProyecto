package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.CuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.CuentaEntity;

@RequiredArgsConstructor
@Component
public class CuentaMapperImpl implements CuentaMapper {
	
	public final ModelMapper modelMapper;

	@Override
	public CuentaDTO toDTO(CuentaEntity e) {
		return modelMapper.map(e, CuentaDTO.class);
	}

	@Override
	public List<CuentaDTO> toLstDTO(List<CuentaEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public CuentaEntity toEntity(CuentaDTO d) {
		return modelMapper.map(d, CuentaEntity.class);
	}

}
