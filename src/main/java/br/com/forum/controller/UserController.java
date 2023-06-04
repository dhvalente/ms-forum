package br.com.forum.controller;

import br.com.forum.config.jwt.TokenService;
import br.com.forum.dtos.UserDTO;
import br.com.forum.model.User;
import br.com.forum.service.RoleService;
import br.com.forum.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public ResponseEntity<User> createPost(@RequestBody @Valid UserDTO userDTO ){
        User user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list =  userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user =  userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/findUserToken/{token}")
    public ResponseEntity<User> findUserByToken(@PathVariable String token){
        String id = tokenService.getSubject(token);
        User user = userService.findByEmail(id);
        return ResponseEntity.ok().body(user);
    }
}
