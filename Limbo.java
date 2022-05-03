import java.util.ArrayDeque;

public class Limbo {

    private ArrayDeque<Card> limbo = new ArrayDeque<Card>();

    public ArrayDeque<Card> getLimbo() {
        return limbo;
    }

    public String toString() {
        return limbo.toString();
    }
}
