public class Tile {
    public final int EMPTY = 0;
    public final String EMPTY_STR_REP = "_";

    public final int value;

    public Tile(int num) {
        this.value = num;
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