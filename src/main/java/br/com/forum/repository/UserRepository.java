package br.com.forum.repository;


import br.com.forum.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByEmail(String email);
    @Query("{ 'email' : ?0 }")
    Optional<User> findUserByEmail(String email);

}