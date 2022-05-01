class Main {
    public static void main(String[] args)
    {
        Card card = Card.Builder.newInstance()
            .setColor(Card.Color.RED)
            .setSymbol(Card.Symbol.KEY)
            .build();

        System.out.println(card);
    }
}
