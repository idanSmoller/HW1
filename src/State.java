public class State {
    private Board board;

    public State(String str) {
        this.board  = new Board(str);
    }

    public State(Board brd) {
        this.board = brd;
    }

    public Board getBoard() {
        return this.board;
    }

    /**
     * check whether the game is over
     * @return whether the game is over
     */
    public boolean isGoal() {
        return this.board.isGoal();
    }

    /**
     * get all possible actions from the board
     * @return all possible actions from the board
     */
    public Action[] actions() {
        return board.actions();
    }

    /**
     * get the state resulting from an action on the current state
     * @param action the action to preform
     * @return the state resulting from an action on the current state
     */
    public State result(Action action) {
        return new State(this.board.result(action));
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
