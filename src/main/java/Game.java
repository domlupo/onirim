public class Game {
    private Input i;
    private Deck deck;
    private Hand hand;
    private Labyrinth labyrinth;
    private Doors doors;
    private Limbo limbo;
    private boolean won;
    private boolean lost;

    public Game() {
        i = new Input();
        deck = new Deck();
        hand = new Hand();
        labyrinth = new Labyrinth();
        doors = new Doors();
        limbo = new Limbo();
        won = false;
        lost = false;
    }

    void play() throws OnirimException {
        deck.initialize();
        hand.drawNewHand(deck);

        while (!(won || lost)) {
            System.out.println(String.format("Hand: %s", hand));
            System.out.println(String.format("Labyrinth: %s", labyrinth));
            System.out.println(String.format("Doors: %s", doors));
            System.out.println(String.format("Limbo: %s", limbo));
            processInput();
            won = doors.doorsComplete();
            lost = false; // check lost
            processDraws();
        }
    }

    public void processInput() throws OnirimException {
        System.out.println("Input move");
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

    public void processNightmareInput() throws OnirimException {
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
        } else {
            throw new OnirimException("Cannot process nightmare input.");
        }
    }

    private void processDraws() throws OnirimException {
        while (hand.getHand().size() != Hand.MAX_CARDS_IN_HAND) {
            processDraw();
        }
    }

    private void processDraw() throws OnirimException {
        Card card = deck.drawCard();

        if (card.getSymbol() == Card.Symbol.SUN ||
                card.getSymbol() == Card.Symbol.MOON ||
                card.getSymbol() == Card.Symbol.KEY) {

            hand.addCard(card.getCard());
        } else if (card.getSymbol() == Card.Symbol.DOOR) {
            processDoorCard(card);
        } else if (card.getSymbol() == Card.Symbol.NIGHTMARE) {
            // process nightmare
        } else {
            throw new OnirimException("Cannot process draw.");
        }
    }

    private void processDoorCard(Card card) throws OnirimException {
        if (card.getSymbol() != Card.Symbol.DOOR) {
            throw new OnirimException("Cannot process card since it is not a door.");
        }

        if (!hand.hasCard(card.getColor(), Card.Symbol.KEY)) {
            limbo.addCard(card);
            return;
        }

        System.out.println(String.format("Open or Limbo %s door.", card.getColor()));
        String input = i.getDoorInput();
        Input.Move move = i.translateMove(input);

        if (move == Input.Move.OPEN) {
            doors.addDoor(card);
        } else if (move == Input.Move.LIMBO) {
            limbo.addCard(card);
        } else {
            throw new OnirimException("Cannot process door card.");
        }
    }

    private void processNightmareCard() {
        // get nightmare input

        // check if nightmare input is valid

        // perform nightmare input
    }

    private void playCard(Card.Color color, Card.Symbol symbol) throws OnirimException {
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
