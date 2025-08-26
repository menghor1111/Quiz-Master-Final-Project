package model.service;

import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;

public interface AuthService {

    UserResponse createUser(UserRequest request);
    UserLoginResponse login(UserLoginRequest request);


}
