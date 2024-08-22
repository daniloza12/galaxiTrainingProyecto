package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name ="CuentaEntity" )
@Table(name =  "tbl_cuenta")
public class CuentaEntity {
	
		@Id
		@Column(name = "cuenta_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "tipo_cuenta", nullable = false, length = 50 )
		private String tipoCuenta;
		
		@Column(name = "moneda", nullable = false, length =10 )
		private String moneda;
		
		@Column(name = "nro_cuenta", nullable = false, length = 11 )
		private String nroCuenta;
		
		@Column(name = "nro_cci", nullable = false, length = 16 )
		private String nroCci;

		@Column(name = "estado", nullable = false, length =1 )
		private String estado="1";
		
		@Column(name = "total", nullable = false, precision = 15, scale = 2)
		private BigDecimal total;
		
		@Column(name = "fk_cliente", nullable = false)
		private Long fkCliente;
		
		
}
