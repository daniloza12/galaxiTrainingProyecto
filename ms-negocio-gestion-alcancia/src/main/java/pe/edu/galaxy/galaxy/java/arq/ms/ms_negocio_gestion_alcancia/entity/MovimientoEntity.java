package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity;

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
@Entity(name ="MovimientoEntity" )
@Table(name =  "tbl_movimiento")
public class MovimientoEntity {
	
		@Id
		@Column(name = "movimiento_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "tipo_movimiento", nullable = false, length = 20 )
		private String tipoMovimiento;
		
		@Column(name = "fecha_movimiento", nullable = true)
		private LocalDate fechaMovimiento;
		
		@Column(name = "monto", nullable = false, precision = 15, scale = 2)
		private BigDecimal monto;
		
		@ManyToOne(cascade = CascadeType.MERGE)
		@JoinColumn(name = "alcancia_id", nullable = false)
		private AlcanciaEntity alcancia;
		
}
