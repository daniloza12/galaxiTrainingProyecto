package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlcanciaDTO {

	private Long id;
	private String nombre;
	private String estado;
	private BigDecimal total;
	private Long fkcuenta;
}
