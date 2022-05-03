class Main {
    public static void main(String[] args)
    {
        Deck deck = new Deck();
        Table table = new Table();

        System.out.println(table);

        System.out.println(deck.getDeck().size());

        Hand hand = new Hand(deck);

        System.out.println(hand);
        System.out.println(deck.getDeck().size());
   }
}
