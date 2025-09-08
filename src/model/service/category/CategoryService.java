package model.service.category;

import exception.CategoryException;
import model.dto.category.CategoryRequest;
import model.dto.category.CategoryResponse;
import model.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    boolean createCategory(CategoryRequest request) throws CategoryException;
    boolean deleteCategory(int categoryId) throws CategoryException;
    List<CategoryResponse> getAllCategories() throws CategoryException;

}
