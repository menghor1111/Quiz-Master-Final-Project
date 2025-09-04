package controller;

import exception.UserException;
import model.entity.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;
import model.mapper.UserMapper;
import model.mapper.UserMapperImpl;
import model.service.user.UserService;
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
        UserRequest  newUser = authView.register();

        try {
            UserResponse existUser = userService.createUser(newUser);
            if (existUser != null) {
                System.out.println("Register successfully");
            } else {
                System.out.println("Fail to register!!");
            }

        }catch (UserException e)
        {
            System.out.println("Register Error : " + e.getMessage());
        }
    }

    public void login(){
        UserLoginRequest loginRequest = authView.login();
        try {
            UserLoginResponse loginResponse = userService.getUser(loginRequest);

            System.out.println("Login successful!");
            User currentUser = userMapper.fromLoginResponseToUserModel(loginResponse);
            Session.getSessionInstance().setCurrenUser(currentUser);
            dashBoardView.showDashboard(currentUser);

        } catch (UserException e) {
            System.out.println("Login Error :" + e.getMessage());
        }

    }
}
