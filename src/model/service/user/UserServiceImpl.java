package model.service.user;

import exception.UserException;
import model.dto.user.*;
import model.entity.User;
import model.mapper.user.UserMapperImpl;
import model.repository.user.UserRepositoryImpl;
import org.mindrot.jbcrypt.BCrypt;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final UserMapperImpl userMapper;

    public UserServiceImpl(UserRepositoryImpl userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public UserResponse createUser(UserRequest request) throws UserException{

        if(userRepository.findByUsername(request.username()) != null)
        {
            throw new UserException("Username already exists");
        }
        if (userRepository.findByEmail(request.email()) != null) {
            throw new UserException("Email already exists");
        }

        validateRequest(request);


        User user = userMapper.toUserModel(request);

        User saveUser = userRepository.saveUser(user);
        return userMapper.toUserResponse(saveUser);

    }

    @Override
    public UserLoginResponse getUser(UserLoginRequest request) throws UserException {

        User user = userRepository.findByEmailAndPassword(request.email(), request.password());
        if(user == null)
        {
            throw new UserException("Invalid email or password");
        }

        return userMapper.toUserLoginResponse(user);
    }

    @Override
    public boolean deleteUser(int userId) throws UserException {

        return userRepository.deleteUserById(userId);
    }

    @Override
    public UserResponse getUserById(int userId,int roleId) throws UserException {

//        UserResponse existsUser = userMapper.toUserResponse(userRepository.findUserById(userId,roleId));
        User existsUser = userRepository.findUserById(userId,roleId);

        if(userId <= 0)
        {
            throw new UserException("Id must be greater than 0.");
        }


        if(existsUser == null)
        {
            throw new UserException("User not found.");

        }


        return userMapper.toUserResponse(existsUser);
    }



    @Override
    public boolean updateUser(UserUpdateRequest request, int roleId) throws UserException {
        validateUpdateRequest(request);

        User existsUser = userRepository.findUserById(request.userId(), roleId);
        if (existsUser == null) {
            throw new UserException("User not found.");
        }

        switch (request.updateOption()) {
            case 1 -> {
                if (!existsUser.getUsername().equals(request.oldValue())) {
                    throw new UserException("Old username does not match.");



                }
                if (userRepository.findByUsername(request.newValue()) != null) {
                    throw new UserException("Username already exists.");
                }
                existsUser.setUsername(request.newValue());

            }

            case 2 -> {
                if (!existsUser.getEmail().equals(request.oldValue())) {
                    throw new UserException("Old email does not match.");
                }
                if (userRepository.findByEmail(request.newValue()) != null) {
                    throw new UserException("Email already exists.");
                }
                existsUser.setEmail(request.newValue());
            }

            case 3 -> {
                if (!BCrypt.checkpw(request.oldValue(), existsUser.getPassword())) {
                    throw new UserException("Old password does not match.");
                }
                existsUser.setPassword(request.newValue());
            }

            default -> throw new UserException("Invalid Option. Please try again.");
        }

        return userRepository.updateUser(existsUser);
    }











    @Override
    public List<UserResponse> getAllUsers(int roleId) throws UserException {
        List<User> users = userRepository.getAllUsers(roleId);
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> {
           userResponses.add(userMapper.toUserResponse(user));

        });
        return userResponses;

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


    private void validateUpdateRequest(UserUpdateRequest request) throws UserException
    {

        if(request.updateOption() == 1)
        {
            String usernameError = ValidationUtil.isValidUsername(request.newValue());
            if(usernameError != null) throw new  UserException(usernameError);
        }
        else if(request.updateOption() == 2)
        {
            String emailError = ValidationUtil.isValidEmail(request.newValue());
            if(emailError != null) throw new UserException(emailError);
        }
        else if(request.updateOption() == 3)
        {
            String passwordError = ValidationUtil.isValidPassword(request.newValue());
            if(passwordError != null) throw new UserException(passwordError);
        }


    }

}
