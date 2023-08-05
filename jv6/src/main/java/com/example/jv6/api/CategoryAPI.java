package com.example.jv6.api;

import com.example.jv6.entity.Category;
import com.example.jv6.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryAPI {
    @Autowired
    CategoryRepository categoryRepository;

    // GET all categories
    @GetMapping
    public List<Category> getAllRoles() {
        return categoryRepository.findAll();
    }

    // GET a category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getRoleById(@PathVariable String id) {
        Optional<Category> role = categoryRepository.findById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CREATE a new category
    @PostMapping
    public Category createRole(@RequestBody Category role) {
        return categoryRepository.save(role);
    }

    // UPDATE a category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateRole(@PathVariable String id, @RequestBody Category roleDetails) {
        Optional<Category> role = categoryRepository.findById(id);
        if (role.isPresent()) {
            Category updatedRole = role.get();
            updatedRole.setName(roleDetails.getName());
            return ResponseEntity.ok(categoryRepository.save(updatedRole));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        Optional<Category> role = categoryRepository.findById(id);
        if (role.isPresent()) {
            categoryRepository.delete(role.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
