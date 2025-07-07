package mutsa.mutsa_practice6.service;

import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.dto.request.ProductRequestDto;
import mutsa.mutsa_practice6.dto.response.ProductResponseDto;
import mutsa.mutsa_practice6.entity.Product;
import mutsa.mutsa_practice6.exception.CustomException;
import mutsa.mutsa_practice6.exception.ErrorCode;
import mutsa.mutsa_practice6.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> productResponseDtos = productRepository.findAll().stream()
                .map(product -> new ProductResponseDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getDescription()
                )).collect(Collectors.toList());
        return productResponseDtos;
    }

    public ProductResponseDto getProduct(Long productId) {
        // 존재하지 않으면 404 예외
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        return ProductResponseDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .category(product.getCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    public List<ProductResponseDto> findByProductName(String productName) {
        List<Product> products = productRepository.findByProductName(productName);

        return products.stream()
                .map(product -> ProductResponseDto.builder()
                        .id(product.getProductId())
                        .name(product.getProductName())
                        .category(product.getCategory())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .build()
                ).collect(Collectors.toList());
    }

    //상품 저장
    public Long createProduct(ProductRequestDto productRequestDto) {
        if (productRepository.existsByProductName(productRequestDto.getProductName()))
            throw new CustomException(ErrorCode.DUPLICATED_PRODUCT);

        Product product = Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory())
                .description(productRequestDto.getDescription())
                .build();
        return productRepository.save(product).getProductId();
    }
}
