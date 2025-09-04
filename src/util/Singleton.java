package util;

import controller.AuthController;
import model.mapper.UserMapperImpl;
import model.repository.user.UserRepositoryImpl;
import model.service.user.UserServiceImpl;
import view.AuthView;
import view.MenuView;

public class Singleton {

    private Singleton(){}
    private static UserRepositoryImpl userRepository = null;
    private static UserServiceImpl userService = null;
    private static AuthView userView = null;
    private static AuthController userController = null;



    public static synchronized UserRepositoryImpl getUserRepoInstance(){
        if(userRepository == null)
        {
            userRepository = new UserRepositoryImpl();
        }

        return  userRepository;
    }

    public static synchronized UserServiceImpl getUserServiceInstance(){
        if(userService == null)
        {
            userService = new UserServiceImpl(getUserRepoInstance(), new UserMapperImpl());
        }

        return userService;
    }

    public static synchronized AuthView getUserViewInstance(){
        if(userView == null)
        {
            userView = new AuthView();
        }
        return userView;
    }


    public static synchronized AuthController getUserControllerInstance(){
        if(userController == null)
        {
            userController = new AuthController(getUserViewInstance(),getUserServiceInstance(),new MenuView());
        }

        return userController;
    }



}
