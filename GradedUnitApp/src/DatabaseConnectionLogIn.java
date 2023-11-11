/**
 * A class that handles the connection to the database for log in functionality.
 */

import java.sql.*;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;

public class DatabaseConnectionLogIn {

    // Database URL, username and password
    private final String DB_URL = "jdbc:oracle:thin:@gsv12wvlntrtx4m7_medium?TNS_ADMIN=C:/wall";
    private String DB_USER;
    private String DB_PASSWORD;

    static String loggedInUser;

    /**
     * Constructs a new instance of DatabaseConnectionLogIn and sets the
     * database username and password.
     */
    public DatabaseConnectionLogIn() {
        this.DB_USER = "ADMIN";
        this.DB_PASSWORD = "Gradedunit123";
    }

    /**
     * Connects to the database.
     */
    public void connectToDB() {
        try {
            dbConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes the connection to the database.
     *
     * @throws SQLException if the connection to the database fails.
     */
    public void dbConnect() throws SQLException {
        try {
            Properties info = new Properties();
            info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
            info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
            info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(DB_URL);
            System.out.println(DB_URL);
            System.out.println(DB_PASSWORD);
            System.out.println(DB_USER);
            ods.setConnectionProperties(info);
            System.out.println(info);
            OracleConnection connection = (OracleConnection) ods.getConnection();
            assignValue(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a SQL statement to validate user credentials.
     *
     * @param connection the database connection to execute the SQL statement against.
     * @throws SQLException if an error occurs while executing the SQL statement.
     */
    public static void assignValue(Connection connection) throws SQLException {
        boolean valid = false;
        String sql = "SELECT * FROM WKSP_TEST.user_account WHERE email = ? AND password = ?";

        String password = UI.passwordField.getText(); // gets hashed password to enter into statement
        PasswordHasher passwordHasher = new PasswordHasher(password);
        Thread passwordHasherThread = new Thread(passwordHasher);

        passwordHasherThread.start();

        try {
            passwordHasherThread.join();
        } catch (InterruptedException e) {
            passwordHasherThread.interrupt();
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String hashedPassword = passwordHasher.getHashedPassword();
            stmt.setString(1, UI.emailField.getText());
            stmt.setString(2, hashedPassword);

            // Execute the query and get the result set
            ResultSet rs = stmt.executeQuery();

            // Check if any rows were returned by the query
            if (rs.next()) {
                // Login successful
                JOptionPane.showMessageDialog(null, "log in successful.");
                loggedInUser = UI.emailField.getText();
                valid = true;
            } else {
                // Login unsuccessful
                JOptionPane.showMessageDialog(null, "Incorrect username or password.");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage());
        }

// If the login was not successful, reset the logged-in user
        if (!valid) {
            loggedInUser = null;
        }
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;

    }
}