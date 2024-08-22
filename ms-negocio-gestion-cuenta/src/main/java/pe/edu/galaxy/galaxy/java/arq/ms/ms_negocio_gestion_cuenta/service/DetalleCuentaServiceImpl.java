package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.DetalleCuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.DetalleCuentaEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.mapper.DetalleCuentaMapper;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.respository.DetalleCuentaRepository;

@RequiredArgsConstructor
@Service
public class DetalleCuentaServiceImpl implements DetalleCuentaService {

	private final DetalleCuentaRepository detalleCuentaRepository;
	private final DetalleCuentaMapper detalleCuentaMapper;
	
	@Override
	public Optional<DetalleCuentaDTO> findById(Long id) throws DetalleCuentaException {
		String msg = String.format("No existe detalle cuenta con id => {}", id);
		DetalleCuentaEntity cuentaEntity = this.detalleCuentaRepository.findById(id).orElseThrow(() -> new DetalleCuentaException(msg));
		DetalleCuentaDTO cuentaDTO = detalleCuentaMapper.toDTO(cuentaEntity);
		return Optional.ofNullable(cuentaDTO);
	}

	@Override
	public List<DetalleCuentaDTO> findAll() throws DetalleCuentaException {
		return detalleCuentaMapper.toLstDTO(detalleCuentaRepository.findAll());
	}

	@Override
	public Long save(DetalleCuentaDTO cuentaDTO) throws DetalleCuentaException {
		DetalleCuentaEntity detalleCuentaEntity= detalleCuentaRepository.save(detalleCuentaMapper.toEntity(cuentaDTO));	
		if (isNull(detalleCuentaEntity)) {
			throw new DetalleCuentaException("Error al resgitrar detalle cuenta");
		}
		return detalleCuentaEntity.getId();
	}
	
 
}
