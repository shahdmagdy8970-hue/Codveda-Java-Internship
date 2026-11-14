
package org.example;

import java.sql.*;

public class TransactionDAO {

    // Borrow a book
    public static void borrowBook(int bookId, int userId)
            throws SQLException {

        String checkSql =
                "SELECT available FROM books WHERE book_id = ?";

        String updateBookSql =
                "UPDATE books SET available = FALSE WHERE book_id = ?";

        String insertTransactionSql =
                "INSERT INTO transactions (book_id, user_id, borrow_date, status) " +
                        "VALUES (?, ?, CURDATE(), 'BORROWED')";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                // Check book availability
                try (PreparedStatement check =
                             con.prepareStatement(checkSql)) {

                    check.setInt(1, bookId);

                    try (ResultSet rs = check.executeQuery()) {

                        if (!rs.next()) {
                            throw new SQLException("Book not found.");
                        }

                        if (!rs.getBoolean("available")) {
                            throw new SQLException("Book is not available.");
                        }
                    }
                }

                // Update book availability
                try (PreparedStatement update =
                             con.prepareStatement(updateBookSql)) {

                    update.setInt(1, bookId);
                    update.executeUpdate();
                }

                // Insert transaction
                try (PreparedStatement insert =
                             con.prepareStatement(insertTransactionSql)) {

                    insert.setInt(1, bookId);
                    insert.setInt(2, userId);
                    insert.executeUpdate();
                }

                con.commit();

                System.out.println("Book borrowed successfully!");

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    // Return a book
    public static void returnBook(int bookId, int userId)
            throws SQLException {

        String findTransactionSql =
                "SELECT transaction_id FROM transactions " +
                        "WHERE book_id = ? AND user_id = ? AND status = 'BORROWED' " +
                        "LIMIT 1";

        String updateTransactionSql =
                "UPDATE transactions SET return_date = CURDATE(), " +
                        "status = 'RETURNED' WHERE transaction_id = ?";

        String updateBookSql =
                "UPDATE books SET available = TRUE WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                int transactionId;

                // Find active borrowing transaction
                try (PreparedStatement find =
                             con.prepareStatement(findTransactionSql)) {

                    find.setInt(1, bookId);
                    find.setInt(2, userId);

                    try (ResultSet rs = find.executeQuery()) {

                        if (!rs.next()) {
                            throw new SQLException(
                                    "No active borrowing transaction found."
                            );
                        }

                        transactionId = rs.getInt("transaction_id");
                    }
                }

                // Mark transaction as returned
                try (PreparedStatement update =
                             con.prepareStatement(updateTransactionSql)) {

                    update.setInt(1, transactionId);
                    update.executeUpdate();
                }

                // Make book available again
                try (PreparedStatement updateBook =
                             con.prepareStatement(updateBookSql)) {

                    updateBook.setInt(1, bookId);
                    updateBook.executeUpdate();
                }

                con.commit();

                System.out.println("Book returned successfully!");

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    // View all borrowing and returning transactions
    public static void viewTransactions() throws SQLException {

        String sql =
                "SELECT t.transaction_id, b.title, u.name, " +
                        "t.borrow_date, t.return_date, t.status " +
                        "FROM transactions t " +
                        "JOIN books b ON t.book_id = b.book_id " +
                        "JOIN users u ON t.user_id = u.user_id";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nTransaction History:");

            while (rs.next()) {

                System.out.println("--------------------");
                System.out.println("Transaction ID: " +
                        rs.getInt("transaction_id"));

                System.out.println("Book: " +
                        rs.getString("title"));

                System.out.println("User: " +
                        rs.getString("name"));

                System.out.println("Borrow Date: " +
                        rs.getDate("borrow_date"));

                System.out.println("Return Date: " +
                        rs.getDate("return_date"));

                System.out.println("Status: " +
                        rs.getString("status"));
            }
        }
    }

}
