package com.siq.ecommerce.productserver.controller;

import com.siq.ecommerce.productclient.dto.category.*;
import com.siq.ecommerce.productserver.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(service.createCategory(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable("id") Long id,
            @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(service.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCategory(
            @PathVariable("id") Long id) {
        service.deleteCategory(id);
        return ResponseEntity.ok(id);
    }

}
