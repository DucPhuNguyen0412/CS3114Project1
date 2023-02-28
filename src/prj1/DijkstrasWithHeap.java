package prj1;

import java.util.ArrayList;


//import java.util.ArrayList;

/**
 * 
 * The implementation of Dijkstras shortest path algorithm by using
 * min-heaps
 * 
 * @author Phu Nguyen
 */
public class DijkstrasWithHeap {
    private int n;
    private int[][] graph;

    /**
     * Constructor of the class
     * 
     * @param n:
     *            number of nodes of the graph
     * @param edges:
     *            the set of edges of the graph. Each row of 'edges' is in the
     *            form of [u, v, w], which means that there is an edge between u
     *            and v with weight w. So edges[i][0] and edges[i][1] are the
     *            end-points of the i-th edge and edges[i][2] is its weight
     */
    public DijkstrasWithHeap(int n, int[][] edges) {
        int[][] graph = new int[n][3];
           for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    graph[i][j] = edges[i][j];
                }
           }
        this.n = n;
        this.graph = graph;
    }


    /**
     * This method computes and returns the distances of all nodes of the graph
     * from the source node
     * 
     * @param source
     * 
     * @return an array containing the distances of the nodes of the graph from
     *         source. Element i of the returned array represents the distance
     *         of node i from the source
     */
     public int[] run(int source) {
        int[] distance = new int[n];
        ArrayList<Integer> visited = new ArrayList<>(); 
        MinHeap q = new MinHeap(n, 2);

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[source-1] = 0;
        q.insert(source, 0);

       while (visited.size() != n && !q.isEmpty()) {
            int[] min = q.extractMin();
            int u = min[0];
            int d = min[1];
            if (!visited.contains(u)) {
                visited.add(u);
            }
            getNeighbors(u, d, visited, q, distance);
        }
        for (int i = 0; i < n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }
        return distance;


    }


     private void getNeighbors(int source, int w, ArrayList<Integer> visited, MinHeap q, int[] distance) {
        //add -1 here 
        for (int i = 0; i < n; i++) {
           // if unexplored, add to queue
           if ((graph[i][0] == source && !visited.contains(graph[i][1]))) {
                q.insert(graph[i][1], graph[i][2]);
                distance[(graph[i][1]) - 1] = min(distance[(graph[i][1] - 1)], distance[source-1] + graph[i][2]);
           }
           else if (graph[i][1] == source && !visited.contains(graph[i][0])) {
                q.insert(graph[i][0], graph[i][2]);
                distance[(graph[i][0]) - 1] = min(distance[(graph[i][0]) - 1], distance[source - 1] + graph[i][2]);
           } 
        }
    }

    private int min(int d1, int d2) {
        if (d1 <= d2) {
            return d1;
        }
        return d2;
     }

}
