import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
    static final int BROWN_SUNS = 6;

    private ArrayList<Card> deck = new ArrayList<>();
    private Map<Card.Color, Integer> colorToSuns = new HashMap<Card.Color, Integer>() {{
        put(Card.Color.RED, RED_SUNS);
        put(Card.Color.BLUE, BLUE_SUNS);
        put(Card.Color.GREEN, GREEN_SUNS);
        put(Card.Color.BROWN, BROWN_SUNS);
    }};

    public Deck() {
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

    public Deck(ArrayList<Card> cards) {
        deck = new ArrayList<>(cards);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public String toString() {
        return deck.toString();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Optional<Card> drawCard() {
        if (deck.size() > 0) {
            return Optional.of(deck.remove(0));
       }

       return Optional.empty();
    }
}
