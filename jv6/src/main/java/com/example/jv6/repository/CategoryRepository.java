package com.example.jv6.repository;

import com.example.jv6.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "SELECT c.id, c.name FROM Categories c WHERE concat(c.id, ' ', c.name) like %?1%", nativeQuery = true)
    public List<Category> search(String pageable);
}
