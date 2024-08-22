package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.mapper;

import java.util.List;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.dto.ProductoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.entity.ProductoEntity;

public interface ProductoMapper {

	ProductoDTO toDTO(ProductoEntity e);
	
	ProductoEntity toEntity(ProductoDTO d);
	
	List<ProductoDTO> toLstDTO(List<ProductoEntity> lstE);

}
