public class State {
    private Board board;

    public State(String str) {
        this.board  = new Board(str);
    }

    public State(Board brd) {
        this.board = brd;
    }

    public boolean isGoal() {
        return this.board.isGoal();
    }

    public Action[] actions() {
        return board.actions();
        // TODO: to check that there no duplicates of states at the set (different reference, same values)
    }

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
