package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.AlcanciaEntity;

@Data
@NoArgsConstructor
public class AlcanciaMovimientoDTO {

	private Long id;	
 	private String tipoMovimiento;
	private LocalDate fechaMovimiento;
	private BigDecimal monto;
 	private AlcanciaEntity alcancia;

 }
