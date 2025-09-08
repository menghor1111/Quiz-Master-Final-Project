package model.mapper.user;

import model.entity.User;
import model.dto.user.UserLoginRequest;
import model.dto.user.UserLoginResponse;
import model.dto.user.UserRequest;
import model.dto.user.UserResponse;

public interface UserMapper {
       User toUserModel(UserRequest request);
       UserResponse toUserResponse(User user);

       User toUserLoginModel(UserLoginRequest request);
       UserLoginResponse toUserLoginResponse(User user);
       User fromLoginResponseToUserModel(UserLoginResponse response);




       
}
