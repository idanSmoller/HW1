import java.util.Arrays;

public class Board {
    private static final String INPUT_EMPTY = "_";
    private static final String INPUT_NEW_LINE = "\\|";
    private static final String INPUT_NEW_TILE = " ";
    private Tile[][] tiles;
    private int emptyX;
    private int emptyY;

    public Board(int numOfRow, int numOfCol) {
        this.tiles = new Tile[numOfRow][numOfCol];
    }

    public Board(String str) {
        String[] rowsStr = str.split(INPUT_NEW_LINE);
        this.tiles = new Tile[rowsStr.length][];
        String[] curRowStr;
        for (int i = 0; i < rowsStr.length; i++) {
            curRowStr = rowsStr[i].split(INPUT_NEW_TILE);
            this.tiles[i] = new Tile[curRowStr.length];
            for(int j = 0; j < curRowStr.length; j++) {
                if (curRowStr[j].equals(INPUT_EMPTY)) {
                    this.tiles[i][j] = new Tile(Tile.EMPTY);
                    this.emptyX = j;
                    this.emptyY = i;
                }
                else {
                    this.tiles[i][j] = new Tile(Integer.parseInt(curRowStr[j]));
                }
            }
        }
    }

    private void setEmptyX(int emptyX) {
        this.emptyX = emptyX;
    }

    private void setEmptyY(int emptyY) {
        this.emptyY = emptyY;
    }

    public boolean isGoal() {
        int counter = 1;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++){
                //check last tile
                if (i == this.tiles.length - 1 && j == this.tiles[0].length - 1 && this.tiles[i][j].isEmpty()) {
                    return true;
                }

                if (this.tiles[i][j].getValue() != counter) {
                    return false;
                }
                counter++;
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
        if (this.isActionPossible(addXToEmpty, addYToEmpty)) {
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
        int possibleActionsCounter = 0;
        for (int i = 0; i < allActions.length; i++) {
            if (allActions[i] != null) {
                possibleActionsCounter++;
            }
        }
        // move the possible actions to array at the right size
        int counter = 0;
        Action[] realActions = new Action[possibleActionsCounter];
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
        Board temp = new Board(this.tiles.length, this.tiles[0].length);
        temp.setEmptyX(this.emptyX);
        temp.setEmptyY(this.emptyY);
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                temp.tiles[i][j] = this.tiles[i][j];
            }
        }
        return temp;
    }


    public Board result(Action action) {
        Board temp = this.realCopy();
        int addX = 0, addY = 0;
        switch(action.getDirection()){
            case UP:
                addX = 0;
                addY = -1;
                break;
            case DOWN:
                addX = 0;
                addY = 1;
                break;
            case RIGHT:
                addX = 1;
                addY = 0;
                break;
            case LEFT:
                addX = -1;
                addY = 0;
                break;
            default:
                System.out.println("Impossible!");
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
