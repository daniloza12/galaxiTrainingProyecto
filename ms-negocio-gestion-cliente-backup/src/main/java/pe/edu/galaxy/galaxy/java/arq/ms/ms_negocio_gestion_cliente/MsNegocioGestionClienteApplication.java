package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.security.PermissionEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.security.RoleEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.security.RoleEnum;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.entity.security.UserEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_cliente.security.UserRepository;


@EnableDiscoveryClient
@SpringBootApplication
public class MsNegocioGestionClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNegocioGestionClienteApplication.class, args);
	}
	


    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            /* Create PERMISSIONS */
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();

            /* Create ROLES */
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleUser = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            RoleEntity roleInvited = RoleEntity.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionList(Set.of(readPermission))
                    .build();

            RoleEntity roleDeveloper = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                    .build();

            /* CREATE USERS */
            
            String data = new BCryptPasswordEncoder().encode("alcancias123pass");

           
            UserEntity alcancia = UserEntity.builder()
                    .username("alcancias123")
                    .password(data)
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();

             //userRepository.saveAll(List.of(alcancia));
        };
    }
	
}
