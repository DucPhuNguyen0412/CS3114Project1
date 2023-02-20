package prj1;

/**
 * In this class, we implement the d-ary min-heap data structure
 * 
 * @author Skylar Mayfield
 * @author 
 *
 */
public class MinHeap {
    // The parameter d in the d-ary min-heap
    private int d;

    // The array representation of your min-heap (It is not required to use this)
    private HeapNode[] nodes;

    /**
     * Constructor
     * 
     * @param n:
     *            maximum number of elements in the min-heap at each time
     * @param d:
     *            parameter d in the d-ary min-heap
     */
    public MinHeap(int n, int d) {
        d = this.d;
        nodes = new HeapNode[n];
    }


    /**
     * This method inserts a new element with "id" and "value" into the min-heap
     * 
     * @param id
     * @param value
     */
    public void insert(int id, int value) {
        int index = nodes.length; //getting the # of values in the list
        int position = index-1;
        nodes[position] = new HeapNode(id, value); // inserting the node as the last node;
        int x = Math.abs(((position+d-2)/d) - 1);
        while (value < nodes[(position+d-2)/d].getValue()) { // if node is less than parent node
            HeapNode temp = nodes[(position+d-2)/d];     // placeholder for the parent
            nodes[(position+d-2)/d] = nodes[position];     // swap position
            nodes[position] = temp;
            position = (position+d-2)/d;
        } 
        // If node is less than parent node, we swap it (in the array)
        // Keep doing so until heap order is preserved 
    }


    /**
     * This method extracts the min value of the heap
     * 
     * @return an array consisting of two integer elements: id of the minimum
     *         element and the value of the minimum element
     * 
     *         So for example, if the minimum element has id = 5 and value = 1,
     *         you should return the array [5, 1]
     */
    public int[] extractMin() {
        if (nodes[0] != null) {
            int[] min = new int[2];
            min[0] = nodes[0].getId();
            min[1] = nodes[0].getValue();
            return min;
        }
        return null;
    }


    /**
     * This method takes an id and a new value newValue for the corresponding
     * node, and updates the data structure accordingly
     * 
     * @param id
     * @param newValue
     */
    public void decreaseKey(int id, int newValue) {
        //find ID of the node and find pos based on that ID
        //decrase key
    }


    /**
     * This method returns the array representation of heap
     * 
     * @return the array representation of heap
     */
    public int[] getHeap() {
        // TODO complete
        return null;
    }


    /**
     * the toString method that returns a string with the values of the heap in
     * the array representation.
     * This method can help you find the issues of your code when you want to
     * debug.
     * 
     * @return string form of the array representation of heap
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.nodes.length; i++) {
            if (nodes[i] != null) {
                sb.append(nodes[i].getValue());
                sb.append(' ');
            }
        }
        return sb.toString();
    }

}
