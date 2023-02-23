package prj1;

/**
 * In this class, we implement the d-ary min-heap data structure
 * 
 * @author Skylar Mayfield
 *         Phu Nguyen
 *
 */
public class MinHeap {
    // The parameter d in the d-ary min-heap
    private int d;

    // The array representation of your min-heap (It is not required to use this)
    private HeapNode[] nodes;


    // The length of the array reprsentation of nodes.
    private int size;
    
    /**
     * Constructor
     * 
     * @param n:
     *            maximum number of elements in the min-heap at each time
     * @param d:
     *            parameter d in the d-ary min-heap
     */
    public MinHeap(int n, int d) {
        this.d = d;
        nodes = new HeapNode[n];
        size = 0;
    }


    /**
     * This method inserts a new element with "id" and "value" into the min-heap
     * 
     * @param id
     * @param value
     */
    public void insert(int id, int value) {
        int position = size;
        nodes[position] = new HeapNode(id, value); // inserting the node as the last node;
        while (value < nodes[position/d].getValue()) { // if node is less than parent node
            HeapNode temp = nodes[position/d];     // placeholder for the parent
            nodes[position/d] = nodes[position];     // swap position
            nodes[position] = temp;
            position = position/d;
        } 
        size++;
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
        if (size == 0) {
            // If the heap is empty, return null
            return null;
        } else {
            // Otherwise, extract the minimum element and update the heap
            int[] min = new int[2];
            min[0] = nodes[0].getId();
            min[1] = nodes[0].getValue();
            nodes[0] = nodes[size-1];
            nodes[size - 1] = null;
            size--;
            heapifyDown(0);
            return min;
        }
    }

    /**
     * This method takes an id and a new value newValue for the corresponding
     * node, and updates the data structure accordingly
     * 
     * @param id
     * @param newValue
     */
    public void decreaseKey(int id, int newValue) {
        for (int i = 0; i < nodes.length-1; i++) {
            if (nodes[i].getId() == id) {
                nodes[i].setValue(newValue);
                while (newValue < nodes[i/d].getValue()) { // if node is less than parent node
                    HeapNode temp = nodes[i/d];     // placeholder for the parent
                    nodes[i/d] = nodes[i];     // swap position
                    nodes[i] = temp;
                } 
            }
        }

        // need to preserve the order

        //find ID of the node and find pos based on that ID
        //decrase key
    }


    /**
     * This method returns the array representation of heap
     * 
     * @return the array representation of heap
     */
    public int[] getHeap() {
        int[] representation = new int[size];
        for (int i = 0; i < size; i++) {
            representation[i] = nodes[i].getValue();
        }
        return representation;
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

    /**
     * Ensuring that the heap peoperty is satisfied at the given index and all indices below it,
     * if not, "bubbles up" the indices that have smaller values.
     * 
     * @param i The index to heapify downwards from
     */
    private void heapifyDown(int i) {
        int minVal = i;

        // Check the values of the left and right children, if they exist
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && nodes[left].getValue() < nodes[minVal].getValue()) {
            minVal = left;
        }
        if (right < size && nodes[right].getValue() < nodes[minVal].getValue()) {
            minVal = right;
        }

        // If the minimum value is not at the current index, swap the nodes
        if (minVal != i) {
            swapNodes(i, minVal);
            // Recursively heapify downwards from the minimum value index
            heapifyDown(minVal);
        }
    }

    /**
     * Swaps the nodes at the given indices.
     * 
     * @param i The index of the first node to swap
     * @param j The index of the second node to swap
     */
    private void swapNodes(int i, int j) {
        HeapNode temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }

}