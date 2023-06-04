package br.com.forum.service;


import br.com.forum.exception.RoleNotFoundException;
import br.com.forum.exception.UserNotFoundException;
import br.com.forum.model.Role;
import br.com.forum.records.RoleRecord;
import br.com.forum.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Role createRole (RoleRecord roleRecord){
        Role role = Role.builder()
                .nameRole(roleRecord.description())
                .build();
        return roleRepository.save(role);
    }


    public Role findRoleById(String id){
        return roleRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Role findRoleByName(String name){
        return roleRepository.findByNameRole(name).orElseThrow(() -> new RoleNotFoundException(name));
    }

    public Page<Role> findAllRolles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public void deleteRole(String id){
        Role role = verifyIfExists(id);
        roleRepository.deleteById(role.getId());
    }

    public Role updateRole(RoleRecord roleRecord){
        verifyIfExists(roleRecord.id());
        return roleRepository.save(modelMapper.map(roleRecord , Role.class));
    }

    private Role verifyIfExists(String id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }
}
