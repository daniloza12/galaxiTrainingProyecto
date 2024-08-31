package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteDTO;

public interface ClienteService {
	
	Optional<ClienteDTO> findById(Long id) throws ClienteException, IllegalArgumentException, InterruptedException;

	List<ClienteDTO> findAll() throws ClienteException;
	
	Long save(ClienteDTO clienteDTO)throws ClienteException;
	
}
