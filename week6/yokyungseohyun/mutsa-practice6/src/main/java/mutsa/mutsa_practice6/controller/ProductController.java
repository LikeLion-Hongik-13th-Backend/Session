package mutsa.mutsa_practice6.controller;

import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.dto.request.ProductRequestDto;
import mutsa.mutsa_practice6.dto.response.ProductResponseDto;
import mutsa.mutsa_practice6.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //상품 생성
    @PostMapping("/product")
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.createProduct(productRequestDto));
    }

    //상품 전체 조회
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //상품 개별 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    //상품 이름 검색
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> findByProductName(@RequestParam String productName) {
        return ResponseEntity.ok(productService.findByProductName(productName));
    }
}
