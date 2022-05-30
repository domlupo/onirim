public class Game {
    private Input i;
    private Deck deck;
    private Hand hand;
    private Labyrinth labyrinth;
    private Doors doors;
    private Limbo limbo;
    private boolean won;
    private boolean lost;
    private int NIGHTMARE_CARD_DISCARD = 5;
    private int LABYRINTH_COLOR_STREAK_FOR_DOOR = 3;

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
            System.out.println(String.format("Deck: %s", deck));
            System.out.println(deck.getDeck().size());
            processInput();
            processLabyrinth();
            won = doors.doorsComplete();
            lost = false; // check lost
            processDraws();
            processLimbo();
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
        } else if (move == Input.Move.DISCARD) {
            discardCard(color, symbol, hand);
        } else {
            throw new OnirimException("Cannot process input.");
        }
    }

    public void processLabyrinth() throws OnirimException {
        if (labyrinth.getLabyrinth().isEmpty()) {
            return;
        }

        Card.Color lastLabyrinthCardColor = labyrinth.getLastCardColor();

        if (doors.doorsCompleteForColor(lastLabyrinthCardColor)) {
            return;
        }

        if (labyrinth.getCardColorStreakCounter() == LABYRINTH_COLOR_STREAK_FOR_DOOR) {
            labyrinth.setCardColorStreakCounter(0);
            Card door = deck.removeCard(lastLabyrinthCardColor, Card.Symbol.DOOR);
            doors.addDoor(door);
            deck.shuffle();
        }
    }

    public void processNightmareCard() throws OnirimException {
        System.out.println("Resolve nightmare card");
        String input = i.getNightmareInput();
        Input.Move move = i.translateMove(input);

        if (move == Input.Move.DISCARD_DECK) {
            discardFromDeckToResolveNightmareCard();
            return;
        } else if (move == Input.Move.DISCARD_HAND) {
            discardHandToResolveNightmareCard();
            return;
        }

        Card.Color color = i.translateColor(input);
        Card.Symbol symbol = i.translateSymbol(input);

        if (move == Input.Move.DISCARD && symbol == Card.Symbol.KEY) {
            discardCard(color, symbol, hand);
        } else if (move == Input.Move.LIMBO) {
            Card limboDoor = doors.removeDoor(color);
            limbo.addCard(limboDoor);
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

        if (cardIsSunMoonOrKey(card)) {
            hand.addCard(card.getCard());
        } else if (card.getSymbol() == Card.Symbol.DOOR) {
            processDoorCard(card);
        } else if (card.getSymbol() == Card.Symbol.NIGHTMARE) {
            processNightmareCard();
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
            hand.removeCard(card.getColor(), Card.Symbol.KEY);
            doors.addDoor(card);
        } else if (move == Input.Move.LIMBO) {
            limbo.addCard(card);
        } else {
            throw new OnirimException("Cannot process door card.");
        }
    }

    private void processLimbo() {
        if (!limbo.getLimbo().isEmpty()) {
            deck.shuffle(limbo.getLimbo());
            limbo.getLimbo().clear();
        }
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

    private void discardHandToResolveNightmareCard() throws OnirimException {
        hand.getHand().clear();
        fillHandWithSunMoonAndKey();
    }

    private void fillHandWithSunMoonAndKey() throws OnirimException {
        while (hand.getHand().size() != Hand.MAX_CARDS_IN_HAND) {
            Card card = deck.drawCard();
            if (cardIsSunMoonOrKey(card)) {
                hand.addCard(card);
            } else {
                limbo.addCard(card);
            }
        }
    }

    private void discardFromDeckToResolveNightmareCard() throws OnirimException {
        int i = 0;
        while (i < NIGHTMARE_CARD_DISCARD) {
            Card card = deck.drawCard();

            if (cardIsSunMoonOrKey(card)) {
                i += 1;
            } else {
                limbo.addCard(card);
            }
        }
    }

    boolean cardIsSunMoonOrKey(Card card) {
        if (card.getSymbol() == Card.Symbol.SUN ||
                card.getSymbol() == Card.Symbol.MOON ||
                card.getSymbol() == Card.Symbol.KEY) {
            return  true;
        }

        return  false;
    }
}
