package model.service.user;

import exception.UserException;
import model.dto.UserRequest;
import model.entity.User;

public interface UserService {
    User createUser(UserRequest request) throws UserException;
}
