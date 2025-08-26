package controller;

import model.User;
import model.dto.UserLoginRequest;
import model.dto.UserLoginResponse;
import model.dto.UserRequest;
import model.dto.UserResponse;
import model.mapper.UserMapper;
import model.mapper.UserMapperImpl;
import model.service.AuthService;
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

    public void registerUser(){
        UserRequest  newUser = authView.register();

        UserResponse existUser = userService.createUser(newUser);
        if(existUser != null)
        {
            System.out.println("Register successfully");
        }
        else
        {
            System.out.println("Fail to register!!");
        }

    }

    public void login(){
        UserLoginRequest loginRequest = authView.login();
        try {
            UserLoginResponse loginResponse = userService.login(loginRequest);

            System.out.println("Login successful! Welcome + " + loginResponse.username());
            User currentUser = userMapper.fromLoginResponseToUserModel(loginResponse);
            Session.getSessionInstance().setCurrenUser(currentUser);
            dashBoardView.showDashboard(currentUser);


        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


    }
}
