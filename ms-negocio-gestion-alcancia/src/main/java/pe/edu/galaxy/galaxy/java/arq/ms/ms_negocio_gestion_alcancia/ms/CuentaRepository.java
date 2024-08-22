package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.ms;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;

public interface CuentaRepository {
	
	void realizarCuentaMovimiento(MovimientoDTO movimientoDTO);

}
