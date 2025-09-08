package model.service.category;

import exception.CategoryException;
import exception.UserException;
import model.dto.category.CategoryRequest;
import model.dto.category.CategoryResponse;
import model.entity.Category;
import model.mapper.category.CategoryMapper;
import model.repository.category.CategoryRepository;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public boolean createCategory(CategoryRequest request) throws CategoryException{
        validateCategoryRequest(request);
        try {
            return categoryRepository.saveCategory(categoryMapper.toCategoryEntity(request));
        } catch (SQLException e) {
            throw new CategoryException(e.getMessage());
        }
    }
    @Override
    public boolean deleteCategory(int categoryId) throws CategoryException {
        

        return false;
    }

    @Override
    public List<CategoryResponse> getAllCategories() throws CategoryException {

        List<Category> categories = categoryRepository.findAllCategories();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categories.forEach(c->{
                          categoryResponses.add(categoryMapper.toCategoryResponse(c));
        });

        return categoryResponses;
    }

    private void validateCategoryRequest(CategoryRequest request) throws CategoryException
    {
        String categoryNameError = ValidationUtil.isValidCategoryName(request.categoryName());
        if(categoryNameError != null) throw new CategoryException(categoryNameError);
    }
}
