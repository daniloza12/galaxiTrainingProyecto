package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.CuentaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.MovimientoDTO;

public interface CuentaService {
	
	Optional<CuentaDTO> findById(Long id) throws CuentaException;

	List<CuentaDTO> findAll() throws CuentaException;
	
	Long save(CuentaDTO cuentaDTO)throws CuentaException;
	
	void realizarMovimiento(MovimientoDTO movimientoDTO) throws CuentaException;
	
}
