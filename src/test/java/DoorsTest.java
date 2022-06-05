import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DoorsTest {
    @Nested
    class addDoor {
        @Test
        void addDoor() throws OnirimException {
            Doors doors = new Doors();

            Card doorCard = new Card.Builder()
                    .setColor(Card.Color.RED)
                    .setSymbol(Card.Symbol.DOOR)
                    .build();

            assertTrue(doors.getDoors().size() == 0);
            doors.addDoor(doorCard);
            assertTrue(doors.getDoors().size() == 1);
            assertTrue(doors.getDoors().contains(doorCard));
        }
    }

    @Nested
    class doorComplete {
        @Test
        void NoColorsDone() {
            Doors doors = new Doors();
            assertFalse(doors.doorsComplete());
        }

        @Test
        void OneColorDone() {
            ArrayList<Card.Color> red = new ArrayList<>();
            red.add(Card.Color.RED);
            Doors redDoorsComplete = completeColors(red);
            assertFalse(redDoorsComplete.doorsComplete());
        }

        @Test
        void AllColorsDone() {
            ArrayList<Card.Color> allColors = new ArrayList<>();
            for (Card.Color color : Card.Color.values()) {
                allColors.add(color);
            }
            Doors allDoorsComplete = completeColors(allColors);

            assertTrue(allDoorsComplete.doorsComplete());
        }
    }

    public Doors completeColors(ArrayList<Card.Color> colors) {
        ArrayList<Card> maxColorDoors = new ArrayList<>();
        colors.forEach(color-> {
            Card.Builder doorCardBuilder = new Card.Builder()
                    .setColor(color)
                    .setSymbol(Card.Symbol.DOOR);

            IntStream.range(0, Deck.DOORS_PER_COLOR).forEach(i ->
                    maxColorDoors.add(doorCardBuilder.build()));
        });

        Doors doors = new Doors(maxColorDoors);
        return doors;
    }
}