package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaMovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;

public interface MovimientoService {
	
	Optional<AlcanciaMovimientoDTO> findById(Long id) throws MovimientoException;

	List<AlcanciaMovimientoDTO> findAll() throws MovimientoException;
	
	Long save(MovimientoDTO movimientoDTO)throws MovimientoException;

}
