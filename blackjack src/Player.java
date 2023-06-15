package project;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    public static Deck deck = new Deck();
    private ArrayList<Card> hand;
    private int wins;

    public Player() {
        hand = new ArrayList<>();
        wins = 0;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int valueOfHand() {
        int value = 0;
        int aces = 0;

        for (Card card : hand) {
            if (card.getFace().equals("A")) {
                aces++;
            } else {
                value += card.valueOf();
            }
        }

        for (int i = 0; i < aces; i++) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }

        return value;
    }

    public void clearHand() {
        hand.clear();
    }

    public boolean stand(int otherPlayerValue) {
        int handValue = valueOfHand();
        Random random = new Random();

        if (handValue > otherPlayerValue) {
            return true;
        } else if (handValue == otherPlayerValue) {
            return handValue >= 16 && random.nextBoolean();
        } else {
            return false;
        }
    }

    public boolean busted() {
        return valueOfHand() > 21;
    }

    public void hit() {
        hand.add(deck.dealCard());
    }

    public int win() {
        wins++;
        return wins;
    }
}