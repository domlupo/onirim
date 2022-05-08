import java.util.ArrayList;
import java.util.Random;

public class Hand {
    private int CARDS_IN_HAND = 5;

    private ArrayList<Card> hand;
    private Random random = new Random();

    public Hand() {
        hand = new ArrayList<>();
    }

    public Hand(Card card) {
        hand = new ArrayList<>();
        hand.add(card);
    }

    public Hand(ArrayList<Card> cards) {
        hand = new ArrayList<>(cards);
    }

    public void drawStartingHand(Deck deck) {
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

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card.Color color, Card.Symbol symbol) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getColor().equals(color)
                && hand.get(i).getSymbol().equals(symbol)) {

                hand.remove(i);
                break;
            }
        }

        // TODO throw exception and add tests
    }

    public boolean hasCard(Card.Color color, Card.Symbol symbol) {
        return hand.stream().anyMatch(card -> 
                card.getColor().equals(color) && card.getSymbol().equals(symbol));
    }

    private boolean validStartingCard(Card card) {
        return (card.getSymbol() == Card.Symbol.SUN ||
                card.getSymbol() == Card.Symbol.MOON || 
                card.getSymbol() == Card.Symbol.KEY);
    }
}
