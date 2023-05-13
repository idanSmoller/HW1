import java.util.Locale;

public class Action {
    Tile tile;
    Direction dir;

    public Action(Tile tile, Direction dir) {
        this.tile = tile;
        this.dir = dir;
    }


    public Tile getTile() {
        return tile;
    }

    public Direction getDirection() {
        return dir;
    }

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
