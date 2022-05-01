import java.util.ArrayList;

public class Deck {
    private final int NIGHTMARE_CARDS = 10;
    private final int LABRINTH_CARDS = 58;
    private final int RED_SUNS = 9;
    private final int BLUE_SUNS = 8;
    private final int GREEN_SUNS = 7;
    private final int BROWN_SUNS = 6;
    private final int MOONS_PER_COLOR = 4;
    private final int KEYS_PER_COLOR = 3;
    private final int DOORS_PER_COLOR = 2;

    private ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {
        Card cardOne = Card.Builder.newInstance()
            .setColor(Card.Color.RED)
            .setSymbol(Card.Symbol.KEY)
            .build();

        Card cardTwo = Card.Builder.newInstance()
            .setColor(Card.Color.RED)
            .setSymbol(Card.Symbol.MOON)
            .build();

        deck.add(cardOne);
        deck.add(cardTwo);
    }

    public ArrayList getDeck() {
        return deck;
    }

    public String toString() {
        return deck.toString();
    }
}
