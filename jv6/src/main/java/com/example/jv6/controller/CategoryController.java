package com.example.jv6.controller;

import com.example.jv6.entity.Category;
import com.example.jv6.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
    public String listCategory(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/list/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model) {
        int pageSize = 3;
        Page<Category> page = categoryService.findPaginated(pageNo, pageSize);
        List<Category> categoryList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("category", categoryList);
        return "Category/category";
    }

    @GetMapping("/search")
    public String viewAdd(@Param("key") String key, Model model) {
        if (key == "") {
            return "redirect:/category/list";
        } else {
            List<Category> category = categoryService.searchCategory(key);
            model.addAttribute("category", category);
        }
        return "Category/findCategory";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, RedirectAttributes model) {
        categoryService.delete(id);
        model.addFlashAttribute("success", "Xoa thanh cong !!!");
        return "redirect:/category/list";
    }

    @GetMapping("/addCategory")
    public String viewAdd(Model model) {
        model.addAttribute("category", new Category());
        return "Category/addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category, RedirectAttributes model) {
        categoryService.add(category);
        model.addFlashAttribute("success", "Them thanh cong !!!");
        return "redirect:/category/list";
    }

    @GetMapping("/detail/{id}")
    public String detailUser(@PathVariable("id") String id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        if (id == null) {
            throw new EntityNotFoundException("Could not find category with ID: " + id);
        }
        return "Category/updateCategory";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") String id, RedirectAttributes model, @ModelAttribute("category") Category category) {
        categoryService.add(category);
        model.addFlashAttribute("success", "Update thanh cong !!!");
        return "redirect:/category/list";
    }
}
