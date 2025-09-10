package controller;

import exception.UserException;
import model.entity.User;
import model.dto.user.UserLoginRequest;
import model.dto.user.UserLoginResponse;
import model.dto.user.UserRequest;
import model.dto.user.UserResponse;
import model.mapper.user.UserMapper;
import model.mapper.user.UserMapperImpl;
import model.service.user.UserService;
import util.ConstantsUtil;
import util.Session;
import view.AuthView;
import view.MenuView;

public class AuthController {

    private final AuthView authView;
    private final UserService userService;
    private final MenuView dashBoardView;
    private final UserMapper userMapper = new UserMapperImpl();

    public AuthController(AuthView authview, UserService userService, MenuView dashBoardView) {
        this.authView = authview;
        this.userService = userService;
        this.dashBoardView = dashBoardView;

    }

    public void register(){
        while (true){
        try {
            UserRequest  newUser = authView.showRegister();
            UserResponse existUser = userService.createUser(newUser);
            if (existUser != null) {
                System.out.println("Register successfully.");
                break;
            } else {
                System.out.println("Failed to register.");
                break;
            }

        }catch (UserException e)
        {
            System.out.println("Error register: " + e.getMessage());
        }
    }
    }

    public void login(){

        while (true) {
            try {
                UserLoginRequest loginRequest = authView.showLogin();
                UserLoginResponse loginResponse = userService.getUser(loginRequest);

                if (loginResponse != null) {
                    System.out.println("Login successful.");
                    User currentUser = userMapper.fromLoginResponseToUserModel(loginResponse);
                    Session.getSessionInstance().setCurrenUser(currentUser);
                    dashBoardView.showDashboard(currentUser);
                    break;
                } else {
                    System.out.println("Failed to login.");
                    break;
                }

            } catch (UserException e) {
                System.out.println("Error login:" + e.getMessage());
            }
        }

    }
}
