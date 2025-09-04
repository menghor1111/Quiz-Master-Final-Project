package controller;

import exception.AuthException;
import model.entity.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;
import model.mapper.UserMapper;
import model.mapper.UserMapperImpl;
import model.service.auth.AuthService;
import util.Session;
import view.AuthView;
import view.MenuView;

public class AuthController {

    private final AuthView authView;
    private final AuthService userService;
    private final MenuView dashBoardView;
    private final UserMapper userMapper = new UserMapperImpl();

    public AuthController(AuthView authview, AuthService userService, MenuView dashBoardView) {
        this.authView = authview;
        this.userService = userService;
        this.dashBoardView = dashBoardView;

    }

    public void register(){
        UserRequest  newUser = authView.register();

        try {
            UserResponse existUser = userService.register(newUser);
            if (existUser != null) {
                System.out.println("Register successfully");
            } else {
                System.out.println("Fail to register!!");
            }

        }catch (AuthException e)
        {
            System.out.println("Register Error : " + e.getMessage());
        }
    }

    public void login(){
        UserLoginRequest loginRequest = authView.login();
        try {
            UserLoginResponse loginResponse = userService.login(loginRequest);

            System.out.println("Login successful!");
            User currentUser = userMapper.fromLoginResponseToUserModel(loginResponse);
            Session.getSessionInstance().setCurrenUser(currentUser);
            dashBoardView.showDashboard(currentUser);

        } catch (AuthException e) {
            System.out.println("Login Error :" + e.getMessage());
        }

    }
}
