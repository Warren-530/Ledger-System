package ledger.system;

import java.util.Scanner;

public class DepositInterestPredictor {
    
    public static void calculateInterest(double balance){

        Scanner sc = new Scanner(System.in);

        double predictor = 0.0;
        String[] bank = {"RHB","Maybank","Hong Leong", "Alliance", "AmBank", "Standard Chartered"};
        double[] rate = {2.6, 2.5, 2.3, 2.85, 2.55, 2.65};

        System.out.println("== Deposit Interest Predictor==");
        System.out.printf("%11s%-13s%-21s\n"," ", "Bank","Interest Rate(%)");
        for (int i = 0; i < bank.length; i++) {
            System.out.printf("%-1d%2s%-28s%-16.2f\n", i+1, ". ", bank[i], rate[i]);
        }

        System.out.print("\nSelect your bank from 1 to 6: ");
        int choice = sc.nextInt();

        if (choice >= 1 && choice <= 6){
            predictor = balance * rate[choice-1];

            System.out.printf("\nYour daily interest is: %.2f\n", predictor/(12*30));
            System.out.printf("Your monthly interest is: %.2f\n", predictor/12);
            System.out.printf("Your anually interest is: %.2f\n", predictor);
        }else{
            System.out.println("Invalid input! Please enter the number from 1 to 6 only.");
        }
    }
}
