package model.mapper.category;

import model.dto.category.CategoryRequest;
import model.dto.category.CategoryResponse;
import model.entity.Category;

public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toCategoryEntity(CategoryRequest request) {
        return new Category(
                request.categoryName()
        );
    }

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCreateAt(),
                category.getUpdateAt()
        );
    }
}
