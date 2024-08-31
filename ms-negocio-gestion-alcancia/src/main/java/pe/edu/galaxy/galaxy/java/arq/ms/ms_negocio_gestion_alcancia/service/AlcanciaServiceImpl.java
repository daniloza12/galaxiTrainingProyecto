package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.service;

import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.bus.BusService;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.AlcanciaEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.MovimientoEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.mapper.AlcanciaMapper;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.ms.CuentaService;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.respository.AlcanciaRepository;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.respository.MovimientoRepository;

@RequiredArgsConstructor
@Service
public class AlcanciaServiceImpl implements AlcanciaService {

	private final AlcanciaRepository alcanciaRepository;
	private final AlcanciaMapper alcanciaMapper;

	private final MovimientoRepository movimientoRepository;
	private final CuentaService cuentaService;
	
	private final BusService busService;

	@Override
	public Optional<AlcanciaDTO> findById(Long id) throws AlcanciaException {
		String msg = String.format("No existe alcancia con id => {}", id);
		AlcanciaEntity alcanciaEntity = this.alcanciaRepository.findById(id).orElseThrow(() -> new AlcanciaException(msg));
		AlcanciaDTO alcanciaDTO = alcanciaMapper.toDTO(alcanciaEntity);
		return Optional.ofNullable(alcanciaDTO);
	}

	@Override
	public List<AlcanciaDTO> findAll() throws AlcanciaException {
		return alcanciaMapper.toLstDTO(alcanciaRepository.findAll());
	}

	@Override
	public Long save(AlcanciaDTO alcanciaDTO) throws AlcanciaException {
		AlcanciaEntity alcanciaEntity= alcanciaRepository.save(alcanciaMapper.toEntity(alcanciaDTO));	
		if (isNull(alcanciaEntity)) {
			throw new AlcanciaException("Error al resgitrar alcancia");
		}
		return alcanciaEntity.getId();
	}
	
	@Override
	public void moverDineroAlcancia(MovimientoDTO movimientoDTO) throws MovimientoException, AlcanciaException {
		
		String msg = String.format("No existe alcancia con id => {}", movimientoDTO.getAlcanciaId());
		AlcanciaEntity alcanciaEntity = this.alcanciaRepository.findById(movimientoDTO.getAlcanciaId()).orElseThrow(() -> new AlcanciaException(msg));
	
		String tipoMovimiento = "";
		int montoEval = movimientoDTO.getMonto().compareTo(BigDecimal.ZERO);
		
		if(montoEval == -1) {
			tipoMovimiento = "RETIRO ALC.";
			
			int aceptarFlujo = (movimientoDTO.getMonto().multiply(BigDecimal.valueOf(-1))).compareTo(alcanciaEntity.getTotal());
			if(aceptarFlujo > 0) {
				throw new AlcanciaException("El monto a retirar no puede ser mayor al monto total de la alcancia. Verifique");
			}
			
		}else if(montoEval == 1) {
			tipoMovimiento = "ABONO ALC.";
		}else {
			throw new AlcanciaException("El monto ingresado debe ser valido. Verifique");
		}
		movimientoDTO.setTipoMovimiento(tipoMovimiento);
		movimientoDTO.setNombreAlcancia(alcanciaEntity.getNombre());
		 
		MovimientoEntity movimiento = new MovimientoEntity();
		movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
		movimiento.setFechaMovimiento(movimientoDTO.getFechaMovimiento());
		movimiento.setMonto(movimientoDTO.getMonto());	 
		AlcanciaEntity alcancia = new AlcanciaEntity();
		alcancia.setId(movimientoDTO.getAlcanciaId());
		movimiento.setAlcancia(alcancia);		
	 	movimientoRepository.save(movimiento);
	 	
		alcanciaEntity.setTotal(alcanciaEntity.getTotal().add(movimientoDTO.getMonto()));
	 	alcanciaRepository.save(alcanciaEntity);	  
	
		CompletableFuture.runAsync( ()-> cuentaService.realizarCuentaMovimiento(movimientoDTO));
		CompletableFuture.runAsync( ()-> busService.sendMessage(movimientoDTO));
	     
	}
    
}
