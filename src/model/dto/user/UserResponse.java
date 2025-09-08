package model.dto.user;

import java.time.LocalDateTime;

public record UserResponse(
        Integer userId,
//        Integer roleId,
        String username,
        String email,
        String createAt,
        String updateAt

) {
}
