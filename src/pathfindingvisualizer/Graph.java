package pathfindingvisualizer;

/**
 *
 * @author Alec
 */
public class Graph {

    private final GraphNode[][] matrix;
    private final int width;
    private final int height;
    private boolean useDiagonal;

    public Graph(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new GraphNode[this.height][this.width];
        useDiagonal = false;
    }

    public Graph(int width, int height, boolean useDiagonal) {
        this.width = width;
        this.height = height;
        matrix = new GraphNode[this.height][this.width];
        this.useDiagonal = useDiagonal;
    }

    public void setDiagonal(boolean useDiagonal) {
        this.useDiagonal = useDiagonal;
    }

    public void fillMatrix() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matrix[y][x] = new GraphNode(x, y);
            }
        }
    }

    public void resetAll() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j].resetNode();
            }
        }
    }

    public void resetVisited() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j].markVisited();
            }
        }
    }

    public void resetParent() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j].setParent(null);
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j].getW() + "\t");
            }
            System.out.println("");
        }
    }

    private GraphNode getTop(GraphNode current) {
        int y = current.getY() - 1;
        if (y >= 0) {
            return matrix[y][current.getX()];
        }
        return null;
    }

    private GraphNode getBottom(GraphNode current) {
        int y = current.getY() + 1;
        if (y < height) {
            return matrix[y][current.getX()];
        }
        return null;
    }

    private GraphNode getLeft(GraphNode current) {
        int x = current.getX() - 1;
        if (x >= 0) {
            return matrix[current.getY()][x];
        }
        return null;
    }

    private GraphNode getRight(GraphNode current) {
        int x = current.getX() + 1;
        if (x < width) {
            return matrix[current.getY()][x];
        }
        return null;
    }

    public double euclideanDistance(GraphNode a, GraphNode b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public void dijkstra(int startX, int startY, int endX, int endY, boolean shortCircuit) {
        // Solve issue of min heap using array and allocating unecessary memory
        MinHeap heap = new MinHeap(width * height);
        // Set start and end
        GraphNode start = matrix[startY][startX];
        GraphNode end = matrix[endY][endX];

        GraphNode temp, curr;
        GraphNode[] neighbours = new GraphNode[8];
        /* Index order of neighbours array
         0   1   2
         7   c   3
         6   5   4
         */
        start.setCost(0);
        heap.insert(start);

        int count = width * height;

        while (!heap.isEmpty()) {

            temp = heap.delete();
            while (count > 0 && (temp == null || temp.v == true)) {
                temp = heap.delete();
            }
            count--;
            temp.v = true;
            if (shortCircuit) {
                if (end.equals(temp)) {
                    break;
                }
            }

            System.out.println("Popped out " + temp.x + " " + temp.y);

            // Get neightbours
            System.out.println("\tNeighbours");
            neighbours[1] = getTop(temp);
            if (neighbours[1] != null) {
                System.out.println("TOP " + neighbours[1].x + " " + neighbours[1].y);
            }
            neighbours[3] = getRight(temp);
            if (neighbours[3] != null) {
                System.out.println("RIGHT " + neighbours[3].x + " " + neighbours[3].y);
            }
            neighbours[5] = getBottom(temp);
            if (neighbours[5] != null) {
                System.out.println("BOTTOM " + neighbours[5].x + " " + neighbours[5].y);
            }
            neighbours[7] = getLeft(temp);
            if (neighbours[7] != null) {
                System.out.println("LEFT " + neighbours[7].x + " " + neighbours[7].y);
            }

            if (!useDiagonal) {
                for (int i = 1; i < neighbours.length; i += 2) {
                    curr = neighbours[i];
                    if (curr != null) {
                        if (!curr.v) {
                            if ((temp.cost + curr.w) < curr.cost) {
                                curr.setCost(temp.cost + curr.w);
                                curr.parent = temp;
                            }
                            heap.insert(curr);
                        }
                    }
                }
            } else {
                // Implement later
                // Algorithm using the diagonals to move
            }

            System.out.println("Size q " + heap.getNumItems());
            heap.print();
//            System.out.println("Count " + countUniqueInQueue);
            System.out.println("____________________________________");
        }

        // reading path back
        System.out.println("Path: ");
        curr = end;
        while (curr.parent != null) { // Could also use !curr.equals(start)
            // print or display curr
            System.out.println(curr.getCoord());
            //move to parent
            curr = curr.parent;
        }
        resetAll();
    }

    public void aStar(int startX, int startY, int endX, int endY, boolean shortCircuit) {
        // Solve issue of min heap using array and allocating unecessary memory
        MinHeap heap = new MinHeap(width * height);
        // Set start and end
        GraphNode start = matrix[startY][startX];
        GraphNode end = matrix[endY][endX];

        GraphNode temp, curr;
        GraphNode[] neighbours = new GraphNode[8];
        /* Index order of neighbours array
         0   1   2
         7   c   3
         6   5   4
         */
        start.setCost(0);
        heap.insert(start);

        int count = width * height;

        while (!heap.isEmpty()) {

            temp = heap.delete();
            while (count > 0 && (temp == null || temp.v == true)) {
                temp = heap.delete();
            }
            count--;
            temp.v = true;
            if (shortCircuit) {
                if (end.equals(temp)) {
                    break;
                }
            }

            System.out.println("Popped out " + temp.x + " " + temp.y);

            // Get neightbours
            System.out.println("\tNeighbours");
            neighbours[1] = getTop(temp);
            if (neighbours[1] != null) {
                System.out.println("TOP " + neighbours[1].x + " " + neighbours[1].y);
            }
            neighbours[3] = getRight(temp);
            if (neighbours[3] != null) {
                System.out.println("RIGHT " + neighbours[3].x + " " + neighbours[3].y);
            }
            neighbours[5] = getBottom(temp);
            if (neighbours[5] != null) {
                System.out.println("BOTTOM " + neighbours[5].x + " " + neighbours[5].y);
            }
            neighbours[7] = getLeft(temp);
            if (neighbours[7] != null) {
                System.out.println("LEFT " + neighbours[7].x + " " + neighbours[7].y);
            }

            if (!useDiagonal) {
                for (int i = 1; i < neighbours.length; i += 2) {
                    curr = neighbours[i];
                    if (curr != null) {
                        curr.h = euclideanDistance(curr, end);
                        System.out.println("H " + curr.h);
                        if (!curr.v) {
                            // Totalcost returns heuristic + cost
                            // Only store in cost var the actual cost, without heuristic
                            if ((temp.cost + temp.h + curr.w) < curr.cost + curr.h) {
                                curr.setCost(temp.cost + curr.w);
                                curr.parent = temp;
                            }
                            heap.insert(curr);
                        }
                    }
                }
            } else {
                // Implement later
                // Algorithm using the diagonals to move
            }

            System.out.println("Size q " + heap.getNumItems());
            heap.print();
//            System.out.println("Count " + countUniqueInQueue);
            System.out.println("____________________________________");
        }

        // reading path back
        System.out.println("Path: ");
        curr = end;
        while (curr.parent != null) { // Could also use !curr.equals(start)
            // print or display curr
            System.out.println(curr.getCoord());
            //move to parent
            curr = curr.parent;
        }
        resetAll();
    }
}
