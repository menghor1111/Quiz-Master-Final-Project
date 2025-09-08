package model.service.user;

import exception.UserException;
import model.dto.user.*;
import model.entity.User;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request) throws UserException;
    UserLoginResponse getUser(UserLoginRequest request) throws UserException;
    boolean deleteUser(int userId) throws UserException;
    UserResponse getUserById(int userId,int roleId) throws UserException;
    boolean updateUser(UserUpdateRequest request, int roleId) throws UserException;
    List<UserResponse> getAllUsers(int roleId) throws UserException;


}
