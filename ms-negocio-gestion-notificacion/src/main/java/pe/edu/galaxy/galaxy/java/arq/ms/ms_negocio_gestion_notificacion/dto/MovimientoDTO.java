package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovimientoDTO {

	private Long alcanciaId;
	private Long cuentaId;
 	private String tipoMovimiento;
	private LocalDate fechaMovimiento;
	private BigDecimal monto;
 	private String numeroOperacion;
 	private String correo;
 	private String nombre;
 	private String moneda;
 	private String nombreAlcancia;

 }
