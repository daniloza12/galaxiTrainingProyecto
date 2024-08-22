package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.MovimientoEntity;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

}
