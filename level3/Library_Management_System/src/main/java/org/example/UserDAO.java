
package org.example;

import java.sql.*;

public class UserDAO {

    // 1. Add User
    public static void addUser(String name, String email)
            throws SQLException {

        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);

            stmt.executeUpdate();

            System.out.println("User added successfully!");
        }
    }

    // 2. View Users
    public static void viewUsers() throws SQLException {

        String sql = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                System.out.println("--------------------");
                System.out.println("ID: " + rs.getInt("user_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
            }
        }
    }

    // 3. Update User
    public static void updateUser(int id, String name, String email)
            throws SQLException {

        String sql = "UPDATE users SET name = ?, email = ? WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("User updated successfully!");
            else
                System.out.println("User not found.");
        }
    }

    // 4. Delete User
    public static void deleteUser(int id) throws SQLException {

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("User deleted successfully!");
            else
                System.out.println("User not found.");
        }
    }
}
