package model.mapper;

import model.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;

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
                user.getRoleId(),
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



//    public User(Integer userId, String username,String email, Integer roleId,String roleType) {
//        this.userId = userId;
//        this.username = username;
//        this.email = email;
//        this.roleId = roleId;
//        this.roleType = roleType;
//
//    }





}
