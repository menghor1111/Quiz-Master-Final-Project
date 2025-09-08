package view;

import model.dto.category.CategoryRequest;
import model.dto.category.CategoryResponse;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class CategoryView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public CategoryRequest showCategoryCreation()
    {
        System.out.print("Enter category name : ");
        return new CategoryRequest(SCANNER.nextLine());
    }

    public void showAllCategories(List<CategoryResponse> categoryResponses)
    {
        Table table = new Table(5,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
             table.addCell("No");
             table.addCell("Category Id");
             table.addCell("Category Name");
             table.addCell("Create At");
             table.addCell("Update At");


           for(int i = 0 ;i < categoryResponses.size() ; i++)
             {
                  CategoryResponse c = categoryResponses.get(i);
                  table.addCell(String.valueOf(i+1));
                  table.addCell(String.valueOf(c.categoryId()));
                  table.addCell(c.categoryName());
                  table.addCell(c.createAt());
                  table.addCell(c.updateAt());
             };

        System.out.println(table.render());
    }


    public int showAllCategoriesForSelect(List<CategoryResponse> categoryResponses){

        System.out.println("==== Category ====");
         categoryResponses.forEach(c->{
             System.out.printf("%d) %s\n",c.categoryId(),c.categoryName());
         });
        System.out.print("Enter category id : ");
        return Integer.parseInt(SCANNER.nextLine());

    }
}
