package com.example.jv6.service.impl;

import com.example.jv6.entity.Category;
import com.example.jv6.repository.CategoryRepository;
import com.example.jv6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void add(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> findPaginated(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1,pageSize);
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public List<Category> searchCategory(String key) {
        return categoryRepository.search(key);
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }
}
