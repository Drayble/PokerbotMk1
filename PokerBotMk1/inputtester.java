package PokerBotMk1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class inputtester {
    public static void main(String[] args) throws InterruptedException {
        String inputString = "peanuts";
        int inputInt = 3;
        Scanner scan = new Scanner(System.in);
        try {
            inputInt = scan.nextInt();
            System.out.println("Int was recieved");
        } catch (InputMismatchException e) {
            inputString = scan.next();
            System.out.println("String was recieved");
        }
        System.out.println("Fuck this\n" + inputInt + " " + inputString);
        String banana = "BANANANA";
        System.out.println(banana);
        banana.toLowerCase();
        System.out.println("First try" + banana);
        banana = banana.toLowerCase();
        System.out.println("Second try" + banana);
    }
}
