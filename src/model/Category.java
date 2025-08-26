package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private Integer categoryId;
    private String categoryName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Category(String categoryName, LocalDateTime createAt, LocalDateTime updateAt) {
        this.categoryName = categoryName;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
