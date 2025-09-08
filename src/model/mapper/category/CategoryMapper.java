package model.mapper.category;

import model.dto.category.CategoryRequest;
import model.dto.category.CategoryResponse;
import model.entity.Category;

public interface CategoryMapper {
    Category toCategoryEntity(CategoryRequest request);
    CategoryResponse toCategoryResponse (Category category);

}
