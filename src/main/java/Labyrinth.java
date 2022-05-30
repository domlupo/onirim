import java.util.ArrayList;

public class Labyrinth {
    private int cardColorStreakCounter = 0;

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

    public int getCardColorStreakCounter() {
        return cardColorStreakCounter;
    }

    public void setCardColorStreakCounter(int i) {
        cardColorStreakCounter = i;
        return;
    }

    public void playCard(Card playCard) throws OnirimException {
        if (playCard.getSymbol() == Card.Symbol.DOOR ||
                playCard.getSymbol() == Card.Symbol.NIGHTMARE) {
            throw new OnirimException("Could not play card because the card symbol is  " +
                    playCard.getSymbol() + " which can never be played in labyrinth.");
        }

        if (labyrinth.size() == 0) {
            labyrinth.add(playCard);
            cardColorStreakCounter = 1;
            return;
        }

        Card lastLabyrinthCard = labyrinth.get(labyrinth.size() - 1);

        if (lastLabyrinthCard.getSymbol() == playCard.getSymbol()) {
            throw new OnirimException("Could not play card because the card symbol " + playCard.getSymbol() +
                    " is the last played symbol in the labyrinth.");
        }

        labyrinth.add(playCard);
        if (getLastCardColor() == getSecondLastCardColor()) {
            cardColorStreakCounter += 1;
        } else {
            cardColorStreakCounter = 1;
        }
    }

    public Card.Color getLastCardColor() throws OnirimException {
        if (labyrinth.isEmpty()) {
            throw new OnirimException("Could not get last card color because labyrinth has no cards.");
        }

        return labyrinth.get(labyrinth.size() - 1).getColor();
    }

    public Card.Color getSecondLastCardColor() throws OnirimException {
        if (labyrinth.isEmpty()) {
            throw new OnirimException("Could not get second last card color because labyrinth has no cards.");
        }

        if (labyrinth.size() == 1) {
            throw new OnirimException("Could not get second last card color because labyrinth has only one card.");
        }

        return labyrinth.get(labyrinth.size() - 2).getColor();
    }

    public String toString() {
        return labyrinth.toString();
    }
}
