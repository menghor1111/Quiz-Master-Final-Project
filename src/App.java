import controller.AuthController;
import util.Singleton;
import view.AuthView;

import java.util.Scanner;

public class App {
    private static final AuthController USER_CONTROLLER = Singleton.getAuthControllerInstance();
    private static final AuthView AUTH_VIEW = Singleton.getAuthViewInstance();
    public static void main(String[] args) {

        boolean success = false;

        while (true) {
            try {
                int option = AUTH_VIEW.showMainMenu();
                if (option == 0) {
                    System.out.println("Exit program...");
                    break;
                }
                    switch (option) {
                        case 1 -> USER_CONTROLLER.register();
                        case 2 -> USER_CONTROLLER.login();
                        default -> {
                            System.out.println("Invalid Option !!! Please Enter again");
                        }
                    }

            } catch (NumberFormatException e) {
                System.out.println("Input Error : " + e.getMessage());
            }
        }
    }
}


