import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Hand {
    private int CARDS_IN_HAND = 5;

    private ArrayList<Card> hand = new ArrayList<Card>();
    private Random random = new Random();

    public Hand (Deck deck) {

        while (hand.size() != 5) {
            int index = random.nextInt(deck.getDeck().size());

            if (validStartingCard(deck.getDeck().get(index))) {
                hand.add(deck.getDeck().get(index));
                deck.getDeck().remove(index);
            }
        }

        deck.shuffle();
    }

    public ArrayList getHand() {
        return hand;
    }

    public String toString() {
        return hand.toString();
    }

    private boolean validStartingCard(Card card) {
        return (card.getSymbol() == Card.Symbol.SUN ||
                card.getSymbol() == Card.Symbol.MOON || 
                card.getSymbol() == Card.Symbol.KEY);
    }
}
