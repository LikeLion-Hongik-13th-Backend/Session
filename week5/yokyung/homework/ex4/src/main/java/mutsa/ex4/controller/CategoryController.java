package mutsa.ex4.controller;

import lombok.RequiredArgsConstructor;
import mutsa.ex4.dto.request.CategoryRequestDto;
import mutsa.ex4.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/api/v0/category")
    public ResponseEntity<Long> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return ResponseEntity.status(201).body(categoryService.createCategory(categoryRequestDto));
    }


}