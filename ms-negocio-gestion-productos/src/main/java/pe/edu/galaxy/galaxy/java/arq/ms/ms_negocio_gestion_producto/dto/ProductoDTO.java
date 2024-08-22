package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.dto;

import lombok.Data;

@Data
public class ProductoDTO{

	private Long id;
	
	private String nombre;
	
	private String descripcion;

	private Double precio;

	private Integer stockMinimo;

	private Integer stockMaximo;
	
	private CategoriaDTO categoria;

	private String estado="1";
	
}

