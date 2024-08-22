package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.entity;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name ="PedidoEntity" )
@Table(name =  "tbl_pedido")
public class PedidoEntity {

	@Id
	@Column(name = "pedido_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "glosa", nullable = false)
	private String glosa;
	
	@Column(name = "fecha_entrega", nullable = true)
	private LocalDate fechaEntrega;
	
	@Column(name = "fecha_pedido", nullable = true)
	private LocalDate fechaPedido;

	@Column(name = "sub_total", nullable = false)
	private Double subTotal;

	@Column(name = "descuento", nullable = false)
	private Double descuento;

	@Column(name = "igv", nullable = false)
	private Double igv;
	
	@Column(name = "total", nullable = false)
	private Double total;

	@Column(name = "cliente_id")
	private Long clientId;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@SQLRestriction("estado='1'")
	private List<PedidoItemEntity> items;
	
	@Column(name = "situacion", nullable = false)
	private String situacion;

	@Column(name = "estado", nullable = false, length =1 )
	private String estado="1";
	
}


