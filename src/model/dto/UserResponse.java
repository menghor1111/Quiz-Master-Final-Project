package model.dto;

import java.time.LocalDateTime;

public record UserResponse(
        Integer userId,
        Integer roleId,
        String username,
        String email,
        LocalDateTime createAt,
        LocalDateTime updateAt

) {
}
