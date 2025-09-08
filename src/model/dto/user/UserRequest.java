package model.dto.user;

public record UserRequest(
        Integer roleId,
        String username,
        String email,
        String password

) {
}
