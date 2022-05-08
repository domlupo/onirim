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

   private boolean validPlayCard(Card.Color color, Card.Symbol symbol) {
       // check if card is in hand
       
       // check if card is playable in labyrinth
       
       return true;
   }

   private void playCard(Card.Color color, Card.Symbol symbol) {
      
       // remove card from hand
       
       // place in labyrinth
   }

   private void  discardCard(Card.Color color, Card.Symbol symbol, Hand hand) throws OnirimException {
       hand.removeCard(color, symbol);
   }

   private void discardCard(Card.Color color, Card.Symbol symbol) {
       // remove card from hand
       
       // if card is key, perform special actions
   }

}
