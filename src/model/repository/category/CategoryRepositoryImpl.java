package model.repository.category;

import exception.CategoryException;
import model.db.DbConnection;
import model.dto.category.CategoryRequest;
import model.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public boolean saveCategory(Category category) throws SQLException {

        if(existsByCategoryName(category.getCategoryName()))
        {
            throw new SQLException("Category already exits");
        }

        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    INSERT INTO categories(category_name)
                    VALUES (?)
                    """;

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1,category.getCategoryName());

                int rowsAffected =  ps.executeUpdate();
                if(rowsAffected == 0)
                {
                    throw new SQLException("Error saving category");
                }
                return rowsAffected > 0;

            }

        }catch (SQLException e)
        {
            System.out.println("Database error : " + e.getMessage());
            return false;
        }
    }



    @Override
    public boolean existsByCategoryName(String name) {

        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    SELECT 1 from categories
                    WHERE category_name = ?
                    """;
            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1,name);
                try(ResultSet rs = ps.executeQuery())
                {
                    return rs.next();
                }
            }
        }catch (SQLException e)
        {
            throw new RuntimeException("Database error : " + e.getMessage());
        }

    }

    @Override
    public Category findCategoryById(int categoryId) {
        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    SELECT * FROM categories
                    WHERE category_id = ?
                    """;
            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1,categoryId);

                try (ResultSet rs = ps.executeQuery()){
                        return new Category(
                                rs.getInt("category_id"),
                                rs.getString("category_name"),
                                String.valueOf(rs.getTimestamp("created_at")),
                                String.valueOf(rs.getTimestamp("updated_at"))
                        );


                }
            }

        }catch (SQLException e)
        {
            throw new RuntimeException("Database error : " + e.getMessage());

        }
    }

    @Override
    public boolean deleteCategoryById(int categoryId) {
        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    DELETE FROM categories
                    WHERE category_id = ?
                    """;

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1,categoryId);
                int rowsAffect = ps.executeUpdate();
                return rowsAffect > 0;
            }

        }catch (SQLException e)
        {
            throw new RuntimeException("Database error : " + e.getMessage());
        }
    }

    @Override
    public List<Category> findAllCategories() {

       try(Connection conn = DbConnection.getInstance())
       {
           String sql = """
                   SELECT * FROM categories
                   """;
           try(PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery())

           {
                  List<Category> categories = new ArrayList<>();
                  while (rs.next())
                  {
                      categories.add(new Category(
                              rs.getInt("category_id"),
                              rs.getString("category_name"),
                              String.valueOf(rs.getTimestamp("created_at")),
                              String.valueOf(rs.getTimestamp("updated_at"))
                      ));
                  }
                  return categories;

           }

       }catch (SQLException e)
       {
           throw new CategoryException("Database error : " + e.getMessage());
       }
    }
}
