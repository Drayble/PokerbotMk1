package PokerBotMk1;

public class Card {
    private String suit;
    private int numericValue;

    //FIXME: THIS STRING IS FOR PRINTING ONLY
    private String cardNumLetValue;

    public Card(int num, String suit) {
        this.suit = suit;
        numericValue = num;
        if (num >= 2 && num <= 10) {
            cardNumLetValue = "" + num + "";
        }
        else if (num == 1) {
            cardNumLetValue = "Ace";
        }
        else if (num == 11) {
            cardNumLetValue = "Jack";
        }
        else if (num == 12) {
            cardNumLetValue = "Queen";
        }
        else if (num == 13) {
            cardNumLetValue = "King";
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

    public String getSuit() {
        return suit;
    }

    public String toString() {
        return "" + cardNumLetValue + " of " + suit + "";
    }
}
