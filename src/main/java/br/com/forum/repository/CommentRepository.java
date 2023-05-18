package br.com.forum.repository;

import br.com.forum.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByPostIdOrderByCreatedAtDesc(String postId);
}