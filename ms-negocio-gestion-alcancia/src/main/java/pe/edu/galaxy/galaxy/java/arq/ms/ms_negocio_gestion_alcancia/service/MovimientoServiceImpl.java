package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaMovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.MovimientoEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.mapper.MovimientoMapper;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.respository.MovimientoRepository; 

@RequiredArgsConstructor
@Service
public class MovimientoServiceImpl implements MovimientoService {

	private final MovimientoRepository movimientoRepository;
	private final MovimientoMapper movimientoMapper;

	@Override
	public Optional<AlcanciaMovimientoDTO> findById(Long id) throws MovimientoException {
		String msg = String.format("No existe movimiento con id => {}", id);
		MovimientoEntity movimientoEntity = this.movimientoRepository.findById(id).orElseThrow(() -> new MovimientoException(msg));
		AlcanciaMovimientoDTO movimientoDTO = movimientoMapper.toDTO(movimientoEntity);
		return Optional.ofNullable(movimientoDTO);
	}

	@Override
	public List<AlcanciaMovimientoDTO> findAll() throws MovimientoException {
		return movimientoMapper.toLstDTO(movimientoRepository.findAll());
	}

	@Override
	public Long save(MovimientoDTO movimientoDTO) throws MovimientoException {
		MovimientoEntity movimientoEntity= movimientoRepository.save(movimientoMapper.toEntity(movimientoDTO));	
		if (isNull(movimientoEntity)) {
			throw new MovimientoException("Error al resgitrar movimiento");
		}
		return movimientoEntity.getId();
	}
	
 
 	
}
