import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Input {
    int MOVE_INDEX = 0;
    int COLOR_INDEX = 1;
    int SYMBOL_INDEX = 2;
    public enum INPUT {
        MOVE, COLOR, SYMBOL
    }
    public enum Move {
        PLAY, DISCARD, OPEN, LIMBO, DISCARD_HAND, DISCARD_DECK
    }

    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> validNormalInputs = new ArrayList<>(Arrays.asList(
            "prs", "prm", "prk", // play red
            "pbs", "pbm", "pbk", // play blue
            "pgs", "pgm", "pgk", // play green
            "pts", "ptm", "ptk", // play tan
            "drs", "drm", "drk", // discard red
            "dbs", "dbm", "dbk", // discard blue
            "dgs", "dgm", "dgk", // discard green
            "dts", "dtm", "dtk"  // discard tan
    ));

    private ArrayList<String> validNightmareInputs = new ArrayList<>(Arrays.asList(
            "drk", "dbk", "dgk", "dtk", // discard key
            "lrd", "lbd", "lgd", "ltd", // limbo door
            "D", "H" // discard deck,hand
    ));

    private ArrayList<String> validDoorInputs = new ArrayList<>(Arrays.asList(
            "o", "l"
    ));

    public Input() { }

    public String getNormalInput() {
        String input = null;

        while (!validNormalInputs.contains(input)) {
            input = scanner.next();
        }

        return input;
    }

    public String getNightmareInput() {
        String input = null;

        while (!validNightmareInputs.contains(input)) {
            input = scanner.next();
        }

        return input;
    }

    public String getDoorInput() {
        String input = null;

        while (!validDoorInputs.contains(input)) {
            input = scanner.next();
        }

        return input;
    }

    public Move translateMove(String input) throws OnirimException {
        checkIndexExists(input, MOVE_INDEX, INPUT.MOVE);
        Character moveChar = input.charAt(MOVE_INDEX);

        if (moveChar.equals('p')) {
            return Move.PLAY;
        } else if (moveChar.equals('d')) {
            return Move.DISCARD;
        } else if (moveChar.equals('o')) {
            return Move.OPEN;
        } else if (moveChar.equals('l')) {
            return Move.LIMBO;
        } else if (moveChar.equals('H')){
            return Move.DISCARD_HAND;
        } else if (moveChar.equals('D')){
            return Move.DISCARD_DECK;
        }

        throw new OnirimException("Could not translate input into move.");
    }

    public Card.Color translateColor(String input) throws OnirimException {
        checkIndexExists(input, COLOR_INDEX, INPUT.COLOR);
        Character colorChar = input.charAt(COLOR_INDEX);

        if (colorChar.equals('r')) {
            return Card.Color.RED;
        } else if (colorChar.equals('b')) {
            return Card.Color.BLUE;
        } else if (colorChar.equals('g')) {
            return Card.Color.GREEN;
        } else if (colorChar.equals('t')) {
            return Card.Color.TAN;
        }

        throw new OnirimException("Could not translate input into color.");
    }

    public Card.Symbol translateSymbol(String input) throws OnirimException {
        checkIndexExists(input, SYMBOL_INDEX, INPUT.SYMBOL);
        Character symbolChar = input.charAt(SYMBOL_INDEX);

        if (symbolChar.equals('s')) {
            return Card.Symbol.SUN;
        } else if (symbolChar.equals('m')) {
            return Card.Symbol.MOON;
        } else if (symbolChar.equals('k')) {
            return Card.Symbol.KEY;
        } else if (symbolChar.equals('d')) {
            return Card.Symbol.DOOR;
        }

        throw new OnirimException("Could not translate input into symbol.");
    }

    private void checkIndexExists(String input, int index, INPUT i) throws OnirimException {
        if (input.length() < (index + 1)) {
            throw new OnirimException(String.format("Input is not long enough for %s index.", i));
        }
    }
}
