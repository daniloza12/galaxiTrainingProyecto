package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.service;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.AlcanciaDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;

public interface AlcanciaService {
	
	Optional<AlcanciaDTO> findById(Long id) throws AlcanciaException;

	List<AlcanciaDTO> findAll() throws AlcanciaException;
	
	Long save(AlcanciaDTO alcanciaDTO)throws AlcanciaException;
		
	void moverDineroAlcancia(MovimientoDTO movimientoDTO) throws MovimientoException, AlcanciaException;

}
