package model.service.user;

import exception.UserException;
import model.entity.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;
import model.mapper.UserMapperImpl;
import model.repository.user.UserRepositoryImpl;
import util.ValidationUtil;

public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final UserMapperImpl userMapper;

    public UserServiceImpl(UserRepositoryImpl userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        validateRequest(request);

        if(userRepository.findByEmail(request.email()) != null)
        {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = userMapper.toUserModel(request);

        User saveUser = userRepository.saveUser(user);
        return userMapper.toUserResponse(saveUser);

    }

    @Override
    public UserLoginResponse getUser(UserLoginRequest request) throws UserException {

        User user = userRepository.findByEmail(request.email());
        if(user == null || !user.getPassword().equals(request.password()))
        {
            throw new UserException("Invalid email or password");
        }

        return userMapper.toUserLoginResponse(user);
    }


    private void validateRequest(UserRequest request) throws UserException
    {
        String usernameError = ValidationUtil.isValidUsername(request.username());
        if(usernameError != null) throw new  UserException(usernameError);

        String emailError = ValidationUtil.isValidEmail(request.email());
        if(emailError != null) throw new UserException(emailError);

        String passwordError = ValidationUtil.isValidPassword(request.password());
        if(passwordError != null) throw new UserException(passwordError);
    }

}
