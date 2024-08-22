package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cuenta.entity.DetalleCuentaEntity;

@Repository
public interface DetalleCuentaRepository extends JpaRepository<DetalleCuentaEntity, Long> {

}
