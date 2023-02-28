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
        Boolean[] visited = new Boolean[n];
        

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distance[source-1] = 0;
        //visited[source-1] = true;

        for (int i = 0; i < n; i++) {
            int min = findMin(distance, visited);
            visited[min-1] = true; // setting true once we process it
            if (!visited[min] && graph[i][min] != -1 && distance[i] != Integer.MAX_VALUE && distance[min] < distance[i]) {
                distance[min] = distance[i] + graph[i][min];

            }
        }
        return distance;
    }

    private int findMin(int[] distance, Boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int x = -1;

        for (int i = 0; i < n; i++) {
            // checking to see if unexplored, finding min value
            if (visited[i] == false && distance[i] <= min) {
                min = distance[i];
                x = i;
            }
        }
        return x;
    }
        
}
