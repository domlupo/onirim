import java.util.ArrayList;

public class Doors {
    ArrayList<Card> doors;

    public Doors() {
        this.doors = new ArrayList<>();
    }

    public Doors(ArrayList<Card> doors) {
        this.doors = doors;
    }

    public ArrayList getDoors() {
        return doors;
    }

    public void addDoor(Card card) throws OnirimException {
        if (card.getSymbol() != Card.Symbol.DOOR) {
            throw new OnirimException("Could not add door because card is a " + card.getSymbol() + " symbol.");
        }

        int doorsWithMatchingColor = getDoorsWithMatchingColor(card.getColor());

        if (doorsWithMatchingColor >= Deck.DOORS_PER_COLOR) {
            throw new OnirimException("Could not add door because " + Deck.DOORS_PER_COLOR + " " + card.getColor()
                    + " doors are already completed.");
        }

        doors.add(card);
    }

    public Card removeDoor(Card.Color color) throws OnirimException {
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i).getColor().equals(color)) {
                return doors.remove(i);
            }
        }

        throw new OnirimException("Could not remove door with color " + color + " because no doors of that color are open.");
    }

    public boolean doorsComplete() {
        for (Card.Color color : Card.Color.values()) {
            if (!doorsCompleteForColor(color)) {
                return false;
            }
        }

        return true;
    }

    public boolean doorsCompleteForColor(Card.Color color) {
        int doorsWithMatchingColor = getDoorsWithMatchingColor(color);

        if (doorsWithMatchingColor != 2) {
            return false;
        }

        return true;
    }

    public String toString() {
        return doors.toString();
    }

    private int getDoorsWithMatchingColor(Card.Color color) {
        int doorsWithMatchingColor = 0;
        for (int i = 0; i < doors.size(); i++) {
            if (color == doors.get(i).getColor()) {
                doorsWithMatchingColor +=1;
            }
        }

        return doorsWithMatchingColor;
    }
}
