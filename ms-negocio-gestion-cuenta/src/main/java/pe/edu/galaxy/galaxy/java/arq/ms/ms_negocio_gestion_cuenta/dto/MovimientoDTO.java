package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoDTO {

	private Long alcanciaId;
	private Long cuentaId;
 	private String tipoMovimiento;
	private LocalDate fechaMovimiento;
	private BigDecimal monto;
 	private String numeroOperacion;

 }
