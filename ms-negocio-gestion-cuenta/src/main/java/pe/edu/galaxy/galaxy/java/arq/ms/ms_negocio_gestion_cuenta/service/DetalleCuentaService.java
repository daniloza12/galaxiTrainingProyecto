package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto.DetalleCuentaDTO;

public interface DetalleCuentaService {
	
	Optional<DetalleCuentaDTO> findById(Long id) throws DetalleCuentaException;

	List<DetalleCuentaDTO> findAll() throws DetalleCuentaException;
	
	Long save(DetalleCuentaDTO detalleCuentaDTO) throws DetalleCuentaException;
	
 	
}
