package com.example.jv6.api;

import com.example.jv6.entity.Role;
import com.example.jv6.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
@CrossOrigin
public class RoleAPI {
    @Autowired
    private RoleRepository roleRepository;

    // GET all roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // GET a role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CREATE a new role
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    // UPDATE a role
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable String id, @RequestBody Role roleDetails) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role updatedRole = role.get();
            updatedRole.setName(roleDetails.getName());
            return ResponseEntity.ok(roleRepository.save(updatedRole));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.delete(role.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
