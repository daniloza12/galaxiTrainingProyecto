package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity(name ="ClienteEntity" )
@Table(name =  "tbl_cliente")
public class ClienteEntity {

	@Id
	@Column(name = "cliente_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dni", nullable = false, length =8 )
	private Integer dni;	
	
	@Column(name = "nombres", nullable = false, length =150 )
	private String nombres;
	
	@Column(name = "apellidos", nullable = false, length =150 )
	private String apellidos;
	
	@Column(name = "direccion", nullable = false, length =400 )
	private String direccion;

	@Column(name = "telefono", nullable = false, length =20 )
	private String telefono;

	@Column(name = "correo", nullable = false, length = 80 )
	private String correo;

	@Column(name = "estado", nullable = false, length =1 )
	private String estado="1";
	
}


