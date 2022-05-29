import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    @Nested
    class addCard {
        @Test
        void addOneCard() throws OnirimException {
            Hand hand = new Hand();
            Card cardOne = new Card.Builder()
                    .setColor(Card.Color.RED)
                    .setSymbol(Card.Symbol.SUN)
                    .build();

            assertTrue(hand.getHand().size() == 0);
            hand.addCard(cardOne);
            assertTrue(hand.getHand().size() == 1);
            assertTrue(hand.getHand().contains(cardOne));
        }

        @Test
        void addTwoCards() throws OnirimException {
            Hand hand = new Hand();
            Card cardOne = new Card.Builder()
                    .setColor(Card.Color.RED)
                    .setSymbol(Card.Symbol.SUN)
                    .build();
            Card cardTwo = new Card.Builder()
                    .setColor(Card.Color.BLUE)
                    .setSymbol(Card.Symbol.SUN)
                    .build();

            assertTrue(hand.getHand().size() == 0);
            hand.addCard(cardOne);
            hand.addCard(cardTwo);
            assertTrue(hand.getHand().size() == 2);
            assertTrue(hand.getHand().contains(cardOne));
            assertTrue(hand.getHand().contains(cardTwo));
        }

        @Test
        void addCardException() {
            Card.Color color = Card.Color.RED;
            Card.Symbol symbol = Card.Symbol.SUN;
            ArrayList<Card> maxCardsInHand = new ArrayList<>();

            Card.Builder cardBuilder = Card.Builder.newInstance()
                    .setColor(color)
                    .setSymbol(symbol);
            IntStream.range(0, Hand.MAX_CARDS_IN_HAND).forEach(i ->
                    maxCardsInHand.add(cardBuilder.build()));

            Hand hand = new Hand(maxCardsInHand);

            assertTrue(hand.getHand().size() == Hand.MAX_CARDS_IN_HAND);
            OnirimException thrown = assertThrows(
                    OnirimException.class,
                    () -> hand.addCard(cardBuilder.build()),
                    "Expected addCard(card) to throw, but it didn't"
            );

            assertTrue(thrown.getMessage().contains("Could not add card with color " + color + " and symbol "
                    + symbol + " to hand because there is already "
                    + Hand.MAX_CARDS_IN_HAND + " cards in hand."));
            assertTrue(hand.getHand().size() == Hand.MAX_CARDS_IN_HAND);
        }
    }

    @Nested
    class removeCard {

        @Test
        void removeOnlyCard() throws OnirimException {
            Card card = new Card.Builder()
                    .setColor(Card.Color.RED)
                    .setSymbol(Card.Symbol.SUN)
                    .build();

            Hand hand = new Hand(card);
            assertTrue(hand.getHand().size() == 1);
            hand.removeCard(card.getColor(), card.getSymbol());
            assertTrue(hand.getHand().size() == 0);
        }

        @Test
        void removeCardByCorrectColor() throws OnirimException {
            Card.Color cardOneColor = Card.Color.RED;
            Card.Color cardTwoColor = Card.Color.BLUE;
            Card.Symbol symbol = Card.Symbol.SUN;

            Card cardOne = new Card.Builder()
                    .setColor(cardOneColor)
                    .setSymbol(symbol)
                    .build();
            Card cardTwo = new Card.Builder()
                    .setColor(cardTwoColor)
                    .setSymbol(symbol)
                    .build();

            ArrayList<Card> cards = new ArrayList<>();
            cards.add(cardOne);
            cards.add(cardTwo);
            Hand hand = new Hand(cards);

            assertTrue(hand.getHand().size() == 2);
            hand.removeCard(cardOneColor, symbol);
            assertTrue(hand.getHand().size() == 1);
            assertTrue(hand.getHand().contains(cardTwo));
        }

        @Test
        void removeCardByCorrectSymbol() throws OnirimException {
            Card.Color color = Card.Color.RED;
            Card.Symbol cardOneSymbol = Card.Symbol.SUN;
            Card.Symbol cardTwoSymbol = Card.Symbol.MOON;

            Card cardOne = new Card.Builder()
                    .setColor(color)
                    .setSymbol(cardOneSymbol)
                    .build();
            Card cardTwo = new Card.Builder()
                    .setColor(color)
                    .setSymbol(cardTwoSymbol)
                    .build();

            ArrayList<Card> cards = new ArrayList<>();
            cards.add(cardOne);
            cards.add(cardTwo);
            Hand hand = new Hand(cards);

            assertTrue(hand.getHand().size() == 2);
            hand.removeCard(color, cardOneSymbol);
            assertTrue(hand.getHand().size() == 1);
            assertTrue(hand.getHand().contains(cardTwo));
        }

        @Test
        void removeCardException() {
            Hand hand = new Hand();
            Card.Color color = Card.Color.RED;
            Card.Symbol symbol = Card.Symbol.SUN;

            OnirimException thrown = assertThrows(
                    OnirimException.class,
                    () -> hand.removeCard(color, symbol),
                    "Expected removeCard(color, symbol) to throw, but it didn't"
            );

            assertTrue(hand.getHand().size() == 0);
            assertTrue(thrown.getMessage().contains("Could not remove card with color " + color + " and symbol "
                    + symbol + " because that card is not in hand."));
        }

        @Nested
        class drawStartingHand {
            @Test
            void drawStartingHand() throws OnirimException {
                Card.Builder validStartingCardBuilder = new Card.Builder()
                        .setColor(Card.Color.RED)
                        .setSymbol(Card.Symbol.SUN);
                ArrayList<Card> validCards = new ArrayList<>();
                IntStream.range(0, Hand.MAX_CARDS_IN_HAND).forEach(i ->
                        validCards.add(validStartingCardBuilder.build()));

                Deck deck = new Deck(validCards);
                Hand hand = new Hand();
                hand.drawNewHand(deck);

                assertTrue(hand.getHand().size() == Hand.MAX_CARDS_IN_HAND);
                assertTrue(deck.getDeck().size() == 0);
                assertTrue(hand.getHand().containsAll(validCards));
            }

            @Test
            void deckTooSmallException() {
                Deck deck = new Deck(new ArrayList<>());
                int deckSizeBeforeDrawingHand = deck.getDeck().size();
                Hand hand = new Hand();

                OnirimException thrown = assertThrows(
                        OnirimException.class,
                        () -> hand.drawNewHand(deck),
                        "Expected drawStartingHand(deck) to throw, but it didn't"
                );

                assertTrue(hand.getHand().size() == 0);
                assertTrue(deck.getDeck().size() == deckSizeBeforeDrawingHand);
                assertTrue(thrown.getMessage().contains("Could not draw " + Hand.MAX_CARDS_IN_HAND+
                        " cards because deck is too small."));
            }

            @Test
            void deckNotEnoughValidStartingCardsException() {
                Card.Builder invalidStartingCardBuilder = new Card.Builder().setSymbol(Card.Symbol.NIGHTMARE);
                ArrayList<Card> invalidCards = new ArrayList<>();
                IntStream.range(0, Hand.MAX_CARDS_IN_HAND).forEach(i ->
                        invalidCards.add(invalidStartingCardBuilder.build()));

                Deck deck = new Deck(invalidCards);
                int deckSizeBeforeDrawingHand = deck.getDeck().size();
                Hand hand = new Hand();

                OnirimException thrown = assertThrows(
                        OnirimException.class,
                        () -> hand.drawNewHand(deck),
                        "Expected drawStartingHand(deck) to throw, but it didn't"
                );

                assertTrue(hand.getHand().size() == 0);
                assertTrue(deck.getDeck().size() == deckSizeBeforeDrawingHand);
                assertTrue(thrown.getMessage().contains("Could not draw " + Hand.MAX_CARDS_IN_HAND +
                        " cards because deck does not have enough valid starting cards."));
            }
        }
    }
}