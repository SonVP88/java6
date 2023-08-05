package com.example.jv6.service;

import com.example.jv6.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface RoleService {
    List<Role> getAll();
    void saveRole(Role role);
    void deleteRole(String id);
    Optional<Role> findRoleById(String id);
    public Page<Role> findPaginated(int pageNo, int pageSize);
    List<Role> searchRole(String key);
}
