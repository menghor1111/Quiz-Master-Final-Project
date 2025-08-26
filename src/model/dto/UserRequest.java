package model.dto;

import java.time.LocalDateTime;

public record UserRequest(

        Integer roleId,
        String username,
        String email,
        String password

) {
}
