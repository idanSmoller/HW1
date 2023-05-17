public class Tile {
    public static final int EMPTY = 0;
    public final String EMPTY_STR_REP = "_";

    private final int value;

    public Tile(int num) {
        this.value = num;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isEmpty() {
        return this.value == this.EMPTY;
    }


    @Override
    public String toString() {
        if (value == EMPTY) {
            return EMPTY_STR_REP;
        }
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}