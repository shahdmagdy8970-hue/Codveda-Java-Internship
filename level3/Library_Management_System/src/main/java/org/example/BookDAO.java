
package org.example;

import java.sql.*;

public class BookDAO {

    // 1. Add a new book
    public static void addBook(String title, String author)
            throws SQLException {

        String sql = "INSERT INTO books (title, author, available) VALUES (?, ?, TRUE)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, author);

            stmt.executeUpdate();

            System.out.println("Book added successfully!");
        }
    }

    // 2. View all books
    public static void viewBooks() throws SQLException {

        String sql = "SELECT * FROM books";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                System.out.println("--------------------");
                System.out.println("ID: " + rs.getInt("book_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Available: " + rs.getBoolean("available"));
            }
        }
    }

    // 3. Update a book
    public static void updateBook(int id, String title, String author)
            throws SQLException {

        String sql = "UPDATE books SET title = ?, author = ? WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("Book updated successfully!");
            else
                System.out.println("Book not found.");
        }
    }

    // 4. Delete a book
    public static void deleteBook(int id) throws SQLException {

        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("Book deleted successfully!");
            else
                System.out.println("Book not found.");
        }
    }
}
