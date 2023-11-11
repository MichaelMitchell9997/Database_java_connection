
/**
 * This class provides methods for connecting to a database and inserting data into a table.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;

public class DatabaseConnectionCreateAccount {
    /**
     * A String variable that holds the database URL.
     */
    private final String DB_URL = "jdbc:oracle:thin:@gsv12wvlntrtx4m7_medium?TNS_ADMIN=C:/wall";

    /**
     * A String variable that holds the database username.
     */
    private String DB_USER;

    String loggedInUser;

    /**
     * A String variable that holds the database password.
     */
    private String DB_PASSWORD;

    /**
     * Constructs a DatabaseConnectionCreateAccount object with default database username and password.
     */
    public DatabaseConnectionCreateAccount() {
        this.DB_USER = "ADMIN";
        this.DB_PASSWORD = "Gradedunit123";
    }


    /**
     * Calls the dbConnect() method to connect to the database.
     */
    public void connectToDB() {
        try {
            dbConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the database and assigns values to the properties object.
     *
     * @throws SQLException if an error occurs while connecting to the database
     */
    private void dbConnect() throws SQLException {
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
     * Inserts data into the USER_ACCOUNT table in the database and start new thread to hash the password.
     *
     * @param connection the connection object to use for the database query
     * @throws SQLException if an error occurs while inserting data into the database
     */
    private static void assignValue(Connection connection) throws SQLException {
        String sql = "INSERT INTO WKSP_TEST.USER_ACCOUNT (ACCOUNTID,password, email, PHONENUMBER, FIRST_NAME, SECOND_NAME, DOB) VALUES (WKSP_TEST.seq_client_id.nextval,?, ?, ?, ?, ?, ?)";
        String password = UI.createPasswordField.getText();
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
            stmt.setString(1, hashedPassword);
            stmt.setString(2, UI.createEmailField.getText());
            stmt.setString(3, UI.createPhoneNumberField.getText());
            stmt.setString(4, UI.createFirstNameField.getText());
            stmt.setString(5, UI.createSecondNameField.getText());
            stmt.setString(6, UI.createDOBField.getText());
            stmt.executeUpdate();
            System.out.println("Data has been inserted into the database.");
        } catch (SQLException e) {
            System.err.println("Error inserting data into the database: " + e.getMessage());
        }
    }
}