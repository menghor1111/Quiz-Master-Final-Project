package model.repository.category;

import model.dto.category.CategoryRequest;
import model.dto.category.CategoryResponse;
import model.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryRepository {
    boolean saveCategory(Category category) throws SQLException;
    boolean existsByCategoryName(String categoryName);
    Category findCategoryById(int categoryId);
    boolean deleteCategoryById(int categoryId);
    List<Category> findAllCategories();

}
