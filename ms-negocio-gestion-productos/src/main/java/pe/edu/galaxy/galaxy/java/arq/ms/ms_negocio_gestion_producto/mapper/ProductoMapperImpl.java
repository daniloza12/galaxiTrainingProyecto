package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.mapper;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.dto.ProductoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.entity.ProductoEntity;

@RequiredArgsConstructor
@Component
public class ProductoMapperImpl implements ProductoMapper {
	
	public final ModelMapper modelMapper;

	@Override
	public ProductoDTO toDTO(ProductoEntity e) {
		return modelMapper.map(e, ProductoDTO.class);
	}

	@Override
	public List<ProductoDTO> toLstDTO(List<ProductoEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public ProductoEntity toEntity(ProductoDTO d) {
		return modelMapper.map(d, ProductoEntity.class);
	}

}
