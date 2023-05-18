package br.com.forum.service;

import br.com.forum.dtos.CommentDTO;
import br.com.forum.exception.PostNotFoundException;
import br.com.forum.model.Comment;
import br.com.forum.model.Post;
import br.com.forum.repository.CommentRepository;
import br.com.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    public Comment createComment (CommentDTO commentDTO){
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .postId(commentDTO.getPostId())
                .authorId(commentDTO.getAuthorId())
                .numberOfLikes(0).build();
        return commentRepository.save(comment);
    }

    public List<Comment> findAllByPostId(String postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException(postId));
        return commentRepository.findAllByPostIdOrderByCreatedAtDesc(post.getId());
    }
}
