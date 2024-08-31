package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
