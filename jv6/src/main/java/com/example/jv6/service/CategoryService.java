package com.example.jv6.service;

import com.example.jv6.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    void add(Category category);
    void delete(String id);
    public Page<Category> findPaginated(int pageNo, int pageSize);
    List<Category> searchCategory(String key);
    Optional<Category> findById(String id);
}
