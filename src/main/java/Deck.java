import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Deck {
    static final int NIGHTMARE_CARDS = 10;
    static final int LABYRINTH_CARDS = 58;
    static final int DOOR_CARDS = 8;

    static final int MOONS_PER_COLOR = 4;
    static final int KEYS_PER_COLOR = 3;
    static final int DOORS_PER_COLOR = 2;
    static final int RED_SUNS = 9;
    static final int BLUE_SUNS = 8;
    static final int GREEN_SUNS = 7;
    static final int TAN_SUNS = 6;

    private ArrayList<Card> deck;
    private Map<Card.Color, Integer> colorToSuns = new HashMap<Card.Color, Integer>() {{
        put(Card.Color.RED, RED_SUNS);
        put(Card.Color.BLUE, BLUE_SUNS);
        put(Card.Color.GREEN, GREEN_SUNS);
        put(Card.Color.TAN, TAN_SUNS);
    }};

    public Deck() {
        deck = new ArrayList<>();
    }

    public Deck(ArrayList<Card> cards) {
        deck = new ArrayList<>(cards);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public String toString() {
        return deck.toString();
    }

    public void initialize() {
        for (Card.Color color : Card.Color.values()) {
            IntStream.range(0, MOONS_PER_COLOR).forEach(i ->
                    deck.add(Card.Builder.newInstance()
                            .setColor(color)
                            .setSymbol(Card.Symbol.MOON)
                            .build()));

            IntStream.range(0, KEYS_PER_COLOR).forEach(i ->
                    deck.add(Card.Builder.newInstance()
                            .setColor(color)
                            .setSymbol(Card.Symbol.KEY)
                            .build()));

            IntStream.range(0, DOORS_PER_COLOR).forEach(i ->
                    deck.add(Card.Builder.newInstance()
                            .setColor(color)
                            .setSymbol(Card.Symbol.DOOR)
                            .build()));

            IntStream.range(0, colorToSuns.get(color)).forEach(i ->
                    deck.add(Card.Builder.newInstance()
                            .setColor(color)
                            .setSymbol(Card.Symbol.SUN)
                            .build()));
        }

        IntStream.range(0, NIGHTMARE_CARDS).forEach(i ->
                deck.add(Card.Builder.newInstance()
                        .setSymbol(Card.Symbol.NIGHTMARE)
                        .build()));

        Collections.shuffle(deck);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void shuffle(ArrayList<Card> cards) {
        deck.addAll(cards);
        Collections.shuffle(deck);
    }

    public Card drawCard() throws OnirimException {
        return deck.remove(0);
    }

    public void addCard(Card card) {
        deck.add(0, card);
    }

    public void removeCard(int index) throws OnirimException {
        deck.remove(index);
    }

    public Card removeCard(Card.Color color, Card.Symbol symbol) throws OnirimException {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getColor() != null
                    && deck.get(i).getColor().equals(color)
                    && deck.get(i).getSymbol().equals(symbol)) {

                return deck.remove(i);
            }
        }

        return null;
    }
}
