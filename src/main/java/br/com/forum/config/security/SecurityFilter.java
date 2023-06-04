package br.com.forum.config.security;

import br.com.forum.config.jwt.TokenService;
import br.com.forum.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = tokenRecover(request);
        boolean isValid = tokenService.isTokenValid(tokenJWT);
        System.out.println(isValid);
        if(isValid){
            authenticateUser(tokenJWT);
        }
        filterChain.doFilter(request , response);
    }

    private void authenticateUser(String token) {
        String userId = tokenService.getSubject(token);
        UserDetails user = userRepository.findByEmail(userId);
        System.out.println(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user , null , user.getAuthorities());
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
    }

    private String tokenRecover(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")){
            return null;
        }
        System.out.println(token);
        return token.substring(7 , token.length());
    }
}