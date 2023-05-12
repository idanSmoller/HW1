import java.util.Locale;

public class Action {
    Tile tile;
    Direction dir;

    @Override
    public String toString() {
        String ret = "";
        ret += "Move ";
        ret += tile.toString();
        ret += " ";
        ret += Action.this.dir.name().toLowerCase();

        return ret;
    }
}
