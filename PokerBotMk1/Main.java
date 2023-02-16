package PokerBotMk1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Card> currentDeck = new ArrayList<>();
    static ArrayList<Card> currentHand = new ArrayList<>();

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
        System.out.println("Enter the first 3 cards of the flop.");
        runCalculations(3);
        System.out.println("Enter the 4th card.");
        runCalculations(1);
        System.out.println("Enter the last card.");
        runCalculations(1);
    }

    public static void runCalculationsStartingHand(int newEntries) {
        for (int i = 1; i <= newEntries; i++) {
            Card cardTransferred = transferCards();
            System.out.println("Received card #" + i + " (" + cardTransferred + ")");
        }
        double cardOneValue = 0;
        double cardTwoValue = 0;
        switch (currentHand.get(0).getCardNumLetValue()) {
            case "Ace" -> cardOneValue = 10;
            case "King" -> cardOneValue = 8;
            case "Queen" -> cardOneValue = 7;
            case "Jack" -> cardOneValue = 6;
            case "10" -> cardOneValue = 5;
            case "9" -> cardOneValue = 4.5;
            case "8" -> cardOneValue = 4;
            case "7" -> cardOneValue = 3.5;
            case "6" -> cardOneValue = 3;
            case "5" -> cardOneValue = 2.5;
            case "4" -> cardOneValue = 2;
            case "3" -> cardOneValue = 1.5;
            case "2" -> cardOneValue = 1;
            default -> {
                System.out.println("I fucked up in switch #1. My bad. uhh check main class, either fucky wucky in transferCards or runCalculationsStartingHand.\nThat's all for now boss, cya.");
                System.exit(0);
            }
        }
        switch (currentHand.get(1).getCardNumLetValue()) {
            case "Ace" -> cardTwoValue = 10;
            case "King" -> cardTwoValue = 8;
            case "Queen" -> cardTwoValue = 7;
            case "Jack" -> cardTwoValue = 6;
            case "10" -> cardTwoValue = 5;
            case "9" -> cardTwoValue = 4.5;
            case "8" -> cardTwoValue = 4;
            case "7" -> cardTwoValue = 3.5;
            case "6" -> cardTwoValue = 3;
            case "5" -> cardTwoValue = 2.5;
            case "4" -> cardTwoValue = 2;
            case "3" -> cardTwoValue = 1.5;
            case "2" -> cardTwoValue = 1;
            default -> {
                System.out.println("I fucked up in switch #2. My bad. uhh check main class, either fucky wucky in transferCards or runCalculationsStartingHand.\nThat's all for now boss, cya.");
                System.exit(0);
            }
        }
        System.out.println(currentHand.get(0) + " value is equal to: " + cardOneValue);
        System.out.println(currentHand.get(1) + " value is equal to: " + cardTwoValue);
        
    }

    public static void runCalculations(int newEntries) {
        for (int i = 1; i <= newEntries; i++) {
            Card cardTransferred = transferCards();
            System.out.println("Received card #" + i + " (" + cardTransferred + ")");
        }
    }

    private static Card transferCards() {
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
        currentHand.add(cardToRemove);
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
                currentHand.add(cardToRemove);
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
