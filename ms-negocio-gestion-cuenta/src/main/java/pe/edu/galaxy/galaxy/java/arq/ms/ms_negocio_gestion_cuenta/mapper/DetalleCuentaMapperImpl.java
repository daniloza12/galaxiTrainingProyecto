package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.DetalleCuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.DetalleCuentaEntity;

@RequiredArgsConstructor
@Component
public class DetalleCuentaMapperImpl implements DetalleCuentaMapper {
	
	public final ModelMapper modelMapper;

	@Override
	public DetalleCuentaDTO toDTO(DetalleCuentaEntity e) {
		return modelMapper.map(e, DetalleCuentaDTO.class);
	}

	@Override
	public List<DetalleCuentaDTO> toLstDTO(List<DetalleCuentaEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public DetalleCuentaEntity toEntity(DetalleCuentaDTO d) {
		return modelMapper.map(d, DetalleCuentaEntity.class);
	}

}
