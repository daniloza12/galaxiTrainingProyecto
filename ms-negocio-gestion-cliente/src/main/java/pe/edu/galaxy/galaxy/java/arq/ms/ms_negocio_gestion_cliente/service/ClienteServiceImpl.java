package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.ClienteEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.mapper.ClienteMapper;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.respository.ClienteRepository;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;

	 
	@Override
	public Optional<ClienteDTO> findById(Long id) throws ClienteException, IllegalArgumentException, InterruptedException {
		String msg = String.format("No existe cliente con id => {}", id);
		ClienteEntity clienteEntity = this.clienteRepository.findById(id).orElseThrow(() -> new ClienteException(msg));
		ClienteDTO clienteDTO = clienteMapper.toDTO(clienteEntity);
		return Optional.ofNullable(clienteDTO);
	}

	@Override
	public List<ClienteDTO> findAll() throws ClienteException {
		return clienteMapper.toLstDTO(clienteRepository.findAll());
	}

	@Override
	public Long save(ClienteDTO clienteDTO) throws ClienteException {
		ClienteEntity clienteEntity= clienteRepository.save(clienteMapper.toEntity(clienteDTO));	
		if (isNull(clienteEntity)) {
			throw new ClienteException("Error al resgitrar cliente");
		}
		return clienteEntity.getId();
	}
}
