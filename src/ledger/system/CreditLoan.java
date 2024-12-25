package ledger.system;

import database.LoansTable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreditLoan{
    private static double principal;
    private static int month;
    private static double interest;
    private static int period;
    private static double payment;
    private static String status;
    private static double total_loan;
    private static Timestamp createdAt;
    
    public static void creditLoan(int userId){
        Scanner sc = new Scanner(System.in);

        // menu
        System.out.println("== Credit Loan ==");
        System.out.println("1.Apply \n2.Repay");
        
        System.out.print("Select your option: ");
        int option = sc.nextInt();

        if(option == 1){
            applyLoan(userId, principal, month, interest, period);
            
        }else if(option == 2){
            repayLoan(userId, payment);
        }else{
            System.out.println("Invalid choice! Please enter the choice from 1 and 2 only.");
        }
    }

    public static void applyLoan(int userId, double principal, int month, double interest, int period){
        String isLoan = LoansTable.getStatus(userId);
        
        if(isLoan.equals("false")){
            status = "Unpaid";

            // monthly installment
            double month_installment = calculateMonthlyInstallment(principal, month, interest);                
            
            // schedule?
            System.out.printf("You are advised to pay RM%.2f each month\n", month_installment);
            
            // total amount have to pay
            double total_loan = calculateLoan(month_installment, month);

            System.out.printf("You will have to pay RM%.2f in total\n", total_loan);

            //insert sql
            LoansTable.insertLoan(userId, principal, interest, period, total_loan, status);

            // System.out.print("Enter a period (in month) for loan balance: ");

            // Periodic loan
            double period_loan = calculateLoan(month_installment, period);

            System.out.printf("The periodic payment is: %.2f\n",period_loan);

            // Calculate due date
            Timestamp dueDate = getDueDate(userId);

            // Format dueDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDueDate = dueDate.toLocalDateTime().format(formatter);

            System.out.println("Due Date: " + formattedDueDate);

        }else if(isLoan.equals(null)){
            System.out.println("There is an error fetching status!");
        }
        else{
            // Notification box liyik part thank you paiseh
            System.out.println("Please pay the current total loan first!");
        }
    }

    public static void repayLoan(int userId, double payment){
        
        // System.out.print("Please enter the amount of payment: ");
        total_loan = LoansTable.getLoan(userId);

        if (payment > total_loan){
            System.out.println("Payment amount must be equal or lower than the total loan!");
        }else if(payment <0){
            System.out.println("Payment amount must be positive");
        }else if(total_loan == 0){
            total_loan -= payment;
            status = "Paid";
            System.out.println("The loan has fully paid!");
        }else{
            total_loan -= payment;
            System.out.println("Payment processed. Outstanding balance: " + total_loan);
        }
        // update sql
        LoansTable.updateLoan(userId, total_loan, status);

    }

    public static double calculateMonthlyInstallment(double principal, int month, double interest){
        interest /= (12 * 100);
        double result = (principal * interest * Math.pow(1.0 + interest, month)) / (Math.pow(1.0 + interest, month) - 1.0);

        return result;
    }

    // Calculate total loan and periodic loan function
    public static double calculateLoan(double month_installment, int month){
        return (month_installment) * month;
    }

    public static LocalDateTime calculateDueDate(int userId){
        
        try {
            createdAt = LoansTable.getCreateAt(userId);
            period = LoansTable.getPeriod(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Creation date or period is null for userId: " + userId);
        }
        
        return createdAt.toLocalDateTime().plusMonths(period);
    }

    public static Timestamp getDueDate(int userId){
        try {
            LocalDateTime dueDate = calculateDueDate(userId);
            return Timestamp.valueOf(dueDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Error calculating due date: " + e.getMessage());
            return null; 
        }
    }

    public static boolean isOverdue(int userId){
        
        try {
            LocalDateTime dueDate = calculateDueDate(userId);

            // Check if the current date is after the due date && the loan is not fully paid
            if (LocalDateTime.now().isAfter(dueDate) && !status.equals("paid") && total_loan > 0) {
                return true;
            } else { // createdAt == null
                System.out.println("No loan found for the user.");
                return false;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error fetching due date: " + e.getMessage());
            return false; 
        }
    }

}
