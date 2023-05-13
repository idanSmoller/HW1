import java.util.Arrays;

public class Board {
    private static final String INPUT_EMPTY = "_";
    private static final String INPUT_NEW_LINE = "|";
    private static final String INPUT_NEW_TILE = " ";
    private Tile[][] tiles;
    private int emptyX;
    private int emptyY;

    public Board(int emptyX, int emptyY) {
        this.emptyX = emptyX;
        this.emptyY = emptyY;
    }

    public Board(String str) {
        String[] rowsStr = str.split(INPUT_NEW_LINE);
        this.tiles = new Tile[rowsStr.length][];
        String[] curRowStr;
        for (int i = 0; i < rowsStr.length; i++) {
            curRowStr = rowsStr[i].split(INPUT_NEW_TILE);
            this.tiles[i] = new Tile[curRowStr.length];
            for(int j = 0; j < curRowStr.length; j++) {
                if (curRowStr[j] == INPUT_EMPTY) {
                    this.tiles[i][j] = new Tile(true, 0);
                    this.emptyX = j;
                    this.emptyY = i;
                }
                else {
                    this.tiles[i][j] = new Tile(false, Integer.parseInt(curRowStr[j]));
                }
            }
        }
    }

    public boolean isGoal() {
        int counter = 1;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++){
                if (!this.tiles[i][j].isEmpty()) {
                    if (counter != this.tiles[i][j].getValue()) {
                        return false;
                    }
                    counter++;
                }
            }
        }
        return true;
    }


    public boolean isActionPossible(int addXToEmpty, int addYToEmpty) {
        int wantedEmptyX = this.emptyX + addXToEmpty;
        int wantedEmptyY = this.emptyY + addYToEmpty;
        return (wantedEmptyX >= 0 && wantedEmptyX < this.tiles[0].length &&
                wantedEmptyY >= 0 && wantedEmptyY < this.tiles.length);
    }


    public Action getActionIfPossible(int addXToEmpty, int addYToEmpty, Direction dir) {
        if (this.isActionPossible(0, -1)) {
            return new Action(this.tiles[emptyY + addYToEmpty][emptyX + addXToEmpty], dir);
        }
        return null;
    }


    public Action[] actions() {
        // finds all possible actions (if not possible, so null)
        Action[] allActions = new Action[4];
        allActions[0] = getActionIfPossible(0, 1, Direction.UP);
        allActions[1] = getActionIfPossible(0, -1, Direction.DOWN);
        allActions[2] = getActionIfPossible(-1, 0, Direction.RIGHT);
        allActions[3] = getActionIfPossible(1, 0, Direction.LEFT);
        // finds how many actions are possible
        int counter = 0;
        for (int i = 0; i < allActions.length; i++) {
            if (allActions[i] != null) {
                counter++;
            }
        }
        // move the possible actions to array at the right size
        counter = 0;
        Action[] realActions = new Action[counter];
        for (int i = 0; i < allActions.length; i++) {
            if (allActions[i] != null) {
                realActions[counter] = allActions[i];
                counter++;
            }
        }
        return realActions;
    }


    public Board realCopy() {
        // mention: don't create new tiles. Just put the references of the current ones on another Board
        Board temp = new Board(this.emptyX, this.emptyY);
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                temp.tiles[i][j] = this.tiles[i][j];
            }
        }
        return temp;
    }


    public Board result(Action action) {
        Board temp = this.realCopy();
        int addX, addY;
        switch(action.getDirection()){
            case Direction.UP:
                addX = 0;
                addY = -1;
                break;
            case Direction.DOWN:
                addX = 0;
                addY = 1;
                break;
            case Direction.RIGHT:
                addX = 1;
                addY = 0;
                break;
            case Direction.LEFT:
                addX = -1;
                addY = 0;
                break;
            default:
                addX = 99999;
                addY = 99999;
        }
        temp.tiles[temp.emptyY - addY][temp.emptyX - addX] = temp.tiles[temp.emptyY][temp.emptyX];
        temp.tiles[temp.emptyY][temp.emptyX] = action.getTile();  // function changes only references, except this tile
        temp.emptyX = emptyX - addX;
        temp.emptyY = emptyY - addY;
        return temp;
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
