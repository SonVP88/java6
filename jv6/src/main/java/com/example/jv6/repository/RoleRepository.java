package com.example.jv6.repository;

import com.example.jv6.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(value = "SELECT r.id, r.name FROM roles r WHERE concat(r.id, ' ', r.name) like %?1%", nativeQuery = true)
    public List<Role> search(String pageable);
}
