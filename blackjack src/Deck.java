package project;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;
    private Random random;

    public Deck() {
        cards = new ArrayList<>();
        random = new Random();
        reset();
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }

        int index = random.nextInt(cards.size());
        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }

    public void reset() {
        cards.clear();

        for (int i = 0; i < 4; i++) {
            for (String face : Card.FACES) {
                cards.add(new Card(face));
            }
        }
    }
}
