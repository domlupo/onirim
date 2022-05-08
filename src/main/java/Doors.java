import java.util.ArrayList;

public class Doors {
    ArrayList<Card> doors;

    public Doors() {
        this.doors = new ArrayList<>();
    }

    public Doors(ArrayList<Card> doors) {
        this.doors = doors;
    }

    public void addDoor(Card door) {
        if (door.getSymbol() != Card.Symbol.DOOR) {
            // TODO throw exception
        }

        int doorsWithMatchingColor = 0;
        for (int i = 0; i < doors.size(); i++) {
            if (door.getColor() == doors.get(i).getColor()) {
                doorsWithMatchingColor +=1;
            }
        }

        if (doorsWithMatchingColor >= Deck.DOORS_PER_COLOR) {
            // TODO throw exception
        }

        doors.add(door);
    }

    public void doorsComplete() {
        // TODO
    }
}
