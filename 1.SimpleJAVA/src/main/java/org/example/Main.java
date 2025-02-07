package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:postgresql://ep-wandering-rice-a5bs1wb2-pooler.us-east-2.aws.neon.tech:5432/neondb";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_5lNQ8sKgfCqY";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void createGenresTable(Connection conn) {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS Genres (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) UNIQUE NOT NULL
            )
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'Genres' checked/created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createGenre(Connection conn, String name) {
        String sql = "INSERT INTO Genres (name) VALUES (?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Genre added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding genre: " + e.getMessage());
        }
    }

    public static void readGenres(Connection conn) {
        String sql = "SELECT * FROM Genres";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Genres List ::");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error reading genres: " + e.getMessage());
        }
    }

    public static void updateGenre(Connection conn, int id, String newName) {
        String sql = "UPDATE Genres SET name = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Genre updated successfully!");
            } else {
                System.out.println("No genre found with this ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating genre :: " + e.getMessage());
        }
    }

    public static void deleteGenre(Connection conn, int id) {
        String sql = "DELETE FROM Genres WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Genre deleted successfully!");
            } else {
                System.out.println("No genre found with this ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting genre :: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                createGenresTable(conn);
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("\nChoose please:");
                    System.out.println("1 - Add Genre");
                    System.out.println("2 - List Genres");
                    System.out.println("3 - Update Genre");
                    System.out.println("4 - Delete Genre");
                    System.out.println("5 - Exit");
                    System.out.print("Enter your choice :: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter genre name :: ");
                            String name = scanner.nextLine();
                            createGenre(conn, name);
                            break;
                        case 2:
                            readGenres(conn);
                            break;
                        case 3:
                            System.out.print("Enter genre ID to update :: ");
                            int idToUpdate = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new name :: ");
                            String newName = scanner.nextLine();
                            updateGenre(conn, idToUpdate, newName);
                            break;
                        case 4:
                            System.out.print("Enter genre ID to delete: ");
                            int idToDelete = scanner.nextInt();
                            deleteGenre(conn, idToDelete);
                            break;
                        case 5:
                            System.out.println("Goodbye!! See you soon!");
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
