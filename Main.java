class Main {
    public static void main(String[] args)
    {
        Deck deck = new Deck();
        System.out.println(deck.getDeck().size());

        Hand hand = new Hand(deck);
        System.out.println(hand);
        System.out.println(deck);
        System.out.println(deck.getDeck().size());

        System.out.println(hand.hasCard(Card.Color.RED, Card.Symbol.SUN));
        if (hand.hasCard(Card.Color.RED, Card.Symbol.SUN)) {
            hand.removeCard(Card.Color.RED, Card.Symbol.SUN);
            hand.addCard(deck.drawCard().get());
            System.out.println(deck.getDeck().size());
        }

        System.out.println(hand);
   }

   private void handleDrawnCard(Card card, Hand hand) {
       if (card.getSymbol() == Card.Symbol.SUN 
           || card.getSymbol() == Card.Symbol.MOON
           || card.getSymbol() == Card.Symbol.KEY) {

           hand.addCard(card.getCard());
       }

       // else if card is door, handle
       
       // else if card is nightmare, handle
       
       // throw exception
   }

   private boolean validPlayCard(Card.Color color, Card.Symbol symbol) {
       // check if card is in hand
       
       // check if card is playable in labyrinth
       
       return true;
   }

   private void playCard(Card.Color color, Card.Symbol symbol) {
      
       // remove card from hand
       
       // place in labyrinth
   }

   private boolean validDiscardCard(Card.Color color, Card.Symbol symbol, Hand hand) {
       return hand.hasCard(color, symbol);
   }

   private void discardCard(Card.Color color, Card.Symbol symbol) {
       // remove card from hand
       
       // if card is key, perform special actions
   }

}
