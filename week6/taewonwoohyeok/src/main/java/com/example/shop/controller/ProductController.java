package com.example.shop.controller;

import com.example.shop.dto.ProductResponse;
import com.example.shop.entity.Product;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.shop.repository.ProductRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")

public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository; // 추가 필요

    // 전체 조회
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    // 아이디로 조회
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    // 이름 검색
    @GetMapping(params = "name")
    public List<ProductResponse> searchProducts(@RequestParam String name) {
        return productService.searchByName(name);
    }

    // 상품 생성
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("상품이 등록되었습니다.");
    }
}