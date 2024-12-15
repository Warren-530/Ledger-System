package ledger.system;

import database.AccountBalance;
import database.DatabaseConnector;
import database.UserTable;
import java.sql.SQLException;
import java.util.Scanner;

public class LedgerSystem {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String name;
        String email;
        String password;
        String hashedPassword;
        int userId = 0;

        //Method calling to bulid connection
        DatabaseConnector dbcon = new DatabaseConnector();

        System.out.println("== Ledger System ==");

        //Registration and Login
        whileloop: //if user prefer login, break the whileloop 
        while (true) {//looping to continue registration until user inputs login
            System.out.println("Login or Register: ");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.print(">");

            int option = sc.nextInt();
            sc.nextLine();//consume new line
            System.out.println("");//new line

            switch (option) {
                case 1 -> {
                    System.out.println("== Please enter your email and password ==");
                    RegistrationAndLogin.Login();

                    email = RegistrationAndLogin.getEmail();
                    //check whether the email is registered or not
                    //if not registered, then unsuccessful
                    if (!UserTable.isRegisteredAccount(email)) {
                        System.out.println("Login Unsuccessful!!!");
                        System.out.println("Email hasn't be registered.\n");
                        continue;
                    }
                    password = RegistrationAndLogin.getPassword();
                    if (!UserTable.checkPassword(email, password)) {
                        System.out.println("Incorrect password!\n");
                        continue;
                    } else {
                        System.out.println("Login Successful!!!");
                        userId = UserTable.getUserId(email);
                        System.out.println("\n");//new line
                        break whileloop;
                    }
                }
                case 2 -> {
                    System.out.println("==Please fill in the form ==");
                    RegistrationAndLogin.registration();

                    name = RegistrationAndLogin.getName();
                    email = RegistrationAndLogin.getEmail();
                    password = RegistrationAndLogin.getPassword();
                    hashedPassword = RegistrationAndLogin.hashPassword(password);

                    //check email
                    if (!RegistrationAndLogin.isEmailValid(email)) {
                        System.out.println("Register Unsuccessful!!!");
                        System.out.println("The email entered is invalid\n");
                        continue;
                    }

                    //check password
                    if (!RegistrationAndLogin.isPasswordValid(password)) {
                        System.out.println("Register Unsuccessful!!!");
                        System.out.println("The password must exceed 5 characters with alphabets and digits.\n");
                        continue;
                    }

                    //check name
                    if (!RegistrationAndLogin.isNameValid(name)) {
                        System.out.println("Register Unsuccessful!!!");
                        System.out.println("The name cannot contain special character.\n");
                        continue;
                    }

                    /*Calling method to check whether the account exists or not
                If true -> Account exists
                If false -> Account doesn't exist
                     */
                    if (UserTable.isRegisteredAccount(email)) {
                        System.out.println("Register Unsuccessful!!!");
                        System.out.println("This email has been registered.\n");
                    } else {
                        UserTable.insertUser(name, email, hashedPassword);
                        System.out.println("Register Successful!!!\n");
                    }

                }
                default -> {
                    System.out.println("Invalid operation!\n");
                    continue;
                }
            }
            System.out.println("");//new line
        }

        double balance;
        double savings;
        double loan;
        int transactionOption;

        transactionLoop:
        while (true) {
            System.out.println("== Welcome, " + UserTable.getName(userId) + " ==");
            balance = AccountBalance.getBalance(userId);
            System.out.printf("Balance: %.2f%n" , balance);
            savings = AccountBalance.getSavings(userId);
            System.out.printf("Savings: %.2f%n" , savings);
            loan = AccountBalance.getLoan(userId);
            System.out.printf("Loan: %.2f%n" , loan);

            System.out.println("\n== Transaction ==");
            System.out.println("""
                               1.Debit
                               2.Credit
                               3.History
                               4.Savings
                               5.Credit Loan
                               6.Deposit Interest Predictor
                               7.Logout
                               """);
            System.out.print(">");
            transactionOption = sc.nextInt();

            System.out.println("\n");//new line

            switch (transactionOption) {
                case 1 -> {
                    Transactions.debit(userId);//calling debit method 
                }
                case 2 -> {
                    Transactions.credit(userId);//calling credit method
                }
                case 3 -> {
                    
                }
                case 4 -> {
                    Savings.saving(userId); // calling saving method
                }
                case 5 -> {
                    CreditLoan.creditLoan(balance); // calling creditLoan method
                }
                case 6 ->{
                    DepositInterestPredictor.calculateInterest(balance); // calling calculateInterest method
                }
                case 7 -> {
                    System.out.println("Thank you for using Ledger System");
                    break transactionLoop;
                }
            }
            
            System.out.println("\n");//new line

        }

    }

}
