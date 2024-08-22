package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.CuentaEntity;

@Data
@NoArgsConstructor
public class DetalleCuentaDTO {

	private Long id;
	private String tipoOperacion;
	private BigDecimal monto;
	private LocalDate fechaOperacion;
	private String numeroOperacion;
	private CuentaEntity cuenta;

}
