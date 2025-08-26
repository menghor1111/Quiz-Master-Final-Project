package view;

import model.User;

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

    //==================Admin==================

    private void showAdminDashboard(){

    }




    //==================Creator==================

    private void showCreatorDashboard(){

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
            System.out.print("Enter an option : ");
            int op = Integer.parseInt(SCANNER.nextLine());

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








}
