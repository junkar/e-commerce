package com.siq.ecommerce.productserver.service;

import com.siq.ecommerce.productclient.dto.category.*;
import com.siq.ecommerce.productclient.exception.*;
import com.siq.ecommerce.productserver.converter.*;
import com.siq.ecommerce.productserver.model.*;
import com.siq.ecommerce.productserver.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryDtoConverter converter;

    public CategoryDto createCategory(@RequestBody CreateCategoryRequest createCategoryRequest ) {
        Category newCategory = new Category(createCategoryRequest.getName());

        return converter.convert(repository.save(newCategory));
    }

    public CategoryDto getCategoryById(Long id) {
        return converter.convert(findCategoryById(id));
    }

    public List<CategoryDto> getAllCategories() {

        return repository
                .findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public CategoryDto updateCategory(Long id, @RequestBody UpdateCategoryRequest request ) {

        Category updatedCategory = findCategoryById(id);

        updatedCategory.name = request.getName();

        return converter.convert(repository.save(updatedCategory));
    }

    public void deleteCategory(Long id) {
        Category category = findCategoryById(id);
        repository.delete(category);
    }

    protected Category findCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category could not find by id: " + id));
    }

}
