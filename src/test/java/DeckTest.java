import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    // TODO make nested and add deck tests for correct number of starting cards types
    @Test
    void initialDeckSize() {
        Deck deck = new Deck();
        assertTrue(deck.getDeck().size() == Deck.LABYRINTH_CARDS + Deck.NIGHTMARE_CARDS + Deck.DOOR_CARDS);
    }

    @Test
    void shuffle() {
        Deck deckOne = new Deck();
        Deck deckTwo = new Deck(deckOne.getDeck());
        // TODO test for deep equals
        deckOne.shuffle();
        assertFalse(deckOne.equals(deckTwo));
    }
}