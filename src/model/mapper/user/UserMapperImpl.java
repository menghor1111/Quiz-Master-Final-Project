package model.mapper.user;

import model.entity.User;
import model.dto.user.UserLoginRequest;
import model.dto.user.UserLoginResponse;
import model.dto.user.UserRequest;
import model.dto.user.UserResponse;

public class UserMapperImpl implements UserMapper {
    @Override
    public User toUserModel(UserRequest request) {
        return new User(request.roleId(),
                        request.username(),
                        request.email(),
                        request.password()

                        );
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getUserId(),
//                user.getRoleId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreateAt(),
                user.getUpdateAt()
        );
    }

    @Override
    public User toUserLoginModel(UserLoginRequest request) {
        return new User(
                request.email(),
                request.password()
        );
    }

    @Override
    public UserLoginResponse toUserLoginResponse(User user) {
        return new UserLoginResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoleId(),
                user.getRoleType()
        );
    }

    @Override
    public User fromLoginResponseToUserModel(UserLoginResponse response) {
        return new User(
                response.userId(),
                response.username(),
                response.email(),
                response.roleId(),
                response.roleType()
        );
    }
}
