package prj1;

//import java.util.ArrayList;

/**
 * The implementation of Dijkstras shortest path algorithm by using a simple
 * linear search to find the unvisited node with the minimum distance estimate
 * 
 * @author Skylar Mayfield
 * @version 1.1
 */

public class DijkstrasWithoutHeap {
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
    public DijkstrasWithoutHeap(int n, int[][] edges) {
        int[][] graph = new int[3][n];
           for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; i++) {
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
        //Boolean[] visited = new Boolean[n];
        //ArrayList<Integer> visited = new ArrayList<>();
        //ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source-1] = 0;
        int min = graph[0][source];
        distance[min] = 1;
        return null;
    }
        
}
