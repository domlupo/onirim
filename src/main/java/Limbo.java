import java.util.ArrayList;

public class Limbo {

    private ArrayList<Card> limbo;

    public Limbo() {
        limbo = new ArrayList<>();
    }

    public Limbo(Card card) {
        limbo = new ArrayList<>();
        limbo.add(card);
    }

    public Limbo(ArrayList<Card> cards) {
        limbo = new ArrayList<>(cards);
    }

    public ArrayList getLimbo() {
        return limbo;
    }

    public void addCard(Card card) throws OnirimException {
        if (card.getSymbol() == Card.Symbol.DOOR || card.getSymbol() == Card.Symbol.NIGHTMARE) {
            limbo.add(card);
        } else {
            throw new OnirimException(
                    String.format("Could not limbo card because the card symbol is %s.", card.getSymbol())
            );
        }
    }

    public String toString() {
        return limbo.toString();
    }
}
