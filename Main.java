class Main {
    public static void main(String[] args)
    {
        Deck deck = new Deck();
        Labyrinth labyrinth = new Labyrinth();

        System.out.println(labyrinth);

        System.out.println(deck.getDeck().size());

        Hand hand = new Hand(deck);

        System.out.println(hand);
        System.out.println(deck.getDeck().size());
   }
}
