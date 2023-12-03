package World;

public class Lane {
    private Line right;
    private Line left;

    public Lane(Line a, Line b) {
        this.right = a;
        this.left = b;
        correctLines();
    }

    private void correctLines() {
        if (right.getLine().getStrat().getX() > left.getLine().getStrat().getX()) {
            Line temp = right.getLine();
            this.right = left;
            this.left = temp;
        }
    }

    public Line getRight() {
        return right.getLine();
    }

    public Line getleft() {
        return left.getLine();
    }

}
