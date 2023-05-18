package br.com.forum.controller;

import br.com.forum.dtos.PostDTO;
import br.com.forum.model.Post;
import br.com.forum.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDTO postDTO ){

        Post post = postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping
    public ResponseEntity<Page<Post>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postService.findAll(pageable);
        return ResponseEntity.ok().body(postPage);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post =  postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/postByAuthor")
    public ResponseEntity<List<Post>> findAllByAuthor(@RequestBody String authorId){
        List<Post> list =  postService.findAllByAuthor(authorId);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
