package ledger.system;

import database.AccountBalance;
import database.TransactionsTable;
import java.time.LocalDate;
import java.util.Scanner;

public class Transactions {

    private static double debitAmount;
    private static double creditAmount;
    private static String description;

    public static void debit(int userId) {
        Scanner sc = new Scanner(System.in);
        
        boolean overdue = CreditLoan.isOverdue(userId);

        if(!overdue){
            LocalDate date = LocalDate.now();
            System.out.println("== Debit ==");
            System.out.print("Enter amount: ");
            debitAmount = sc.nextDouble();
            sc.nextLine();//consume new line
            System.out.print("Enter description: ");
            description = sc.nextLine();
            System.out.println("");//new line

            //If the debit amount is invalid
            if (!(debitAmount >= 0 && debitAmount <= Double.MAX_VALUE)) {
                System.out.println("Debit Record Unsuccess!!!");
                System.out.println("Amount must be positive!");
            } else {
                AccountBalance.debitBalance(userId, debitAmount);//update the balance of user in account_balance table
                TransactionsTable.insertTransaction(userId, "Debit", debitAmount, description, date);//insert a new debit record into transactions table
                System.out.println("Debit Successfully Recorded!!!");
            }
        } else {
            System.out.println("You are not allowed to make any debit or credit transaction as your loan is not paid within the period!");
        }
    }

    public static void credit(int userId) {
        Scanner sc = new Scanner(System.in);

        boolean overdue = CreditLoan.isOverdue(userId);

    if(!overdue){
            LocalDate date = LocalDate.now();
            System.out.println("== Credit ==");
            System.out.print("Enter amount: ");
            creditAmount = sc.nextDouble();
            sc.nextLine();//consume new line
            System.out.print("Enter description: ");
            description = sc.nextLine();
            System.out.println("");//new line

            //If the credit amount is invalid
            if (!(creditAmount >= 0 && creditAmount <= Double.MAX_VALUE)) {
                System.out.println("Credit Record Unsuccess!!!");
                System.out.println("Amount must be positive!");
            } else {
                AccountBalance.creditBalance(userId, creditAmount);//update the balance of user in account_balance table
                TransactionsTable.insertTransaction(userId, "Credit", creditAmount, description, date);//insert a new debit record into transactions table
                System.out.println("Credit Successfully Recorded!!!");
            }
    } else {
        System.out.println("You are not allowed to make any debit or credit transaction as your loan is not paid within the period!");
    }
    }
}
