package com.siq.ecommerce.productserver.service;

import com.siq.ecommerce.productclient.dto.product.*;
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
public class ProductService {

    private final ProductDtoConverter productDtoConverter;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductDto createProduct(@RequestBody CreateProductRequest request) {
        Product product = new Product(request.getName(),
                categoryService.findCategoryById(request.getCategoryId()),
                request.getPrice());

        return productDtoConverter.convert(productRepository.save(product));
    }

    public ProductDto getProductById(Long id) {
        return productDtoConverter.convert(findProductById(id));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, @RequestBody UpdateProductRequest request) {
        Product updatedProduct = findProductById(id);

        Category category = categoryService.findCategoryById(request.getCategoryId());

        updatedProduct.setCategory(category);
        updatedProduct.setName(request.getName());

        return productDtoConverter.convert(productRepository.save(updatedProduct));
    }

    public ProductDto updateProductPrice(Long id, @RequestBody UpdateProductPriceRequest request) {
        Product updatedProduct = findProductById(id);

        updatedProduct.setPrice(request.getPrice());

        return productDtoConverter.convert(productRepository.save(updatedProduct));
    }

    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    protected Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product could not find by id: " + id));
    }

    public ProductDto addProduct(Long id, @RequestBody UpdateProductStockRequest request) {
        Product updatedProduct = findProductById(id);

        updatedProduct.setCount(updatedProduct.getCount() + request.getChange());

        return productDtoConverter.convert(productRepository.save(updatedProduct));
    }

    public ProductDto removeProduct(Long id, UpdateProductStockRequest request) {
        Product updatedProduct = findProductById(id);
        if (updatedProduct.getCount() < request.getChange()) {
            throw new ProductNotFoundException("Not enough Products:"+updatedProduct.getName()+" in stock");
        }

        updatedProduct.setCount(updatedProduct.getCount() - request.getChange());

        return productDtoConverter.convert(productRepository.save(updatedProduct));
    }
}
