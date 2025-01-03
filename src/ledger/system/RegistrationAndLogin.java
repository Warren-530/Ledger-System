package ledger.system;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationAndLogin {

    private static String name;
    private static String email;
    private static String password;

    public static void registration() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        name = sc.nextLine();
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        System.out.println("");//new line
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isEmailValid(String email) {
        // Validating email in the form of name@example.com
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+com";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Return whether the email matches the pattern
        return matcher.matches();
    }

    //Check whether password is valid or not (Minimum 6 characters with alphabets and digits)
    public static boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            return false;
        }

        boolean hasLetter = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isLetter(ch)) {
                hasLetter = true;
            }

            if (Character.isDigit(ch)) {
                hasDigit = true;
            }

            // If both conditions are met, we can return true early
            if (hasLetter && hasDigit) {
                return true;
            }
        }

        return hasLetter && hasDigit;
    }

    public static boolean isNameValid(String name) {
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            // Check if the character is a letter, a digit, or a space
            if (!(Character.isLetterOrDigit(ch) || ch == ' ')) {
                return false; // Return false if any character is not a letter, digit, or space
            }
        }
        if (name==null ||name.equals("")){
            return false;
        }
        return true; // Return true if all characters are valid
    }


    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

}
