package prj1;

import java.util.ArrayList;

/**
 * The implementation of Dijkstras shortest path algorithm by using a simple
 * linear search to find the unvisited node with the minimum distance estimate
 * 
 * @author Skylar Mayfield
 *         Phu Nguyen
 * @version 1.1
 */

public class DijkstrasWithoutHeap {
    private int n;
    private ArrayList<Integer[]>[] graph;

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
        this.n = n;
        int maxNode = 0;

        // Find the maximum node number
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            maxNode = Math.max(maxNode, Math.max(u, v));
        }

        // Initialize the graph array with the maximum node number
        this.graph = new ArrayList[maxNode + 1];

        // Initialize each adjacency list
        for (int i = 0; i <= maxNode; i++) {
            graph[i] = new ArrayList<Integer[]>();
        }

        // Populate adjacency list with edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            Integer[] edge1 = {v, w};
            graph[u].add(edge1);

            Integer[] edge2 = {u, w};
            graph[v].add(edge2);
        }
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
        // Initializing distance and visited arrays
        int[] distance = new int[n];
        Boolean[] visited = new Boolean[n];
        
        // Initializing all distances to maximum value and all nodes as unvisited
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        
        // Distance of source node to itself is 0
        // distance[source-1] = 0;
        distance[source - 1] = 0;
        int count = 0;
        
        // Looping through all nodes in the graph
        while (count < n) {
            // Finding the node with minimum distance that is not visited
            int min = findMin(distance, visited);
            
            // If the minimum distance is Integer.MAX_VALUE, then we can break out of the loop since
            // all remaining unvisited nodes are not connected to the source node
            if (distance[min] == Integer.MAX_VALUE) {
                break;
            }
        
            // Marking this node as visited
            visited[min] = true;
            count++;
                
            // Update the distances of the adjacent nodes if necessary
            for (Integer[] edge : graph[min]) {
                int neighbor = edge[0] - 1;
                int weight = edge[1];
                int altDistance = distance[min] + weight;
                if (!visited[neighbor] && altDistance < distance[neighbor]) {        
                    distance[neighbor] = altDistance;
                }
            }
        }

        //Returning the array of distances from the source node
        return distance;
    }
    
    
    // Finding the node with minimum distance that is not visited
    private int findMin(int[] distance, Boolean[] visited) {
        // Initialize minimum distance to infinity and the minimum distance node to -1
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        // Iterate over all nodes
        for (int i = 0; i < n; i++) {
            // Check if the node is unexplored and if its distance is less than the current minimum distance
            if (!visited[i] && distance[i] <= min) {
                // Update the minimum distance and minimum distance node
                min = distance[i];
                minIndex = i;
            }
        }

        // Return the index of the node with the minimum distance
        return minIndex;
    }
    
        
}
