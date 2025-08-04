package mutsa.mutsa_practice6.controller;

import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.dto.request.ProductRequestDto;
import mutsa.mutsa_practice6.dto.response.ApiResponse;
import mutsa.mutsa_practice6.dto.response.ProductResponseDto;
import mutsa.mutsa_practice6.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //상품 생성
    @PostMapping("/products")
    public ResponseEntity<ApiResponse<Long>> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(productService.createProduct(productRequestDto)));
    }

    //상품 전체 조회
    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts() {
        return ResponseEntity.ok(ApiResponse.success(productService.getAllProducts()));
    }

    //상품 개별 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProduct(productId)));
    }

    //상품 이름 검색
    @GetMapping(value = "/products", params = "productName") //productName 쿼리 파라미터가 있을 때만 이 핸들러 호출
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> findByProductName(@RequestParam String productName) {
        return ResponseEntity.ok(ApiResponse.success(productService.findByProductName(productName)));
    }
}
