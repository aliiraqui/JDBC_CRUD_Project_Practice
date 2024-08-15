package Action;

import domain.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // DAO -> Data Access Object
    // CRUD -> Create, Read, Update, Delete

    // Create
    public void createUser(String name, String email) throws SQLException {
        String createUserQuery = "INSERT INTO USERS (NAME, EMAIL) VALUES (?, ?)";
        try (
                Connection connection = DataBaseUtil.connection();
                PreparedStatement preparedStatement = connection.prepareStatement(createUserQuery)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);

            preparedStatement.executeQuery();

            System.out.println("~~~ User " + name + " Created ~~~");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // Read
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM USERS";
        try (
                Connection connection = DataBaseUtil.connection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String email = resultSet.getString("EMAIL");
                usersList.add(new Users(id, name, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    // Update
    public void updateUser(int id, String name, String email) {
        String updateUserQuery = "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE ID = ?";
        try (Connection connection = DataBaseUtil.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateUserQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("~~~ User Data Got Updated ~~~");
            } else
                System.out.println("~~~ User Not Found ~~~");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void deleteUser(int id) {
        String deleteUserQuery = "DELETE FROM USERS WHERE ID = ?";
        try (Connection connection = DataBaseUtil.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUserQuery)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("~~~ User Data Got Updated ~~~");
            } else
                System.out.println("~~~ User Not Found ~~~");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
