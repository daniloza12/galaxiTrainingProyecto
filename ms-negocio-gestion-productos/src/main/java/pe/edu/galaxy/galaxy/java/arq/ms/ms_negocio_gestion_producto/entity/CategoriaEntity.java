package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name ="CategoriaEntity" )
@Table(name =  "tbl_categoria")
public class CategoriaEntity {
		@Id
		@Column(name = "categoria_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "nombre_corto", nullable = false, length =60 )
		private String nombreCorto;
		
		@Column(name = "nombre_largo", nullable = false, length =160 )
		private String nombreLargo;

		@Column(name = "estado", nullable = false, length =1 )
		private String estado="1";
		
}
