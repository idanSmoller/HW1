public class Node {
    private Action action;  // null if the root
    private State currState;
    private Node parent;   // null if the root

    /**
     * Node constructor
     * @param action the action that lead to the current state
     * @param currState the current state of the game
     * @param parent the parent node from which the node was formed
     */
    public Node(Action action, State currState, Node parent) {
        this.action = action;
        this.currState = currState;
        this.parent = parent;
    }

    /**
     * Node constructor of the first node of the game
     * @param boardInitStr the string of the starting board
     */
    public Node(String boardInitStr) {     // initial the root Node
        this(null, new State(boardInitStr), null);
    }

    public Action getAction() {
        return action;
    }

    public State getState() {
        return currState;
    }

    public Node getParent() {
        return parent;
    }

    /**
     * get all the possible actions that can be preformed from the current state of the game
     * @return an array of actions that could be preformed on the board
     */
    public Node[] expand() {
        Action[] actions = currState.actions();
        int actionNum = actions.length;
        Node[] ret = new Node[actionNum];
        for (int i = 0; i < actionNum; i++) {
            ret[i] = new Node(actions[i], currState.result(actions[i]), this);
        }

        return ret;
    }

    /**
     * calculate the manhattan distance of a tile from its right spot
     * @param dimX the X dimension of the board
     * @param value the tile's value
     * @param row the tile's row in the current state
     * @param col the tile's column in the current state
     * @return the manhattan distance from the tile's spot
     */
    public int distanceFromHome(int dimX, int value, int row, int col) {
        int homeRow = (value - 1) / dimX;
        int homeCol = (value - 1) % dimX;

        int vertDistance = Math.abs(homeRow - row);
        int horDistance = Math.abs(homeCol - col);
        return  vertDistance + horDistance;
    }

    /**
     * calculate the heuristic value of the node's state
     * @return the heuristic value of the node's state
     */
    public int heuristicValue() {
        int sum = 0;
        Board thisBoard = this.currState.getBoard();
        int dimX = thisBoard.getDimX();
        int dimY = thisBoard.getDimY();
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                int dist = distanceFromHome(dimX, thisBoard.tileAt(j, i).getValue(), j, i);
                sum += dist;
            }
        }

        return sum;
    }
}
