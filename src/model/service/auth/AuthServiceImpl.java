package model.service.auth;

import exception.AuthException;
import model.entity.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;
import model.mapper.UserMapperImpl;
import model.repository.user.UserRepositoryImpl;
import util.ValidationUtil;

public class AuthServiceImpl implements AuthService {

    private final UserRepositoryImpl userRepository;
    private final UserMapperImpl userMapper;

    public AuthServiceImpl(UserRepositoryImpl userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponse register(UserRequest request) {
        validateRequest(request);
        User user = userMapper.toUserModel(request);

        User saveUser = userRepository.saveUser(user);
        return userMapper.toUserResponse(saveUser);

    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) throws AuthException {

        User user = userRepository.findByEmail(request.email());
        if(user == null || !user.getPassword().equals(request.password()))
        {
            throw new AuthException("Invalid email or password");
        }

        return userMapper.toUserLoginResponse(user);
    }


    private void validateRequest(UserRequest request) throws AuthException
    {
        String usernameError = ValidationUtil.isValidUsername(request.username());
        if(usernameError != null) throw new  AuthException(usernameError);

        String emailError = ValidationUtil.isValidEmail(request.email());
        if(emailError != null) throw new AuthException(emailError);

        String passwordError = ValidationUtil.isValidPassword(request.password());
        if(passwordError != null) throw new AuthException(passwordError);
    }

}
