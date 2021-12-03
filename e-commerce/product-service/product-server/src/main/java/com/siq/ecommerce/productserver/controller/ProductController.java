package com.siq.ecommerce.productserver.controller;

import com.siq.ecommerce.productclient.dto.product.*;
import com.siq.ecommerce.productserver.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/model/{id}")
    public ResponseEntity<ProductDto> getProductModelById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @PutMapping("/price/{id}")
    public ResponseEntity<ProductDto> updateProductPrice(
            @PathVariable("id") Long id,
            @RequestBody UpdateProductPriceRequest request) {
        return ResponseEntity.ok(productService.updateProductPrice(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProduct(
            @PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<ProductDto> addProduct(
            @PathVariable("id") Long id,
            @RequestBody UpdateProductStockRequest request) {
        return ResponseEntity.ok(productService.addProduct(id, request));
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<ProductDto> removeProduct(
            @PathVariable("id") Long id,
            @RequestBody UpdateProductStockRequest request) {
        return ResponseEntity.ok(productService.removeProduct(id, request));
    }

}
