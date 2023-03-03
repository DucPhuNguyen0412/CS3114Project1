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
    // instance variables
    private int n; // number of vertices in the graph
    private int[][] graph; // adjacency matrix representation of the graph

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
            System.arraycopy(edges[i], 0, graph[i], 0, 3);
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
        // initialize the distance array, the visited list, and the adjacency list
        int[] distance = new int[n]; // distance from source to each vertex
        ArrayList<Integer> visited = new ArrayList<>(); // list of visited vertices
        ArrayList<int[]> adj = new ArrayList<>(); // adjacency list of vertices

        // set all distances to infinity except for the source vertex
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source - 1] = 0;

        // add the source vertex to the adjacency list
        adj.add(new int[]{source, 0});

        // loop until all vertices have been visited or there are no more adjacent vertices
        while (visited.size() != n && adj.size() != 0) {
            // get the vertex with the minimum distance from the source
            int[] list = getMin(adj);
            int u = list[0];
            int d = list[1];

            // add the vertex to the visited list if it hasn't been visited already
            if (!visited.contains(u)) {
                visited.add(u);
            }

            // get the adjacent vertices of the current vertex and update their distances
            getAdjacent(u, d, visited, adj, distance);
        }

        // set distances to -1 for any vertices that were not visited
        for (int i = 0; i < n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }

        // return the distance array
        return distance;
    }

    // method that finds the adjacent vertices of a given vertex and updates their distances
    private void getAdjacent(int source, int w, ArrayList<Integer> visited, ArrayList<int[]> adj, int[] distance) {
        // loop through each edge in the graph
        for (int i = 0; i < n; i++) {
            // if the edge starts at the source vertex and the other vertex hasn't been visited
            if (graph[i][0] == source && !visited.contains(graph[i][1])) {
                int v = graph[i][1];
                // add the other vertex to the adjacency list and update its distance
                adj.add(new int[]{v, graph[i][2]});
                distance[v - 1] = Math.min(distance[v - 1], distance[source - 1] + graph[i][2]);
            }
            // if the edge ends at the source vertex and the other vertex hasn't been visited
            else if (graph[i][1] == source && !visited.contains(graph[i][0])) {
                int v = graph[i][0];
                // add the other vertex to the adjacency list and update its distance
                adj.add(new int[]{v, graph[i][2]});
                distance[v - 1] = Math.min(distance[v - 1], distance[source - 1] + graph[i][2]);
            }
        }
    }

    // method that finds
    private int[] getMin(ArrayList<int[]> adj) {
        int min = Integer.MAX_VALUE;
        int w = 0;
        int index = 0;
        
        // Loop through each integer array in the ArrayList
        for (int i = 0; i < adj.size(); i++) {
            int[] arr = adj.get(i);
            // If the second element of the array is less than the current minimum value,
            // update the minimum value, variable w and the index of the minimum value
            if (arr[1] < min) {
                min = arr[1];
                w = arr[0];
                index = i;
            }
        }
        
        // Remove the integer array with the minimum value from the ArrayList
        adj.remove(index);

        return new int[]{w, min};
    }
}