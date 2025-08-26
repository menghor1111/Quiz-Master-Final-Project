package model.dto;

public record UserLoginResponse(
        Integer userId,
        String username,
        String email,
        Integer roleId,
        String roleType
) {
}
