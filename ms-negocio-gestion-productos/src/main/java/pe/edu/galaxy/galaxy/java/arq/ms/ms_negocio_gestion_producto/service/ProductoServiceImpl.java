package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.dto.ProductoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.entity.ProductoEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.mapper.ProductoMapper;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.respository.ProductoRepository;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

	private final ProductoRepository productoRepository;
	private final ProductoMapper productoMapper;

	@Override
	public Optional<ProductoDTO> findById(Long id) throws ProductoException {
		String msg = String.format("No existe producto con id => {}", id);
		ProductoEntity productoEntity = this.productoRepository.findById(id).orElseThrow(() -> new ProductoException(msg));
		ProductoDTO productoDTO = productoMapper.toDTO(productoEntity);
		return Optional.ofNullable(productoDTO);
	}

	@Override
	public List<ProductoDTO> findAll() throws ProductoException {
		return productoMapper.toLstDTO(productoRepository.findAll());
	}

	@Override
	public Long save(ProductoDTO productoDTO) throws ProductoException {
		ProductoEntity productoEntity= productoRepository.save(productoMapper.toEntity(productoDTO));	
		if (isNull(productoEntity)) {
			throw new ProductoException("Error al resgitrar producto");
		}
		return productoEntity.getId();
	}
}
