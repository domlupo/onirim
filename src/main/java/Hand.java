import java.util.ArrayList;
import java.util.Random;

public class Hand {
    public static int STARTING_CARDS_IN_HAND = 5;

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

    public void drawStartingHand(Deck deck) throws OnirimException {
        if (deck.getDeck().size() < STARTING_CARDS_IN_HAND) {
            throw new OnirimException("Could not draw " + STARTING_CARDS_IN_HAND +
                    " starting cards because deck is too small.");
        }

       deck.getDeck().forEach(card -> {
                   if (validStartingCard(card) && hand.size() < STARTING_CARDS_IN_HAND) {
                       hand.add(card);
                   }
               }
       );

       if (hand.size() != STARTING_CARDS_IN_HAND) {
           removeAllCards();
           throw new OnirimException("Could not draw " + STARTING_CARDS_IN_HAND +
                   " starting cards because deck does not have enough valid starting cards.");
       }

       hand.forEach(card -> {
           deck.getDeck().remove(card);
       });

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

    public void removeCard(Card.Color color, Card.Symbol symbol) throws OnirimException {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getColor().equals(color)
                && hand.get(i).getSymbol().equals(symbol)) {

                hand.remove(i);
                return;
            }
        }

        throw new OnirimException("Could not remove card with color " + color + " and symbol "
                + symbol + " because that card is not in hand.");
    }

    private boolean validStartingCard(Card card) {
        return (card.getSymbol() == Card.Symbol.SUN ||
                card.getSymbol() == Card.Symbol.MOON || 
                card.getSymbol() == Card.Symbol.KEY);
    }

    private void removeAllCards() {
        hand = new ArrayList<>();
    }

}
