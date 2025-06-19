package mutsa.ex4.service;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.CategoryRequestDto;
import mutsa.ex4.entity.Category;
import mutsa.ex4.exception.CustomException;
import mutsa.ex4.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long createCategory(CategoryRequestDto categoryRequestDto) {
        //중복 확인
        if (categoryRepository.existsByCategoryName(categoryRequestDto.getCategoryName()))
            throw new CustomException("이미 존재하는 카테고리입니다.", HttpStatus.CONFLICT);

        Category category = Category.builder()
                .categoryName(categoryRequestDto.getCategoryName())
                .build();
        return categoryRepository.save(category).getCategoryId();
    }
}
