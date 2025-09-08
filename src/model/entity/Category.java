package model.entity;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private Integer categoryId;
    private String categoryName;
    private String createAt;
    private String updateAt;

    public Category(Integer categoryId,String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(String categoryName)
    {
        this.categoryName = categoryName;
    }
}
