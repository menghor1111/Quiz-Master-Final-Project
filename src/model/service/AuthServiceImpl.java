package model.service;

import model.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;
import model.mapper.UserMapperImpl;
import model.repository.UserRepositoryImpl;
import util.ValidationUtil;

public class AuthServiceImpl implements AuthService {

    private final UserRepositoryImpl userRepository;
    private final UserMapperImpl userMapper;

    public AuthServiceImpl(UserRepositoryImpl userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponse createUser(UserRequest request) {
        validateRequest(request);
        User user = userMapper.toUserModel(request);

        userRepository.saveUser(user);
        return userMapper.toUserResponse(user);

    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {

        User user = userRepository.findByEmail(request.email());
        if(user == null || !user.getPassword().equals(request.password()))
        {
            throw new RuntimeException("Invalid email or password");
        }

        return userMapper.toUserLoginResponse(user);
    }


    private void validateRequest(UserRequest request)
    {
        String usernameError = ValidationUtil.isValidUsername(request.username());
        if(usernameError != null) throw new  RuntimeException(usernameError);

        String emailError = ValidationUtil.isValidEmail(request.email());
        if(emailError != null) throw new RuntimeException(emailError);

        String passwordError = ValidationUtil.isValidPassword(request.password());
        if(passwordError != null) throw new RuntimeException(passwordError);
    }

}
