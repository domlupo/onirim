public class Card {
    public enum Color {
        RED, BLUE, GREEN, TAN
    }

    public enum Symbol {
        STAR, MOON, KEY, DOOR
    }

    private final Color color;
    private final Symbol symbol;

    public Card(Builder builder) {
        this.color = builder.color;
        this.symbol = builder.symbol;
    }

    public static class Builder {

        private Color color;
        private Symbol symbol;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {}

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder setSymbol(Symbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }

    public Color getColor() {
        return color;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String toString() {
        return "Color: " + color + ", Symbol: " + symbol;
    }
}
