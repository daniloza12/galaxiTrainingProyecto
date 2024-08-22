package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.mapper;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.ClienteEntity;

@RequiredArgsConstructor
@Component
public class ClienteMapperImpl implements ClienteMapper {
	
	public final ModelMapper modelMapper;

	@Override
	public ClienteDTO toDTO(ClienteEntity e) {
		return modelMapper.map(e, ClienteDTO.class);
	}

	@Override
	public List<ClienteDTO> toLstDTO(List<ClienteEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public ClienteEntity toEntity(ClienteDTO d) {
		return modelMapper.map(d, ClienteEntity.class);
	}

}
