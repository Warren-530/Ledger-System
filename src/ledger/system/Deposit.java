package ledger.system;

import java.util.Scanner;

public class DepositInterestPredictor {

    public static double calculateInterest(double balance, int index, int period) {
        Scanner sc = new Scanner(System.in);
        double interestDay;
        double interestMonth;
        double interestAnnual;
        String[] bank = {"RHB", "Maybank", "Hong Leong", "Alliance", "AmBank", "Standard Chartered"};
        double[] rate = {2.6, 2.5, 2.3, 2.85, 2.55, 2.65};
        double interestRate = rate[index];

        //1:Daily, 2:Monthly, 3:Annually
        switch (period) {
            case 0 -> {
                interestDay = (balance * interestRate) / (365);
                return interestDay;
            }
            case 1 -> {
                interestMonth = (balance * interestRate) / 12;
                return interestMonth;
            }
            case 2 -> {
                return interestAnnual = balance * interestRate;
            }
        }

        return 0;

    }
}