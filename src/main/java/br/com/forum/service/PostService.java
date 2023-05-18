package br.com.forum.service;

import br.com.forum.dtos.PostDTO;
import br.com.forum.model.User;
import br.com.forum.exception.UserNotFoundException;
import br.com.forum.model.Post;
import br.com.forum.repository.PostRepository;
import br.com.forum.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;
    public Post createPost (PostDTO postDTO){
        Post post = Post.builder()
                .content(postDTO.getContent())
                .title(postDTO.getTitle())
                .topic(postDTO.getTopic())
                .authorId(postDTO.getAuthorId())
                .build();
       return postRepository.save(post);
    }


    public Post findById(String id){
        return postRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> findAllByAuthor(String authorId){
        User user = userRepository.findById(authorId).orElseThrow(()-> new UserNotFoundException(authorId));
        return postRepository.findByAuthorId(user.getId().toString());
    }

    public List<Post> findAllByTopic(String topic){
        return  postRepository.findAllByTopic(topic);
    }

    public void deletePost(String id){
        postRepository.deleteById(id);
    }


}
