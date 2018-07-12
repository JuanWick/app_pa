package fr.esgi.components.category.adapter;

import entities.Category;
import fr.esgi.components.category.dto.CategoryApiDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryApiAdapter {
    public Category dtoToModel(CategoryApiDto categoryApiDto) {
        Category category = new Category();
        category.setName(categoryApiDto.getName());
        category.setId(categoryApiDto.getId());
        return category;
    }

    public CategoryApiDto modelToDto(Category category) {
        return CategoryApiDto.builder()
                .name(category.getName())
                .id(category.getId())
                .build();
    }

    public List<CategoryApiDto> modelsToDtos(List<Category> allCategorie) {
        List<CategoryApiDto> categoryApiDtos = new ArrayList<>();
        for(Category category : allCategorie){
            categoryApiDtos.add(modelToDto(category));
        }
        return categoryApiDtos;
    }
}
