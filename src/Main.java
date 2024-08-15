import Action.UserDAO;
import domain.Users;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final UserDAO USER_DAO = new UserDAO();

    public static void main(String[] args) throws SQLException {
        Scanner obj = new Scanner(System.in);
        while (true) {
            System.out.println("~~~ Press 1 to Create User ~~~");
            System.out.println("~~~ Press 2 to View All Users ~~~");
            System.out.println("~~~ Press 3 to Update User ~~~");
            System.out.println("~~~ Press 4 to Delete User ~~~");
            System.out.println("~~~ Press 0 to Exit ~~~");
            int choice = obj.nextInt();

            switch (choice) {
                case 1:
                    String l = obj.nextLine();

                    System.out.println("Enter user name");
                    String name = obj.nextLine();
                    System.out.println("Enter user email");
                    String email = obj.nextLine();
                    USER_DAO.createUser(name, email);
                    break;
                case 2:
                    List<Users> usersList = USER_DAO.getAllUsers();
                    usersList.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("enter user id");
                    int id = obj.nextInt();
                    obj.nextLine();
                    System.out.println("enter user name");
                    String updateUserName = obj.nextLine();
                    System.out.println("enter user email");
                    String updateUserEmail = obj.nextLine();
                    USER_DAO.updateUser(id, updateUserName, updateUserEmail);
                    break;
                case 4:
                    System.out.println("Enter user id to delete");
                    int deleteUserID = obj.nextInt();
                    USER_DAO.deleteUser(deleteUserID);
                    break;
                case 5:
                    System.out.println("Exiting........");
                    return;
            }
        }
    }
}
