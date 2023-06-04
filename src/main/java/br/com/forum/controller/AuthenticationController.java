package br.com.forum.controller;

import br.com.forum.model.User;
import br.com.forum.records.LoginRecord;
import br.com.forum.records.TokenRecord;
import br.com.forum.repository.UserRepository;
import br.com.forum.config.jwt.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auths")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TokenRecord> authenticate(@RequestBody @Valid LoginRecord loginRecord){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRecord.email(), loginRecord.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        String tokenJWT = tokenService.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenRecord(tokenJWT));
    }

    @GetMapping("/findUserToken")
    public Optional<User> recoverUserID(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String token2 = token.substring(7 , token.length());
        String id = tokenService.getSubject(token2);
        System.out.println("ID : "+ id);
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok().body(user).getBody();
    }

}