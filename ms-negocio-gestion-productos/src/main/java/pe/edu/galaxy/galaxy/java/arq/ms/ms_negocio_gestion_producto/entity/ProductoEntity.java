package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.entity;

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
@Entity(name ="ProductoEntity" )
@Table(name =  "tbl_producto")
public class ProductoEntity {

	@Id
	@Column(name = "producto_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false, length =260 )
	private String nombre;
	
	@Column(name = "descripcion", nullable = true, length =1200 )
	private String descripcion;

	@Column(name = "precio", nullable = false)
	private Double precio;

	@Column(name = "stock_minimo", nullable = false)
	private Integer stockMinimo;

	@Column(name = "stock_maximo", nullable = false)
	private Integer stockMaximo;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "categoria_id", nullable = false)
	private CategoriaEntity categoria;

	@Column(name = "estado", nullable = false, length =1 )
	private String estado="1";
	
}


