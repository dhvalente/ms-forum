package br.com.forum.service;

import br.com.forum.dtos.UserDTO;
import br.com.forum.model.Role;
import br.com.forum.model.User;
import br.com.forum.exception.UserNotFoundException;
import br.com.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleService roleService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(UserDTO userDTO) {
        Role role = roleService.findRoleByName(userDTO.getRole());
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(role)
                .build();
        return userRepository.save(user);
    }


    public User findById(String  id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }


    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}