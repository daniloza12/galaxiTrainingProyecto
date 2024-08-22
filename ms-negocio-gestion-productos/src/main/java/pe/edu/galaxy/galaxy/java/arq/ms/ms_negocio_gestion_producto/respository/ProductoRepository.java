package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_producto.entity.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
