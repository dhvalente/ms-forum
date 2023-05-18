package br.com.forum.repository;

import br.com.forum.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.ArrayList;
import java.util.List;


public interface PostRepository extends MongoRepository<Post, String> {

    Page<Post> findAll(Pageable pageable);
    List<Post> findAllByOrderByDateCreatedDesc();

    ArrayList<Post> findAllByTopic(String topic);

    List<Post> findByAuthorId(String authorId);
}