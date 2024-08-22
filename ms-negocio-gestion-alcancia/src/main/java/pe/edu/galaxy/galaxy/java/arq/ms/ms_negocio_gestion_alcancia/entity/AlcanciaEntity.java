package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name ="AlcanciaEntity" )
@Table(name =  "tbl_alcancia")
public class AlcanciaEntity {
	
		@Id
		@Column(name = "alcancia_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "nombre", nullable = false, length =60 )
		private String nombre;
		
		@Column(name = "estado", nullable = false, length =1 )
		private String estado="1";
		
		@Column(name = "total", nullable = false, precision = 15, scale = 2)
		private BigDecimal total;
		
		@Column(name = "fk_cuenta", nullable = false)
		private Long fkcuenta;
		
}
