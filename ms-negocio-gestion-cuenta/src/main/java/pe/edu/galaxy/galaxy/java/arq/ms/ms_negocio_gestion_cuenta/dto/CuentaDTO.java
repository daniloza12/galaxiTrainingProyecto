package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CuentaDTO {

	private Long id;
	private String tipoCuenta;
	private String nroCuenta;
	private String nroCci;
	private String moneda;
	private String estado;
	private BigDecimal total;
	private Long fkCliente;

}
