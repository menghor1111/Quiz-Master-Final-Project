package view;

import model.dto.user.UserRequest;
import model.dto.user.UserResponse;
import model.dto.user.UserUpdateRequest;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);



    private int getOption(){
        System.out.print("Enter an option : ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public UserRequest showCreateUser(int roleId,String text){
        System.out.println("====" + text + "====");
        System.out.print("Enter Username : ");
        String username = SCANNER.nextLine();

        System.out.print("Enter Email : ");
        String email = SCANNER.nextLine();

        System.out.print("Enter Password : ");
        String password = SCANNER.nextLine();



        return new UserRequest(roleId,username,email,password);

    }

    public int showUserId()
    {

            System.out.print("Enter id : ");
            return Integer.parseInt(SCANNER.nextLine());

    }


    public String showConfirmDelete(String userName){
        System.out.printf("Are you sure to delete user %s? (yes/no)  : ",userName);
        return SCANNER.nextLine().trim().toLowerCase();

    }


    public UserUpdateRequest showUpdateUser(int userId,int option)
    {
        String oldValue = "";
        String newValue = "";



       switch (option)
       {
           case 1-> {
               System.out.println("=== Change Username===");
               System.out.print("Enter old username : ");
               oldValue = SCANNER.nextLine();
               System.out.print("Enter new username : ");
               newValue = SCANNER.nextLine();
           }
           case 2->{
               System.out.println("=== Change Email ===");
               System.out.print("Enter old email : ");
               oldValue = SCANNER.nextLine();
               System.out.print("Enter new email : ");
               newValue = SCANNER.nextLine();
           }
           case 3->{
               System.out.println("=== Change Email ===");
               System.out.print("Enter old password : ");
               oldValue = SCANNER.nextLine();
               System.out.print("Enter new password : ");
               newValue = SCANNER.nextLine();
           }
           case 0 -> {
           return  null;
       }
       }
        return new UserUpdateRequest(userId,option,oldValue,newValue);

    }



    public int showModifyUser(){

        while (true)
        {
            try {

                System.out.println("""
                        ==== Modify Option ====
                        1) Change Username
                        2) Change Email
                        3) Change Password
                        0) Exit""");
                int op = getOption();

                if(op == 0)
                {
                    System.out.println("Exiting...");
                    return 0;
                }
                if(op >= 1 && op <=3) return op;

                System.out.println("Invalid option. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Error input : "+e.getMessage());
            }
        }

    }


    public void ShowAllUsers(List<UserResponse> responses)
    {
        Table table = new Table(
                5,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER
        );
        table.addCell("Id     ");
        table.addCell("Username     ");
        table.addCell("email     ");
        table.addCell("Create At");
        table.addCell("Update At");


        responses.forEach(u->{
            table.addCell(String.valueOf(u.userId()));
            table.addCell(u.username());
            table.addCell(u.email());
            table.addCell(u.createAt());
            table.addCell(u.updateAt());
        });

        System.out.println(table.render());
    }




}
