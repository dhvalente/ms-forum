package br.com.forum.repository;


import br.com.forum.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}