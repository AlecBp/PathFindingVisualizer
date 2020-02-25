package pathfindingvisualizer;

/**
 *
 * @author Alec
 */
public class GraphNode {

    public GraphNode parent;
    public int x;
    public int y;
    public boolean v;   // visisted
    public double w;       // weight
    public double cost;    // cost
    public double h;       // heuristic

    public GraphNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = 1;
        v = false;
        parent = null;
        cost = Integer.MAX_VALUE;
        h = 0;
    }

    public void resetNode() {
        v = false;
        parent = null;
        cost = Integer.MAX_VALUE;
        h = 0;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    public GraphNode getParent() {
        return parent;
    }

    public boolean wasVisited() {
        return v;
    }

    public void markVisited() {
        v = true;
    }

    public void markUnvisited() {
        v = false;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getTotalCost() {
        return cost+h;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(GraphNode other) {
        return x == other.getX() && y == other.getY();
    }

    @Override
    public String toString() {
        String str = "\nVisisted = " + v;
        str += "\nWeight = " + w;
        return str;
    }

    public String getCoord() {
        String str = "X: " + x + " Y: " + y;
        return str;
    }
}
