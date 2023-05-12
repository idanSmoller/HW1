public class Node {
    private Action action;
    private State currState;
    private Node parent;

    public Node(Action action, State currState, Node parent) {
        this.action = action;
        this.currState = currState;
        this.parent = parent;
    }

    public Node[] expand() {
        Action[] actions = currState.actions();
        int actionNum = actions.length;
        Node[] ret = new Node[actionNum];
        for (int i = 0; i < actionNum; i++) {
            ret[i] = new Node(actions[i], currState.result(actions[i]), this);
        }

        return ret;
    }

    public int huristicValue() {
        return 0; // TODO: this function
    }
}
