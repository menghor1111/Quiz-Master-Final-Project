package model.dto.user;

public record UserLoginRequest(
        String email,
        String password
) {
}
