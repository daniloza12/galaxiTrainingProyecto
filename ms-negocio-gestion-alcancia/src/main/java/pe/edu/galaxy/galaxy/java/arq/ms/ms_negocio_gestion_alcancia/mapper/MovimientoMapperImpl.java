package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaMovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.MovimientoEntity;

@RequiredArgsConstructor
@Component
public class MovimientoMapperImpl implements MovimientoMapper {
	
	public final ModelMapper modelMapper;

	@Override
	public AlcanciaMovimientoDTO toDTO(MovimientoEntity e) {
		return modelMapper.map(e, AlcanciaMovimientoDTO.class);
	}

	@Override
	public List<AlcanciaMovimientoDTO> toLstDTO(List<MovimientoEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public MovimientoEntity toEntity(MovimientoDTO d) {
		return modelMapper.map(d, MovimientoEntity.class);
	}

}
