package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.entity;

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
@Entity(name ="PedidoItemEntity" )
@Table(name =  "tbl_pedido_item")
public class PedidoItemEntity {

	@Id
	@Column(name = "pedido_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "producto_id")
	private Long producto_id;
	
	@Column(name = "precio")
	private Double precio;

	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "sub_total", nullable = false)
	private Double subTotal;

	@Column(name = "descuento", nullable = false)
	private Double descuento;

	@Column(name = "igv", nullable = false)
	private Double igv;
	
	@Column(name = "total", nullable = false)
	private Double total;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "pedido_id", nullable = false)
	private PedidoEntity pedido;

	@Column(name = "estado", nullable = false, length =1 )
	private String estado="1";
	
}


