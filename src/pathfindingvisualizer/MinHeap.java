package pathfindingvisualizer;

/* @author AlecBp */
public class MinHeap {

    private final GraphNode[] arr;
    private final int max; // Might be needed
    private int numItems;

    public MinHeap(int max) {
        this.max = max;
        arr = new GraphNode[max + 1];
        arr[0] = null;
        numItems = 1;
    }

    public void insert(GraphNode data) {
        int p1, p2;
        p1 = numItems++;
        arr[p1] = data;
        // sift up
        p2 = (p1 / 2);
        while (p2 >= 1 && arr[p2].getTotalCost() > data.getTotalCost()) {
            swap(p1, p2);
            p1 = p2;
            p2 = (p2 / 2);
        }
    }

    public GraphNode delete() {
        if (numItems < 1) {
            return null;
        }
        int curr, p1, p2;
        GraphNode removed;
        removed = arr[1];

        arr[1] = arr[numItems - 1];
        numItems--;

        //sift down
        curr = 1;
        p1 = curr * 2;
        p2 = curr * 2 + 1;

        while (p2 < numItems && p1 < numItems) {
//            System.out.println("p1 " + p1 + " p2 " + p2 + "numItems " + numItems);
//            print();
            if (arr[p1].getTotalCost() < arr[p2].getTotalCost()) {
                if (arr[p1].getTotalCost() < arr[curr].getTotalCost()) {
                    swap(curr, p1);
                    curr = p1;
                }else{
                    break;
                }
            } else {
                if (arr[p2].getTotalCost() < arr[curr].getTotalCost()) {
                    swap(curr, p2);
                    curr = p2;
                } else {
                    break;
                }
            }

            p1 = curr * 2;
            p2 = curr * 2 + 1;
        }
        return removed;
    }

    public void print() {
        for (int i = 1; i < numItems; i++) {
            System.out.print(arr[i].getCoord() + " cost: " + arr[i].getTotalCost() + "\t");
        }
        System.out.println("");
    }

    private void swap(int a, int b) {
        GraphNode temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public int getNumItems() {
        return numItems - 1;
    }

    public boolean isEmpty() {
        return numItems == 1;
    }
}
