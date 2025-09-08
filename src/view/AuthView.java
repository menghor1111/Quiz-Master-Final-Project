package view;

import model.dto.user.UserLoginRequest;
import model.dto.user.UserRequest;
import util.ConstantsUtil;

import java.util.Scanner;

public class AuthView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Integer showMainMenu() {
        System.out.println("=== Quiz Master ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Enter an option: ");

        return Integer.parseInt(SCANNER.nextLine());
    }

    public UserRequest showRegister(){
        System.out.print("Enter your Username : ");
        String username = SCANNER.nextLine();

        System.out.print("Enter your Email : ");
        String email = SCANNER.nextLine();

        System.out.print("Enter your Password : ");
        String password = SCANNER.nextLine();

        return new UserRequest(ConstantsUtil.PLAYER_ID,username,email,password);

    }


    public UserLoginRequest showLogin(){
        System.out.print("Enter Email : ");
        String email = SCANNER.nextLine();

        System.out.print("Enter Password : ");
        String password = SCANNER.nextLine();

        return new UserLoginRequest(email,password);

    }

}
