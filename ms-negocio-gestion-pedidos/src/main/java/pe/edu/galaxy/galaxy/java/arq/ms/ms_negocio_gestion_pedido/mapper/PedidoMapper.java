package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.mapper;

import java.util.List;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.PedidoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.entity.PedidoEntity;

public interface PedidoMapper {

	PedidoDTO toDTO(PedidoEntity e);
	
	PedidoEntity toEntity(PedidoDTO d);
	
	List<PedidoDTO> toLstDTO(List<PedidoEntity> lstE);

}
