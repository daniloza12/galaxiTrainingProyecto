package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name ="DetalleCuentaEntity" )
@Table(name =  "tbl_detalle_cuenta")
public class DetalleCuentaEntity {
	
		@Id
		@Column(name = "detalle_cuenta_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "tipo_operacion", nullable = false, length = 20 )
		private String tipoOperacion;
		
		@Column(name = "monto", nullable = false, precision = 15, scale = 2)
		private BigDecimal monto;
		
		@Column(name = "fecha_operacion", nullable = false )
		private LocalDate fechaOperacion;

		@Column(name = "numero_operacion", nullable = false, length = 10 )
		private String numeroOperacion;
		
		@ManyToOne(cascade = CascadeType.MERGE)
		@JoinColumn(name = "cuenta_id", nullable = false)
		private CuentaEntity cuenta;
		
}
