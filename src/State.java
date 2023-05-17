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

    public boolean isGoal() {
        return this.board.isGoal();
    }

    public Action[] actions() {
        return board.actions();
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
