package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.security;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.entity.security.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);

}
