package model.service.user;

import exception.UserException;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request) throws UserException;
    UserLoginResponse getUser(UserLoginRequest request) throws UserException;
}
