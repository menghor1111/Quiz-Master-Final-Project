package model.dto.user;

public record UserUpdateRequest(
        int userId,
        int updateOption,
        String oldValue,
        String newValue
) {
}
