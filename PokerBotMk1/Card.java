package PokerBotMk1;

public class Card {
    private String suit;
    private int numericValue;
    private double chenVal;

    //NOTICE: THIS STRING IS FOR PRINTING ONLY
    private String cardNumLetValue;

    public Card(int num, String suit) {
        this.suit = suit;
        numericValue = num;
        if (num >= 2 && num <= 10) {
            cardNumLetValue = "" + num + "";
            chenVal = num / 2.0;
        }
        else if (num == 1) {
            cardNumLetValue = "Ace";
            chenVal = 10;
            numericValue = 14;
        }
        else if (num == 11) {
            cardNumLetValue = "Jack";
            chenVal = 6;
        }
        else if (num == 12) {
            cardNumLetValue = "Queen";
            chenVal = 7;
        }
        else if (num == 13) {
            cardNumLetValue = "King";
            chenVal = 8;
        }
        else {
            System.out.println("Error in Card Creation. (Card class line 27)");
            System.exit(52);
        }
    }

    public String getCardNumLetValue() {
        return cardNumLetValue;
    }

    public int getNumericValue() {
        return numericValue;
    }

    public double getChenValue() {
        return chenVal;
    }

    public String getSuit() {
        return suit;
    }

    public String toString() {
        return "" + cardNumLetValue + " of " + suit + "";
    }
}
