package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.PedidoDTO;

public interface PedidoService {
	
	Optional<PedidoDTO> findById(Long id) throws PedidoException; // API Composite

	List<PedidoDTO> findAll() throws PedidoException;
	
	Long save(PedidoDTO pedidoDTO)throws PedidoException;
	
}
