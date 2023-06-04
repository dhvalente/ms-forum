package br.com.forum.repository;

import br.com.forum.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByNameRole(String name);
}
