package view;

import model.entity.User;

import java.util.Scanner;


public class MenuView {

    private final static Scanner SCANNER =  new Scanner(System.in);

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


    private void exitProgram(int op)
    {

        if(op == 0)
        {
            System.out.println("Logout Successfully");
            System.exit(0);
        }

    }

    //==================Admin==================

    private void showAdminDashboard(){

        while(true)
        {
            System.out.println("""
                    ==== Admin Dashboard ====
                    1) Manage Creator
                    2) Manage Player
                    3) Manage Quizzes
                    4) View Result
                    5) View Top 10
                    0) Logout""");
            int op = getOption();
        }

    }


    //==================Creator==================

    private void showCreatorDashboard(){
        while(true)
        {
            System.out.println("""
                    ==== Admin Dashboard ====
                    1) Manage Quizzes
                    2) View Result
                    3) View Top 10
                    0) Logout""");
            int op = getOption();
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
            System.out.println("""
                    ==== Manage Creator ====
                    1) Create Creator
                    2) Delete Creator
                    3) Modify Creator
                    4) Creator List
                    0) Exit
                    """);
            int op = getOption();

            exitProgram(0);
        }
    }

    private void managePlayer(){

        while (true)
        {
            System.out.println("""
                    ==== Manage Player ====
                    1) Create Player
                    2) Delete Player
                    3) Modify Player
                    4) Player List
                    0) Exit
                    """);
            int op = getOption();

            if(op == 0)
            {
                System.out.println("Exiting...");
                break;
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
                    0) Exit
                    """);
            int op = getOption();


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

}
