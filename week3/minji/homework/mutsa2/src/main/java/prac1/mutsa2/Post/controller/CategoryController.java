package prac1.mutsa2.Post.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prac1.mutsa2.Post.dto.CategoryRequestDto;
import prac1.mutsa2.Post.dto.PostRequestDto;
import prac1.mutsa2.Post.service.CategoryService;
import prac1.mutsa2.Post.service.PostService;

@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<String> createPosts(@RequestBody CategoryRequestDto categoryRequestDto){
        try {
            categoryService.createCategory(categoryRequestDto);
            return ResponseEntity.ok("카테고리 생성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("카테고리 생성 실패: " + e.getMessage());
        }
    }
}
