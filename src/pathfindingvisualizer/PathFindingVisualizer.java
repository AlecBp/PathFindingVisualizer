package pathfindingvisualizer;

/**
 *
 * @author Alec Pagliarussi
 */
public class PathFindingVisualizer {

    public static void main(String[] args) throws InterruptedException {
        Graph g = new Graph(30, 50);
        g.fillMatrix();
        g.printMatrix();
        System.out.println("_________________________");
        g.dijkstra(0, 0, 10, 0, true);
        g.aStar(0, 0, 10, 0, true);
    }
}
