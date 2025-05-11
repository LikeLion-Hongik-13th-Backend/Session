package prac1.mutsa2.Post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac1.mutsa2.Post.dto.CategoryRequestDto;
import prac1.mutsa2.Post.dto.PostRequestDto;
import prac1.mutsa2.Post.entity.Category;
import prac1.mutsa2.Post.entity.Post;
import prac1.mutsa2.Post.entity.PostCategory;
import prac1.mutsa2.Post.repository.CategoryRepository;
import prac1.mutsa2.Post.repository.PostCategoryRepository;
import prac1.mutsa2.User.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final PostCategoryRepository postCategoryRepository;

    public void createCategory(CategoryRequestDto categoryRequestDto){
        Category category = new Category();
        category.setCategory(categoryRequestDto.getCategory());
        categoryRepository.save(category);
    }

    public void findCategory(Post post, List<Long> categoryIds){
        for (Long categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("카테고리 없음"));

            PostCategory postCategory = PostCategory.builder()
                    .post(post)
                    .category(category)
                    .build();

            postCategoryRepository.save(postCategory);
        }
    }
}
