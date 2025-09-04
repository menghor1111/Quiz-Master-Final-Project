import controller.AuthController;
import util.Singleton;
import view.AuthView;

import java.util.Scanner;

public class App {
    private static final AuthController userController = Singleton.getUserControllerInstance();
    private static final AuthView userView = Singleton.getUserViewInstance();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                Integer option = userView.showMainMenu();
                if (option == 0) {
                    System.out.println("Exit program...");
                    break;
                }
                switch (option) {
                    case 1 -> userController.register();
                    case 2 -> userController.login();
                    default -> System.out.println("Invalid Option !!! Please Enter again");
                }
            } catch (RuntimeException e) {
                System.out.println("Input Error : " + e.getMessage());;
            }

        }
    }
}
