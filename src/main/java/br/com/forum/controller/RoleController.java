package br.com.forum.controller;

import br.com.forum.exception.RoleNotFoundException;
import br.com.forum.model.Role;
import br.com.forum.records.RoleRecord;
import br.com.forum.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(CREATED)
    public ResponseEntity<Role> createRole(@RequestBody @Valid RoleRecord roleRecord ){
        Role role = roleService.createRole(roleRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN" , "ROLE_USER"})
    public ResponseEntity<Page<Role>> findAllRoles(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Role> rolePage = roleService.findAllRolles(pageable);
        return ResponseEntity.ok().body(rolePage);
    }

    @GetMapping(value="/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Role> findById(@PathVariable String id){
        Role role =  roleService.findRoleById(id);
        return ResponseEntity.ok().body(role);
    }

    @PutMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> updateRole(@RequestBody RoleRecord roleRecord) throws RoleNotFoundException {
        return ResponseEntity.ok().body(roleService.updateRole(roleRecord));
    }
}
