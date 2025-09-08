package model.dto.category;

public record CategoryResponse(
        int categoryId,
        String categoryName,
        String createAt,
        String updateAt
) {
}
