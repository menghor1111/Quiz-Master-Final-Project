package model.dto;

public record UserLoginRequest(
        String email,
        String password
) {
}
