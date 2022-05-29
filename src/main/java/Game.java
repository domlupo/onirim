public class Game {
    private static Input i;
    private static Deck deck;
    private static Hand hand;
    private static Labyrinth labyrinth;
    private static Doors doors;
    private static boolean won;
    private static boolean lost;

    public Game() {
        i = new Input();
        deck = new Deck();
        hand = new Hand();
        labyrinth = new Labyrinth();
        doors = new Doors();
        won = false;
        lost = false;
    }

    void play() throws OnirimException {
        deck.initialize();
        hand.drawNewHand(deck);

        while (!(won || lost)) {
            System.out.println(hand);
            System.out.println(labyrinth);
            processInput();
            won = doors.doorsComplete();
            lost = false; // check lose
            processDraw();
        }
    }

    public static void processInput() throws OnirimException {
        String input = i.getNormalInput();
        Input.Move move = i.translateMove(input);
        Card.Color color = i.translateColor(input);
        Card.Symbol symbol = i.translateSymbol(input);

        if (move == Input.Move.PLAY) {
            playCard(color, symbol);
            return;
        } else if (move == Input.Move.DISCARD) {
            discardCard(color, symbol, hand);
            return;
        }

        throw new OnirimException("Cannot process input.");
    }

    public static void processNightmareInput() throws OnirimException {
        String input = i.getNightmareInput();
        Input.Move move = i.translateMove(input);

        if (move == Input.Move.DISCARD_DECK) {
            // discard deck
            return;
        } else if (move == Input.Move.DISCARD_HAND) {
            // discard hand
            return;
        }

        Card.Color color = i.translateColor(input);
        Card.Symbol symbol = i.translateSymbol(input);

        if (move == Input.Move.DISCARD) {
            discardCard(color, symbol, hand);
            return;
        } else if (move == Input.Move.LIMBO) {
            // limbo card
            return;
        }

        throw new OnirimException("Cannot process nightmare input.");
    }

    private static void processDraw() throws OnirimException {
        Card card = deck.drawCard();

        if (card.getSymbol() == Card.Symbol.SUN ||
                card.getSymbol() == Card.Symbol.MOON ||
                card.getSymbol() == Card.Symbol.KEY) {

            hand.addCard(card.getCard());
            return;
        }

        // else if card is door

        // else if card is nightmare

        // throw exception
    }

    private static void playCard(Card.Color color, Card.Symbol symbol) throws OnirimException {
        Card card;

        try {
            card = hand.removeCard(color, symbol);
        } catch (OnirimException e) {
            throw e;
        }

        try {
            labyrinth.playCard(card);
        } catch (OnirimException e) {
            hand.addCard(card);
            throw e;
        }
    }

    private static void discardCard(Card.Color color, Card.Symbol symbol, Hand hand) throws OnirimException {
        hand.removeCard(color, symbol);
    }
}
