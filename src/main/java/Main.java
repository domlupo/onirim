class Main {
    public static void main(String[] args)
    { }

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

   private void playCard(Card.Color color, Card.Symbol symbol) {
        // try to remove card

       // try to play card
       // if play card fails add card back to hand
   }

   private void discardCard(Card.Color color, Card.Symbol symbol, Hand hand) throws OnirimException {
       hand.removeCard(color, symbol);
   }

}
