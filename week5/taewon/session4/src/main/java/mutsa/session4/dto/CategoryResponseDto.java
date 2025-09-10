package mutsa.session4.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mutsa.session4.entity.Category;

@Getter
@Setter
public class CategoryResponseDto {

    private Long id;
    private String name;
    private int hierarchy;
    private List<CategoryResponseDto> childs = new ArrayList<>();


    public static CategoryResponseDto of(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setHierarchy(category.getHierarchy());
        List<CategoryResponseDto> list = new ArrayList<>();
        category.getChilds().forEach(c -> {
            list.add(CategoryResponseDto.of(c));
        });
        categoryResponseDto.setChilds(list);
        categoryResponseDto.setHierarchy(categoryResponseDto.getHierarchy());
        return categoryResponseDto;
    }
}
