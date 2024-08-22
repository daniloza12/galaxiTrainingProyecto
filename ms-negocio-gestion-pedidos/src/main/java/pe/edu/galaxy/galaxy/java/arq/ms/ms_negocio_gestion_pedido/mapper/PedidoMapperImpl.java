package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.mapper;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.PedidoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.entity.PedidoEntity;

@RequiredArgsConstructor
@Component
public class PedidoMapperImpl implements PedidoMapper {
	
	public final ModelMapper modelMapper;

	@Override
	public PedidoDTO toDTO(PedidoEntity e) {
		return modelMapper.map(e, PedidoDTO.class);
	}

	@Override
	public List<PedidoDTO> toLstDTO(List<PedidoEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public PedidoEntity toEntity(PedidoDTO d) {
		return modelMapper.map(d, PedidoEntity.class);
	}

}
