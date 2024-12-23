package ledger.system;

import database.LoansTable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
            applyLoan(userId, principal, month, interest, period, payment);
            
        }else if(option == 2){
            repayLoan(userId);
        }else{
            System.out.println("Invalid choice! Please enter the choice from 1 and 2 only.");
        }
    }

    public static void applyLoan(int userId, double principal, int month, double interest, int period, double payment){
        String isLoan = LoansTable.getStatus(userId);
        
        if(isLoan.equals("false")){
            status = "Unpaid";
            
            interest /= (12 * 100);

            // monthly installment
            double month_installment = (principal * interest * Math.pow(1.0 + interest, month)) / (Math.pow(1.0 + interest, month) - 1.0);                
            
            // schedule?
            System.out.printf("You are advised to pay RM%.2f each month\n", month_installment);
            
            // total amount have to pay
            double total_loan = (month_installment) * month;

            System.out.printf("You will have to pay RM%.2f in total\n", total_loan);

            
            //insert sql
            LoansTable.insertLoan(userId, principal, interest, period, total_loan, status);

            // System.out.print("Enter a period for loan balance: ");

            // Periodic loan
            double period_loan = period * month_installment;

            System.out.printf("The periodic payment is: %.2f\n",period_loan);

        }else if(isLoan.equals(null)){
            System.out.println("There is an error fetching status!");
        }
        else{
            // Notification box liyik part thank you paiseh
            System.out.println("Please pay the total loan first!");
        }
    }

    public static void repayLoan(int userId){
        
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

    public static boolean isOverdue(int userId){
        total_loan = LoansTable.getLoan(userId);
        createdAt = LoansTable.getCreateAt(userId);
        period = LoansTable.getPeriod(userId);

        LocalDateTime dueDate = createdAt.toLocalDateTime().plusMonths(period);

        // Check if the current date is after the due date && the loan is not fully paid
        if (LocalDateTime.now().isAfter(dueDate) && !status.equals("paid") && total_loan > 0) {
            return true;
        } else { // createdAt == null
            System.out.println("No loan found for the user.");
            return false;
        }
    }

}
