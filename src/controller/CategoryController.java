package controller;

import exception.CategoryException;
import model.dto.category.CategoryRequest;
import model.dto.category.CategoryResponse;
import model.service.category.CategoryService;
import view.CategoryView;

import java.sql.SQLException;
import java.util.List;

public class CategoryController {

    private final CategoryView categoryView;
    private final CategoryService categoryService;

    public CategoryController(CategoryView categoryView, CategoryService categoryService) {
        this.categoryView = categoryView;
        this.categoryService = categoryService;
    }

    public void creatCategory(){
           while (true)
           {
               try{
                  CategoryRequest categoryRequest =  categoryView.showCategoryCreation();
                  if(categoryService.createCategory(categoryRequest)){
                      System.out.println("Category created successfully.");
                      break;
                  }
                  else {
                      System.out.println("Failed to create category.");
                  }
               }catch (CategoryException e)
               {
                   System.out.println("Error creating category : " + e.getMessage());
               }
           }
    }

    public List<CategoryResponse> getAllCategories(){

        try{
           List<CategoryResponse> categoryResponses = categoryService.getAllCategories();
            if(categoryResponses == null)
            {
                System.out.println("There no category yet.");
                return null;
            }
            return  categoryResponses;


        }catch (CategoryException e)
        {
            System.out.println("Error Show All Categories : " + e.getMessage());
        }
        return null;
    }

    public void categoryList(){

        categoryView.showAllCategories(getAllCategories());

    }

    public int getCategoryId(){

        return categoryView.showAllCategoriesForSelect(getAllCategories());

    }



}
