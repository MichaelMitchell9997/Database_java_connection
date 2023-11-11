

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String NAME_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private static final String PHONE_REGEX = "^07[0-9]{9}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z].*[A-Z])(?=.*[a-z].*[a-z])(?=.*\\d.*\\d)(?=.*[@#$%^&*!£$]).{8,}$";


    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    private static boolean isValidPassword(String password, String confirmPassword) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return false;
        }
        if (!password.equals(confirmPassword)) {
            return false;
        }
        return true;
    }


    private static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    static void isValidDetails(JTextField createFirstNameField, JTextField createSecondNameField, JPasswordField createPasswordField, JPasswordField confirmPasswordField, JTextField createPhoneNumber, JTextField createEmailField, JTextField createDOBField) {
        boolean validFName = isValidName(createFirstNameField.getText());
        boolean validSName = isValidName(createSecondNameField.getText());
        boolean validPassword = isValidPassword(createPasswordField.getText(), confirmPasswordField.getText());
        boolean validPhone = isValidPhone(createPhoneNumber.getText());
        boolean validEmail = isValidEmail(createEmailField.getText());


        if (!validFName || !validSName) {
            JOptionPane.showMessageDialog(null, "Name not valid input");
        }
        if (!validSName) {
            JOptionPane.showMessageDialog(null, "Name not valid input");
        }
        if (!validEmail) {
            JOptionPane.showMessageDialog(null, "Email field incorrect");
        }
        if (!validPhone) {
            JOptionPane.showMessageDialog(null, "Phone number field incorrect");
        }
        if (!validPassword) {
            JOptionPane.showMessageDialog(null, "Password Not valid");
        }
        if ((validEmail) && (validSName) && (validFName) && (validPhone)) {
            DatabaseConnectionCreateAccount db = new DatabaseConnectionCreateAccount();
            db.connectToDB();

        }
    }


}
