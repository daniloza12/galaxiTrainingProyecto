package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.entity.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

}
