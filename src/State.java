public class State {
    private Board brd;

    public State(String str) {
        this.brd  = new Board(str);
    }

    public State(Board brd) {
        this.brd = brd;
    }

    public boolean isGoal() {
        return this.brd.isGoal();
    }

    public Action[] actions() {
        return brd.actions();
        // TODO: to check that there no duplicates of states at the set (different reference, same values)
    }

    public State result(Action action) {
        return new State(this.brd.result(action));
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
