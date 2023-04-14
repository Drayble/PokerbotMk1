package PokerBotMk1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Card> currentDeck = new ArrayList<>();
    static ArrayList<Card> currentHand = new ArrayList<>();
    static ArrayList<Card> currentRiver = new ArrayList<>();

    public static void main(String[] args) {
        for (int x = 0; x < 4; x++) {
            String suit = "Spades";
            if (x == 1) {
                suit = "Hearts";
            }
            if (x == 2) {
                suit = "Clubs";
            }
            if (x == 3) {
                suit = "Diamonds";
            }
            for (int i = 1; i <= 13; i++) {
                currentDeck.add(new Card(i, suit));
            }
        }
        System.out.println("Please enter the 2 cards in your hand in this format: card # (space) suit.\nExample: \"3 spades\"");
        runCalculationsStartingHand(2);
        System.exit(52);
        System.out.println("Enter the first 3 cards of the flop.");
        runCalculations(3);
        System.out.println("Enter the 4th card.");
        runCalculations(1);
        System.out.println("Enter the last card.");
        runCalculations(1);
    }

    public static String printCardList(ArrayList<Card> inputList) {
        String output = "(";
        for (int i = 0; i < inputList.size() - 1; i++) {
            output += inputList.get(i) + ", ";
        }
        output += inputList.get(inputList.size() - 1) + ")";
        return output;
    }

    public static void runCalculationsStartingHand(int newEntries) {
        for (int i = 1; i <= newEntries; i++) {
            Card cardTransferred = transferCards(0);
            System.out.println("Received card #" + i + " (" + cardTransferred + ")");
        }
        System.out.println("The starting hand of " + printCardList(currentHand) + ", gives a Chen value of " + calculateStartingHandByChenFormula());
    }

    public static double calculateStartingHandByChenFormula() {
        double totalValue = 0.0;
        if (currentHand.get(0).getNumericValue() == currentHand.get(1).getNumericValue()) {
            totalValue = (currentHand.get(0).getChenValue() * 2);
            if (totalValue < 5) {
                totalValue = 5;
            }
        }
        else {
            totalValue += getHighestValCard(currentHand).getChenValue();
            switch (getCardDifference(currentHand)) {
                case 2 -> totalValue -= 1; //1 gap
                case 3 -> totalValue -= 2; //2 gap
                case 4 -> totalValue -= 4; //3 gap
                case 5, 6, 7, 8, 9, 10, 11, 12 -> totalValue -= 5; //5 or more gap
            }
            if ((getHighestValCard(currentHand).getNumericValue() < 12) && (getCardDifference(currentHand) <= 2)) {
                totalValue += 1;
            }
            if (currentHand.get(0).getSuit().equals(currentHand.get(1).getSuit())) {
                totalValue += 2;
            }
        }
        return totalValue;
    }

    public static int getCardDifference(ArrayList<Card> inputList) {
        Card cardOne = inputList.get(0);
        Card cardTwo = inputList.get(1);
        return Math.abs(cardOne.getNumericValue() - cardTwo.getNumericValue());
    }

    public static Card getHighestValCard(ArrayList<Card> inputList) {
        Card cardOne = inputList.get(0);
        Card cardTwo = inputList.get(1);
        if (cardOne.getChenValue() > cardTwo.getChenValue()) {
            return cardOne;
        }
        else {
            return cardTwo;
        }
    }

    public static void runCalculations(int newEntries) {
        for (int i = 1; i <= newEntries; i++) {
            Card cardTransferred = transferCards(1);
            System.out.println("Received card #" + i + " (" + cardTransferred + ")");
        }
    }


    /* TODO:
          destination = 0 --> player's hand
          destination = 1 --> river
     */
    private static Card transferCards(int destination) {
        Scanner scan = new Scanner(System.in);
        String cardLet = "dis is a card";
        int cardNum = 0;
        try {
            cardNum = scan.nextInt();
        } catch (InputMismatchException e) {
            cardLet = scan.next();
            cardLet = cardLet.toLowerCase();
        }
        while ((cardNum < 2 || cardNum > 10) && ((!(cardLet.equals("a"))) && (!(cardLet.equals("k"))) && (!(cardLet.equals("q"))) && (!(cardLet.equals("j")))))    {
            System.out.println("Please enter a valid card number 2-10 or the letter of a face card or ace.");
            try {
                cardNum = scan.nextInt();
            } catch (InputMismatchException e) {
                cardLet = scan.next();
                cardLet = cardLet.toLowerCase();
            }
        }
        int newCardNumVal = 0;
        if (cardNum >= 2 && cardNum <= 10) {
            newCardNumVal = cardNum;
        }
        else if ((cardLet.equals("a")) || (cardLet.equals("k")) || (cardLet.equals("q")) || (cardLet.equals("j"))) {
            if (cardLet.equals("a")) {
                newCardNumVal = 1;
            }
            if (cardLet.equals("k")) {
                newCardNumVal = 13;
            }
            if (cardLet.equals("q")) {
                newCardNumVal = 12;
            }
            if (cardLet.equals("j")) {
                newCardNumVal = 11;
            }
        }
        else {
            System.out.println("Fuck this shit and go to bed.\n\n\n\npussy");
            System.exit(69);
        }
        String suitInput = "blank";
        suitInput = scan.next();
        suitInput = suitInput.toLowerCase();
        while ((!(suitInput.equals("spades"))) && (!(suitInput.equals("hearts"))) && (!(suitInput.equals("diamonds"))) && (!(suitInput.equals("clubs")))) {
            System.out.println("Please enter the suit of the card, either Spades, Clubs, Hearts, or Diamonds.");
            suitInput = scan.next();
            suitInput = suitInput.toLowerCase();
        }
        String newCardSuit = ((suitInput.substring(0, 1)).toUpperCase()) + suitInput.substring(1);
        Card cardToRemove = new Card(newCardNumVal, newCardSuit);
        if (destination == 0) {
            currentHand.add(cardToRemove);
        }
        if (destination == 1) {
            currentRiver.add(cardToRemove);
        }
        boolean wasRemoved = false;
        for (int i = 0; i < currentDeck.size(); i++) {
            if (currentDeck.get(i).getSuit().equals(cardToRemove.getSuit()) && currentDeck.get(i).getNumericValue() == cardToRemove.getNumericValue()) {
                currentDeck.remove(i);
                wasRemoved = true;
            }
        }
        if (!wasRemoved) {
            while (!wasRemoved) {
                System.out.println("The card you just entered has already been factored into this calculator run.\n\nGreat. We gotta do this all over again. This time, don't fuck it up.");
                cardLet = "dis is a card";
                cardNum = 0;
                try {
                    cardNum = scan.nextInt();
                } catch (InputMismatchException e) {
                    cardLet = scan.next();
                    cardLet = cardLet.toLowerCase();
                }
                while ((cardNum < 2 || cardNum > 10) && ((!(cardLet.equals("a"))) && (!(cardLet.equals("k"))) && (!(cardLet.equals("q"))) && (!(cardLet.equals("j")))))    {
                    System.out.println("Please enter a valid card number 2-10 or the letter of a face card or ace.");
                    try {
                        cardNum = scan.nextInt();
                    } catch (InputMismatchException e) {
                        cardLet = scan.next();
                        cardLet = cardLet.toLowerCase();
                    }
                }
                newCardNumVal = 0;
                if (cardNum >= 2 && cardNum <= 10) {
                    newCardNumVal = cardNum;
                }
                else if ((cardLet.equals("a")) || (cardLet.equals("k")) || (cardLet.equals("q")) || (cardLet.equals("j"))) {
                    if (cardLet.equals("a")) {
                        newCardNumVal = 1;
                    }
                    if (cardLet.equals("k")) {
                        newCardNumVal = 13;
                    }
                    if (cardLet.equals("q")) {
                        newCardNumVal = 12;
                    }
                    if (cardLet.equals("j")) {
                        newCardNumVal = 11;
                    }
                }
                else {
                    System.out.println("Fuck this shit and go to bed.\n\n\n\npussy");
                    System.exit(69);
                }
                suitInput = "blank";
                suitInput = scan.next();
                suitInput = suitInput.toLowerCase();
                while ((!(suitInput.equals("spades"))) && (!(suitInput.equals("hearts"))) && (!(suitInput.equals("diamonds"))) && (!(suitInput.equals("clubs")))) {
                    System.out.println("Please enter the suit of the card, either Spades, Clubs, Hearts, or Diamonds.");
                    suitInput = scan.next();
                    suitInput = suitInput.toLowerCase();
                }
                newCardSuit = ((suitInput.substring(0, 1)).toUpperCase()) + suitInput.substring(1);
                cardToRemove = new Card(newCardNumVal, newCardSuit);
                if (destination == 0) {
                    currentHand.add(cardToRemove);
                }
                if (destination == 1) {
                    currentRiver.add(cardToRemove);
                }
                wasRemoved = false;
                for (int i = 0; i < currentDeck.size(); i++) {
                    if (currentDeck.get(i).getSuit().equals(cardToRemove.getSuit()) && currentDeck.get(i).getNumericValue() == cardToRemove.getNumericValue()) {
                        currentDeck.remove(i);
                        wasRemoved = true;
                    }
                }
            }
        }
        return cardToRemove;
    }
}
