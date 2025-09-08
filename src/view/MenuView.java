package view;

import controller.CategoryController;
import controller.QuizController;
import controller.UserController;
import model.entity.User;
import util.ConstantsUtil;
import util.Singleton;

import java.util.Scanner;


public class MenuView {

    private static final Scanner SCANNER =  new Scanner(System.in);
    private static final UserController  USER_CONTROLLER = Singleton.getUserControllerInstance();
    private static final CategoryController CATEGORY_CONTROLLER =  Singleton.getCategoryControllerInstance();
    private static final QuizController QUIZ_CONTROLLER = Singleton.getQuizControllerInstance();

    public void showDashboard(User currentUser)
    {
        System.out.println("Welcome : " + currentUser.getUsername());
        switch (currentUser.getRoleType())
        {
            case "Admin" -> showAdminDashboard();
            case "Creator" -> showCreatorDashboard();
            case "Player" -> showPlayerDashboard();
        }
    }

    private int getOption(){
        System.out.print("Enter an option : ");
        return Integer.parseInt(SCANNER.nextLine());
    }


//    private void exitProgram(int op)
//    {
//
//        if(op == 0)
//        {
//            System.out.println("Logout Successfully");
//
//        }
//
//    }

    //==================Admin==================

    private void showAdminDashboard(){

        while(true)
        {

            try {


                System.out.println("""
                        ==== Admin Dashboard ====
                        1) Manage Creator
                        2) Manage Player
                        3) Manage Quizzes
                        4) View Result
                        5) View Top 10
                        0) Logout""");
                int op = getOption();


                if (op == 0) {
                    System.out.println("Logout Successfully");
                    break;
                }

//            exitProgram(op);

                switch (op) {
                    case 1 -> manageCreator();
                    case 2 -> managePlayer();
                    case 3 -> manageQuizzes();
                    default -> System.out.println("Invalid option!! Please enter again");
                }
            }catch (NumberFormatException e)
            {
                System.out.println("Input error : " + e.getMessage());
            }
        }

    }


    //==================Creator==================

    private void showCreatorDashboard(){
        while(true)
        {
            try {


                System.out.println("""
                        ==== Creator Dashboard ====
                        1) Manage Quizzes
                        2) View Result
                        3) View Top 10
                        0) Logout""");
                int op = getOption();

            }catch (NumberFormatException e)
            {
                System.out.println("Error input : " + e.getMessage());
            }
        }

    }

    //==================Player==================

    private void showPlayerDashboard(){

        while (true) {
            System.out.println("""
                    ===== Player Dashboard =====
                    1) Start Quiz
                    2) Change Setting
                    0) Logout
                    """);

            int op = getOption();

            switch (op) {
                case 1 -> startQuizMenu();
                case 2 -> changeSetting();
                default -> System.out.println("Invalid option!!! Please try again");
            }

            if(op == 0)
            {
                System.out.println("Logout Successfully");
                break;
            }

        }
    }


    private void startQuizMenu(){

    }
    private void changeSetting(){

    }

    private void manageCreator(){

        while (true)
        {
            try {
                System.out.println("""
                        ==== Manage Creator ====
                        1) Create Creator
                        2) Delete Creator
                        3) Modify Creator
                        4) Creator List
                        0) Exit""");
                int op = getOption();

                switch (op) {
                    case 1 -> USER_CONTROLLER.createCreator();
                    case 2 -> USER_CONTROLLER.deleteCreator();
                    case 3 -> USER_CONTROLLER.updateCreator();
                    case 4 -> USER_CONTROLLER.getAllCreator();
                    default -> System.out.println("Invalid option!! Please enter again");
                }

                if (op== 0) {
                    System.out.println("Exit program...");

                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error Input : " + e.getMessage());
            }
        }
    }

    private void managePlayer(){

        while (true)
        {

            try {
                System.out.println("""
                        ==== Manage Player ====
                        1) Create Player
                        2) Delete Player
                        3) Modify Player
                        4) Player List
                        0) Exit""");
                int op = getOption();


                switch (op)
                {
                    case 1 -> USER_CONTROLLER.createPlayer();
                    case 2 -> USER_CONTROLLER.deletePlayer();
                    case 3 -> USER_CONTROLLER.updatePlayerByAdminOrCreator();
                    case 4 -> USER_CONTROLLER.getAllPlayer();
                    default -> System.out.println("Invalid option!! Please enter again");
                }


                if (op == 0) {
                    System.out.println("Exiting...");
                    break;
                }

            }catch (NumberFormatException e)
            {
                System.out.println("Error input : " + e.getMessage());
            }
        }
    }

    private void manageQuizzes(){

        while (true)
        {
            System.out.println("""
                    ==== Manage Quizzes ====
                    1) Manage Category
                    2) Manage Quiz
                    3) View Quizzes
                    0) Exit""");
            int op = getOption();

            switch (op)
            {
                case 1 -> manageCategory();
                case 2 -> manageQuiz();
                default -> System.out.println("Invalid option!! Please enter again");
            }

            if(op == 0)
            {
                System.out.println("Exiting...");
                break;
            }


        }

    }

    private void viewResult(){

        while (true)
        {
            System.out.println("""
                    ==== View Result ====
                    1) View Result By Individual
                    2) View Result By Specific Category
                    3) View All
                    0) Exit
                    """);
            int op = getOption();
        }
    }

    private void viewTop10(){

        while (true)
        {
            System.out.println("""
                   ==== View Top 10
                   1) View By Specific Category
                   2) View All
                   0) Exit""");
            int op = getOption();
        }

    }


    private void manageCategory(){

        while (true) {
            try {
                System.out.println("""
                        ==== Manage Category ====
                        1) Create Category
                        2) Delete Category
                        3) Update Category
                        4) Category List
                        0) Exit""");
                int op = getOption();
                if(op == 0)
                {
                    System.out.println("Exiting...");
                    break;
                }
                switch (op)
                {
                    case 1 -> CATEGORY_CONTROLLER.creatCategory();
                    case 4 -> CATEGORY_CONTROLLER.categoryList();
                    default -> System.out.println("Invalid option!! Please enter again");
                }


            }catch (NumberFormatException e)
            {
                System.out.println("Error input : " + e.getMessage());
            }

        }
    }


    public void manageQuiz(){
        while (true)
        {
            try{
                System.out.println("""
                        ==== Manage Quiz ====
                        1) Create Quiz
                        2) Delete Quiz
                        3) Update Quiz
                        4) Quiz List
                        0) Exit""");

                int op = getOption();
                if(op == 0)
                {
                    System.out.println("Exiting...");
                    break;
                }
                switch (op){
                    case 1-> QUIZ_CONTROLLER.createQuiz();
                    case 4 -> quizList();
                    default -> System.out.println("Invalid option!! Please enter again");

                }

            }catch (NumberFormatException e)
            {
                System.out.println("Error input : " + e.getMessage());
            }
        }
    }


    public void quizList(){
        while (true)
        {
            try{
                System.out.println("""
                         ==== Quiz List ====
                         1) View All Quizzes
                         2) View Quizzes By Specific Category
                         3) View Quizzes By Specific Quiz Type
                         0) Exit""");
                int option = getOption();

                if(option == 0)
                {
                    System.out.println("Exiting...");
                    break;
                }
                switch (option)
                {
                    case 1 -> QUIZ_CONTROLLER.viewAllQuizzes();
                    default -> System.out.println("Invalid option!! Please enter again");
                }
            }catch (NumberFormatException e)
            {
                System.out.println("Input error : "+ e.getMessage());
            }
        }
    }

}
