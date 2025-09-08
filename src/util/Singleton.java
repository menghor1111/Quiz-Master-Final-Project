package util;

import controller.*;
import model.mapper.category.CategoryMapperImpl;
import model.mapper.quiz.QuizMapperImpl;
import model.mapper.quizType.QuizTypeMapperImpl;
import model.mapper.user.UserMapperImpl;
import model.repository.category.CategoryRepository;
import model.repository.category.CategoryRepositoryImpl;
import model.repository.quiz.QuizRepositoryImpl;
import model.repository.quizType.QuizTypeRepository;
import model.repository.quizType.QuizTypeRepositoryImpl;
import model.repository.user.UserRepositoryImpl;
import model.service.category.CategoryService;
import model.service.category.CategoryServiceImpl;
import model.service.quiz.QuizService;
import model.service.quiz.QuizServiceImpl;
import model.service.quizType.QuizTypeService;
import model.service.quizType.QuizTypeServiceImpl;
import model.service.user.UserServiceImpl;
import view.*;

public class Singleton {

    private Singleton(){}
    private static UserRepositoryImpl userRepository = null;
    private static UserServiceImpl userService = null;
    private static CategoryServiceImpl categoryService = null;
    private static AuthView userView = null;
    private static AuthController authController = null;
    private static UserController userController = null;
    private static CategoryController categoryController = null;
    private static QuizTypeService quizTypeService = null;
    private static QuizTypeController quizTypeController = null;
    private static QuizServiceImpl quizService = null;
    private static QuizController quizController = null;



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

    public static synchronized AuthView getAuthViewInstance(){
        if(userView == null)
        {
            userView = new AuthView();
        }
        return userView;
    }


    public static synchronized AuthController getAuthControllerInstance(){
        if(authController == null)
        {
            authController = new AuthController(getAuthViewInstance(),getUserServiceInstance(),new MenuView());
        }

        return authController;
    }


    public static synchronized UserController getUserControllerInstance(){
        if(userController == null)
        {
            userController = new UserController(new UserView(),getUserServiceInstance());
        }

        return userController;
    }

    public static synchronized CategoryServiceImpl getCategoryServiceInstance(){
        if(categoryService == null)
        {
            categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl(), new CategoryMapperImpl());
        }
        return categoryService;
    }

    public static synchronized CategoryController getCategoryControllerInstance(){
        if(categoryController == null)
        {
            categoryController = new CategoryController(new CategoryView(), getCategoryServiceInstance());
        }
        return categoryController;
    }

    public static synchronized QuizTypeService getQuizTypeServiceInstance(){
        if(quizTypeService == null)
        {
            quizTypeService = new QuizTypeServiceImpl(new QuizTypeRepositoryImpl(), new QuizTypeMapperImpl());
        }
        return quizTypeService;
    }

    public static synchronized QuizTypeController getQuizTypeControllerInstance(){
        if(quizTypeController == null)
        {
            quizTypeController = new QuizTypeController(getQuizTypeServiceInstance(),new QuizTypeView());
        }

        return quizTypeController;
    }

    public static synchronized QuizServiceImpl getQuizServiceInstance(){
        if(quizService == null)
        {
            quizService = new QuizServiceImpl(new QuizRepositoryImpl(),new QuizMapperImpl());
        }

        return quizService;
    }

    public static synchronized QuizController getQuizControllerInstance(){
        if(quizController == null)
        {
            quizController = new QuizController(getQuizServiceInstance(),new QuizView(),getCategoryControllerInstance(),getQuizTypeControllerInstance());
        }
        return quizController;
    }





}
