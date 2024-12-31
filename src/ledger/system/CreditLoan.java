package ledger.system;

import database.LoansTable;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class CreditLoan{
    private static double principal;
    private static int month;
    private static double interest;
    private static int period;
    private static double payment;
    private static String status;
    private static double total_loan;
    private static Timestamp createdAt;
    
    public static double calculateMonthlyInstallment(double principal,double interest,int month){
        interest /= (12 * 100);
        double result = (principal * interest * Math.pow(1.0 + interest, month)) / (Math.pow(1.0 + interest, month) - 1.0);

        return Double.parseDouble(String.format("%.2f", result));
    }

    // Calculate total loan and periodic loan function
    public static double calculateLoan(double month_installment, int month){
        return Double.parseDouble(String.format("%.2f", (month_installment * month)));
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
        status=LoansTable.getStatus(userId);
        try {
            LocalDateTime dueDate = calculateDueDate(userId);

            // Check if the current date is after the due date && the loan is not fully paid
            System.out.println(LocalDateTime.now().isAfter(dueDate));
            if (LocalDateTime.now().isAfter(dueDate) && status.equals("Unpaid") && LoansTable.getLoan(userId) > 0) {
                return true;
            } else { // createdAt == null
                return false;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error fetching due date: " + e.getMessage());
            return false; 
        }
    }

}