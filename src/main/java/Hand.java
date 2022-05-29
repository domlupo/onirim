import java.util.ArrayList;
import java.util.Random;

public class Hand {
    public static int MAX_CARDS_IN_HAND = 5;

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

    public void drawNewHand(Deck deck) throws OnirimException {
        if (deck.getDeck().size() < MAX_CARDS_IN_HAND) {
            throw new OnirimException("Could not draw " + MAX_CARDS_IN_HAND +
                    " cards because deck is too small.");
        }

       deck.getDeck().forEach(card -> {
                   if (validHandCard(card) && hand.size() < MAX_CARDS_IN_HAND) {
                       hand.add(card);
                   }
               }
       );

       if (hand.size() != MAX_CARDS_IN_HAND) {
           removeAllCards();
           throw new OnirimException("Could not draw " + MAX_CARDS_IN_HAND +
                   " cards because deck does not have enough valid starting cards.");
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

    public void addCard(Card card) throws OnirimException {
        if (hand.size() == Hand.MAX_CARDS_IN_HAND) {
            throw new OnirimException("Could not add card with color " + card.getColor()+ " and symbol "
                    + card.getSymbol() + " to hand because there is already "
                    + Hand.MAX_CARDS_IN_HAND + " cards in hand.");
        }

        hand.add(card);
    }

    public Card removeCard(Card.Color color, Card.Symbol symbol) throws OnirimException {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getColor().equals(color)
                && hand.get(i).getSymbol().equals(symbol)) {

                return hand.remove(i);
            }
        }

        throw new OnirimException("Could not remove card with color " + color + " and symbol "
                + symbol + " because that card is not in hand.");
    }

    public boolean hasCard(Card.Color color, Card.Symbol symbol) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getColor().equals(color)
                    && hand.get(i).getSymbol().equals(symbol)) {

                return true;
            }
        }

        return false;
    }

    private boolean validHandCard(Card card) {
        return (card.getSymbol() == Card.Symbol.SUN ||
                card.getSymbol() == Card.Symbol.MOON || 
                card.getSymbol() == Card.Symbol.KEY);
    }

    private void removeAllCards() {
        hand = new ArrayList<>();
    }

}
