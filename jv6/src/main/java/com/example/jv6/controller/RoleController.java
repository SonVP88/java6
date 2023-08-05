package com.example.jv6.controller;

import com.example.jv6.entity.Role;
import com.example.jv6.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/list")
    public String listRole(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/list/{pageNo}")
    public String findPaginated( @PathVariable("pageNo") int pageNo,Model model) {
        int pageSize = 3;
        Page<Role> page = roleService.findPaginated(pageNo, pageSize);
        List<Role> roleList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("role", roleList);
        return "Role/role";
    }

    @GetMapping("/search")
    public String viewAdd(@Param("key") String key, Model model) {
        if (key == ""){
            return "redirect:/role/list";
        }else{
            List<Role> role = roleService.searchRole(key);
            model.addAttribute("role", role);
        }
        return "Role/findRole";
    }

    @GetMapping("/addRole")
    public String viewAdd(Model model) {
        model.addAttribute("role", new Role());
        return "Role/addRole";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") String id, RedirectAttributes model) {
        roleService.deleteRole(id);
        model.addFlashAttribute("success", "Xoa thanh cong !!!");
        return "redirect:/role/list";
    }

    @PostMapping("/add")
    public String addRole(@ModelAttribute("role")@Valid Role role, BindingResult bindingResult, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            return "Role/addRole";
        }else{
            roleService.saveRole(role);
            model.addFlashAttribute("success", "Them thanh cong !!!");
            return "redirect:/role/list";
        }
    }

    @GetMapping("/detail/{id}")
    public String detailUser(@PathVariable("id") String id, Model model) {
        model.addAttribute("role", roleService.findRoleById(id));
        if (id == null) {
            throw new EntityNotFoundException("Could not find role with ID: " + id);
        }
        return "Role/updateRole";
    }

    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable("id") String id, RedirectAttributes model, @ModelAttribute("role") Role role) {
        roleService.saveRole(role);
        model.addFlashAttribute("success", "Update thanh cong !!!");
        return "redirect:/role/list";
    }
}
