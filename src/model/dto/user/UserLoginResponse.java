package model.dto.user;

public record UserLoginResponse(
        Integer userId,
        String username,
        String email,
        Integer roleId,
        String roleType
) {
}
