package br.com.forum.controller;

import br.com.forum.dtos.CommentDTO;
import br.com.forum.model.Comment;
import br.com.forum.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Comment> createPost(@RequestBody @Valid CommentDTO commentDTO ){
        Comment comment = commentService.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<List<Comment>> findAllByPostId(@PathVariable String id){
        List<Comment> list =  commentService.findAllByPostId(id);
        return ResponseEntity.ok().body(list);
    }
}
