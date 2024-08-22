package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.service;

import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.CuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.CuentaEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.DetalleCuentaEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.mapper.CuentaMapper;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.respository.CuentaRepository;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.respository.DetalleCuentaRepository;

@RequiredArgsConstructor
@Service
public class CuentaServiceImpl implements CuentaService {

	private final CuentaRepository cuentaRepository;
	private final CuentaMapper cuentaMapper;

	private final DetalleCuentaRepository detalleCuentaRepository;

	
	@Override
	public Optional<CuentaDTO> findById(Long id) throws CuentaException {
		String msg = String.format("No existe cuenta con id => {}", id);
		CuentaEntity cuentaEntity = this.cuentaRepository.findById(id).orElseThrow(() -> new CuentaException(msg));
		CuentaDTO cuentaDTO = cuentaMapper.toDTO(cuentaEntity);
		return Optional.ofNullable(cuentaDTO);
	}

	@Override
	public List<CuentaDTO> findAll() throws CuentaException {
		return cuentaMapper.toLstDTO(cuentaRepository.findAll());
	}

	@Override
	public Long save(CuentaDTO cuentaDTO) throws CuentaException {
		CuentaEntity cuentaEntity= cuentaRepository.save(cuentaMapper.toEntity(cuentaDTO));	
		if (isNull(cuentaEntity)) {
			throw new CuentaException("Error al resgitrar cuenta");
		}
		return cuentaEntity.getId();
	}
	
	@Override
	public void realizarMovimiento(MovimientoDTO movimientoDTO) throws CuentaException {
		BigDecimal montoMovido = movimientoDTO.getMonto().multiply(BigDecimal.valueOf(-1));
 		DetalleCuentaEntity detalle = new DetalleCuentaEntity();
		detalle.setTipoOperacion(movimientoDTO.getTipoMovimiento());
		detalle.setMonto(movimientoDTO.getMonto());
		detalle.setFechaOperacion(movimientoDTO.getFechaMovimiento());
		detalle.setNumeroOperacion(movimientoDTO.getNumeroOperacion());
		CuentaEntity cuenta = new CuentaEntity();
		cuenta.setId(movimientoDTO.getCuentaId());
		detalle.setCuenta(cuenta);
		detalleCuentaRepository.save(detalle);
		
		String msg = String.format("No existe cuenta con id => {}", movimientoDTO.getCuentaId());
		CuentaEntity cuentaEntity = this.cuentaRepository.findById(movimientoDTO.getCuentaId()).orElseThrow(() -> new CuentaException(msg));
		cuentaEntity.setTotal(cuentaEntity.getTotal().add(montoMovido));
		cuentaRepository.save(cuentaEntity);
		 
	}

}
