package view;

import model.dto.UserLoginRequest;
import model.dto.UserRequest;
import util.ConstantsUtil;

import java.util.Scanner;

public class AuthView {

    private final static Scanner SCANNER = new Scanner(System.in);

    public Integer showMainMenu() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Enter an option: ");

        return Integer.parseInt(SCANNER.nextLine());
    }


    public UserRequest register(){
        System.out.print("Enter Username : ");
        String username = SCANNER.nextLine();

        System.out.print("Enter Email : ");
        String email = SCANNER.nextLine();

        System.out.print("Enter Password : ");
        String password = SCANNER.nextLine();



        return new UserRequest(ConstantsUtil.PLAYER,username,email,password);



    }


    public UserLoginRequest login(){
        System.out.print("Enter Email : ");
        String email = SCANNER.nextLine();

        System.out.print("Enter Password : ");
        String password = SCANNER.nextLine();

        return new UserLoginRequest(email,password);

    }

}
