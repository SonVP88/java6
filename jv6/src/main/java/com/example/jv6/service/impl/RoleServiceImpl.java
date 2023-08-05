package com.example.jv6.service.impl;

import com.example.jv6.entity.Role;
import com.example.jv6.repository.RoleRepository;
import com.example.jv6.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findRoleById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Page<Role> findPaginated(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1,pageSize);
        return this.roleRepository.findAll(pageRequest);
    }

    @Override
    public List<Role> searchRole(String key) {
        return roleRepository.search(key);
    }

}
