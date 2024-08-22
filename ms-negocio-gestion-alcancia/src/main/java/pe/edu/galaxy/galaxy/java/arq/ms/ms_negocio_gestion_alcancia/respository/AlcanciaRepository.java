package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.AlcanciaEntity;

@Repository
public interface AlcanciaRepository extends JpaRepository<AlcanciaEntity, Long> {

}
