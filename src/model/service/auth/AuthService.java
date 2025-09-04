package model.service.auth;

import exception.AuthException;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;

public interface AuthService {

    UserResponse register(UserRequest request) throws AuthException;
    UserLoginResponse login(UserLoginRequest request) throws AuthException;
}
