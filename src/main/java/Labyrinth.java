import java.util.ArrayList;

public class Labyrinth {

    private ArrayList<Card> labyrinth;

    public Labyrinth() {
        labyrinth = new ArrayList<>();
    }

    public Labyrinth(Card card) {
        labyrinth = new ArrayList<>();
        labyrinth.add(card);
    }

    public Labyrinth(ArrayList<Card> cards) {
        labyrinth = new ArrayList<>(cards);
    }

    public ArrayList<Card> getLabyrinth() {
        return labyrinth;
    }

    public void playCard(Card playCard) throws OnirimException {
        if (playCard.getSymbol() == Card.Symbol.DOOR ||
                playCard.getSymbol() == Card.Symbol.NIGHTMARE) {
            throw new OnirimException("Could not play card because the card symbol is  " +
                    playCard.getSymbol() + " which can never be played in labyrinth.");
        }

        if (labyrinth.size() == 0) {
            labyrinth.add(playCard);
            return;
        }

        Card lastLabyrinthCard = labyrinth.get(labyrinth.size() - 1);

        if (lastLabyrinthCard.getSymbol() == playCard.getSymbol()) {
            throw new OnirimException("Could not play card because the card symbol " + playCard.getSymbol() +
                    " is the last played symbol in the labyrinth.");
        }

        labyrinth.add(playCard);
    }

    public String toString() {
        return labyrinth.toString();
    }
}
