package model.mapper;

import model.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;

public interface UserMapper {
       User toUserModel(UserRequest request);
       UserResponse toUserResponse(User user);

       User toUserLoginModel(UserLoginRequest request);
       UserLoginResponse toUserLoginResponse(User user);
       User fromLoginResponseToUserModel(UserLoginResponse response);




       
}
