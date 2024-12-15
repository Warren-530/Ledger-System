package ledger.system;

import database.SavingsTable;
import java.util.Scanner;

public class Savings {

    private static String status;
    private static int percentage;

        public static void saving(int userId) {
            Scanner sc = new Scanner(System.in);

            System.out.println("== Savings ==");
            System.out.print("Are you sure you want to activate it? (Y/N) : ");
            char choice = sc.next().charAt(0);
            
            if (choice == 'Y'){
                System.out.print("Please enter the percentage you wish to deduct from the next debit: ");
                percentage = sc.nextInt();
                System.out.println("\n"); //new line
        
                
                if(percentage >= 0 && percentage <= 100){
                    SavingsTable.updateSavings(userId, "Active", percentage);
                    System.out.println("Savings Settings added successfully");
                }
                else{
                    System.out.println("Savings Settings unable to save!");
                    System.out.println("Please enter percentage in between 0 and 100."); 
                }
            }
            
        }
    }
