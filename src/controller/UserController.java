package controller;

import exception.UserException;
import model.dto.user.UserRequest;
import model.dto.user.UserResponse;
import model.dto.user.UserUpdateRequest;
import model.service.user.UserService;
import util.ConstantsUtil;
import view.UserView;

import java.util.List;

public class UserController {

    private final UserView userView;
    private final UserService userService;



    public UserController(UserView userView, UserService userService) {
        this.userView = userView;
        this.userService = userService;

    }
    public void createCreator()
    {
        boolean success = false;
        while (!success) {


            try {
                UserRequest request = userView.showCreateUser(ConstantsUtil.CREATOR_ID,"Create Creator");
                UserResponse existUser = userService.createUser(request);

                if (existUser != null) {
                    System.out.println("Creator created successfully!");
                    success = true;
                } else {
                    System.out.println("Failed to create creator");
                }

            } catch (UserException e) {
                System.out.println("Error creating creator : " + e.getMessage());
            }
        }
    }

    public void deleteCreator(){

        boolean success = false;
        while (!success) {
            try {
                int userId = userView.showUserId();
                UserResponse userResponse = userService.getUserById(userId, ConstantsUtil.CREATOR_ID);
                while (true) {
                    String confirmInput = userView.showConfirmDelete(userResponse.username());

                    if (confirmInput.equals("y") || confirmInput.equals("yes")) {

                        if (userService.deleteUser(userId)) {
                            System.out.println("Creator deleted successfully.");
                        } else {
                            System.out.println("Failed to delete creator.");
                        }
                        success = true;
                        break;


                    } else if (confirmInput.equals("n") || confirmInput.equals("no")) {
                        System.out.printf("Delete cancelled. creator %s was not removed.\n", userResponse.username());
                        success = true;
                        break;

                    } else {
                        System.out.println("Invalid Input. Please enter yes or no. ");
                    }
                }
            } catch (UserException | NumberFormatException e) {
                System.out.println("Error deleting creator : " + e.getMessage());
            }
        }

    }

    public void updateCreator(){

        boolean success = false;

        while (!success) {

            try {

                int userId = userView.showUserId();

                while (true) {



                        UserResponse userResponse = userService.getUserById(userId, ConstantsUtil.CREATOR_ID);

                        if (userResponse != null) {

                            int option = userView.showModifyUser();

                            while (true)
                            {


                                try {

                                    UserUpdateRequest request = userView.showUpdateUser(userId, option);

                                    if (request == null) {
                                        return;
                                    }
                                    if (userService.updateUser(request, ConstantsUtil.CREATOR_ID)) {
                                        if(option == 1)
                                        {
                                            System.out.println("Update creator username successfully.");
                                        } else if (option == 2) {

                                            System.out.println("Update creator email successfully.");
                                        } else if (option == 3) {

                                            System.out.println("Update creator password successfully.");
                                        }

                                        success = true;
                                        break;
                                    } else {
                                        System.out.println("Failed to update creator.");

                                    }
                                }catch (UserException e)
                                {
                                    System.out.println(e.getMessage());
                                }



                        }}

                }

            } catch (UserException | NumberFormatException e) {
                System.out.println("Error Update Creator : " + e.getMessage());
            }

        }

    }

    public void getAllCreator(){

        userView.ShowAllUsers(userService.getAllUsers(ConstantsUtil.CREATOR_ID));
    }










    public void createPlayer()
    {
        boolean success = false;
        while (!success) {


            try {
                UserRequest request = userView.showCreateUser(ConstantsUtil.PLAYER_ID,"Creator Player");
                UserResponse existUser = userService.createUser(request);

                if (existUser != null) {
                    System.out.println("Player created successfully!");
                    success = true;
                } else {
                    System.out.println("Failed to create Player");
                }

            } catch (UserException e) {
                System.out.println("Error creating player : " + e.getMessage());
            }
        }
    }

    public void deletePlayer(){

        boolean success = false;
        while (!success) {
            try {
                int userId = userView.showUserId();
                UserResponse userResponse = userService.getUserById(userId, ConstantsUtil.PLAYER_ID);
                while (true) {
                    String confirmInput = userView.showConfirmDelete(userResponse.username());

                    if (confirmInput.equals("y") || confirmInput.equals("yes")) {

                        if (userService.deleteUser(userId)) {
                            System.out.println("Player deleted successfully.");
                        } else {
                            System.out.println("Failed to delete player.");
                        }
                        success = true;
                        break;


                    } else if (confirmInput.equals("n") || confirmInput.equals("no")) {
                        System.out.printf("Delete cancelled. player %s was not removed.\n", userResponse.username());
                        success = true;
                        break;

                    } else {
                        System.out.println("Invalid Input. Please enter yes or no. ");
                    }
                }
            } catch (UserException | NumberFormatException e) {
                System.out.println("Error deleting player : " + e.getMessage());
            }
        }

    }



    public void updatePlayerByAdminOrCreator()
    {
        updatePlayer(userView.showUserId());
    }




    public void updatePlayer(int userId){

        boolean success = false;

        while (!success) {

            try {

//                int userId = userView.showUserId();

                while (true) {

                    UserResponse userResponse = userService.getUserById(userId, ConstantsUtil.PLAYER_ID);


                    if (userResponse != null) {

                        int option = userView.showModifyUser();

                        while (true)
                        {


                            try {


                                UserUpdateRequest request = userView.showUpdateUser(userId, option);

                                if (request == null) {
                                    return;
                                }
                                if (userService.updateUser(request, ConstantsUtil.PLAYER_ID)) {
                                    if(option == 1)
                                    {
                                        System.out.println("Update player username successfully.");
                                    } else if (option == 2) {

                                        System.out.println("Update player email successfully.");
                                    } else if (option == 3) {

                                        System.out.println("Update player password successfully.");
                                    }

                                    success = true;
                                    break;
                                } else {
                                    System.out.println("Failed to update player.");
                                    break;
                                }
                            }catch (UserException e)
                            {
                                System.out.println(e.getMessage());
                            }



                        }}

                }

            } catch (UserException | NumberFormatException e) {
                System.out.println("Error Update player : " + e.getMessage());
            }

        }

    }




    public void getAllPlayer(){

        userView.ShowAllUsers(userService.getAllUsers(ConstantsUtil.PLAYER_ID));
    }









}
