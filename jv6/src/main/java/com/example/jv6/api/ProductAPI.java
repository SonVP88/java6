package com.example.jv6.api;

import com.example.jv6.entity.Category;
import com.example.jv6.entity.Product;
import com.example.jv6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductAPI {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllRoles() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getRoleById(@PathVariable Integer id) {
        Optional<Product> role = productRepository.findById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Product createRole(@RequestBody Product role) {
        return productRepository.save(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateRole(@PathVariable Integer id, @RequestBody Product roleDetails) {
        Optional<Product> role = productRepository.findById(id);
        if (role.isPresent()) {
            Product updatedRole = role.get();
            updatedRole.setName(roleDetails.getName());
            updatedRole.setImage(roleDetails.getImage());
            updatedRole.setPrice(roleDetails.getPrice());
            updatedRole.setCreateDate(roleDetails.getCreateDate());
            updatedRole.setAvailable(roleDetails.getAvailable());
            Category category = new Category();
            category.setId(roleDetails.getCategory().getId());
            updatedRole.setCategory(roleDetails.getCategory());
            return ResponseEntity.ok(productRepository.save(updatedRole));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        Optional<Product> role = productRepository.findById(id);
        if (role.isPresent()) {
            productRepository.delete(role.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
