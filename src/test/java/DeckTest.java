import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    // TODO make nested and add deck tests for correct number of starting cards types
    @Test
    void initialDeckSize() {
        Deck deck = new Deck();
        deck.initialize();
        assertTrue(deck.getDeck().size() == Deck.LABYRINTH_CARDS + Deck.NIGHTMARE_CARDS + Deck.DOOR_CARDS);
    }

    @Test
    void shuffle() {
        // TODO test for deep equals
    }

    @Nested
    class drawCard {
        @Test
        void drawCard() throws OnirimException {
            Card card = new Card.Builder()
                    .setColor(Card.Color.RED)
                    .setSymbol(Card.Symbol.SUN)
                    .build();
            ArrayList<Card> cards = new ArrayList<Card>();
            cards.add(card);
            Deck deck = new Deck(cards);

            assertTrue(deck.getDeck().size() == 1);
            assertTrue(deck.getDeck().contains(card));
            Card drawnCard= deck.drawCard();
            assertTrue(deck.getDeck().size() == 0);
            assertTrue(drawnCard == card);
        }

        @Test
        void drawCardException() {
            Deck deck = new Deck();

            assertTrue(deck.getDeck().size() == 0);
            OnirimException thrown = assertThrows(
                    OnirimException.class,
                    () -> deck.drawCard(),
                    "Expected addCard(card) to throw, but it didn't"
            );

            assertTrue(thrown.getMessage().contains("Could not draw card because deck has no cards."));
        }

    }
}