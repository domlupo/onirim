import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {

    @Nested
    class playCard {
        @Test
        void playCardEmptyLabyrinth() throws OnirimException {
            Card playCard = new Card.Builder()
                    .setColor(Card.Color.RED)
                    .setSymbol(Card.Symbol.SUN)
                    .build();

            Labyrinth labyrinth = new Labyrinth();
            labyrinth.playCard(playCard);

            assertTrue(labyrinth.getLabyrinth().size() == 1);
            assertTrue(labyrinth.getLabyrinth().contains(playCard));
        }

        @Test
        void playCardWithDifferentSymbol() throws OnirimException {
            Card.Color color = Card.Color.RED;
            Card.Symbol playCardSymbol = Card.Symbol.SUN;
            Card.Symbol labyrinthCardSymbol = Card.Symbol.MOON;

            Card playCard = new Card.Builder()
                    .setColor(color)
                    .setSymbol(playCardSymbol)
                    .build();
            Card labyrinthCard = new Card.Builder()
                    .setColor(color)
                    .setSymbol(labyrinthCardSymbol)
                    .build();

            Labyrinth labyrinth = new Labyrinth(labyrinthCard);

            assertTrue(labyrinth.getLabyrinth().size() == 1);
            assertTrue(labyrinth.getLabyrinth().contains(labyrinthCard));
            labyrinth.playCard(playCard);
            assertTrue(labyrinth.getLabyrinth().size() == 2);
            assertTrue(labyrinth.getLabyrinth().contains(labyrinthCard));
            assertTrue(labyrinth.getLabyrinth().contains(playCard));
        }

        @Test
        void playCardWithMatchingSymbolException() {
            Card.Color color = Card.Color.RED;
            Card.Symbol matchingSymbol = Card.Symbol.SUN;

            Card playCard = new Card.Builder()
                    .setColor(color)
                    .setSymbol(matchingSymbol)
                    .build();
            Card labyrinthCard = new Card.Builder()
                    .setColor(color)
                    .setSymbol(matchingSymbol)
                    .build();

            Labyrinth labyrinth = new Labyrinth(labyrinthCard);

            assertTrue(labyrinth.getLabyrinth().size() == 1);
            assertTrue(labyrinth.getLabyrinth().contains(labyrinthCard));
            OnirimException thrown = assertThrows(
                    OnirimException.class,
                    () -> labyrinth.playCard(playCard),
                    "Expected playCard(playCard) to throw, but it didn't"
            );

            assertTrue(labyrinth.getLabyrinth().size() == 1);
            assertTrue(labyrinth.getLabyrinth().contains(labyrinthCard));
            assertTrue(thrown.getMessage().contains("Could not play card because the card symbol " + playCard.getSymbol() +
                    " is the last played symbol in the labyrinth."));
        }



        @ParameterizedTest
        @EnumSource(value = Card.Symbol.class, names = {"DOOR", "NIGHTMARE"})
        void playCardWithMatchingSymbolException(Card.Symbol symbol) {
            Card playCard = new Card.Builder()
                    .setSymbol(symbol)
                    .build();
            Labyrinth labyrinth = new Labyrinth();

            OnirimException thrown = assertThrows(
                    OnirimException.class,
                    () -> labyrinth.playCard(playCard),
                    "Expected playCard(playCard) to throw, but it didn't"
            );

            assertTrue(labyrinth.getLabyrinth().size() == 0);
            assertTrue(thrown.getMessage().contains("Could not play card because the card symbol is  " +
                    playCard.getSymbol() + " which can never be played in labyrinth."));
        }
    }

}