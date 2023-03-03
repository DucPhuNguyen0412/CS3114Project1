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
        while (value < nodes[(position-1)/d].getValue()) { // if node is less than parent node
            HeapNode temp = nodes[(position-1)/d];     // placeholder for the parent
            nodes[(position-1)/d] = nodes[position];     // swap position
            nodes[position] = temp;
            position = (position-1)/d;
            if(position == 0){
                break;
            }
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
                heapifyUp(i);
                break;
            }
        }
    }

    /**
     * Ensuring that the heap peoperty is satisfied at the given index and all indices above it,
     * if not, "bubbles up" the indices that have smaller values.
     * 
     * @param i The index to heapify upwards from
     */
    private void heapifyUp(int i) {
        while (i > 0 && nodes[i].getValue() < nodes[(i - 1) / d].getValue()) {
            int parent = (i - 1) / d;
            HeapNode temp = nodes[i];
            nodes[i] = nodes[parent];
            nodes[parent] = temp;
            i = parent;
        }
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

        int firstChild = d * i + 1;
        int lastChild = Math.min(firstChild + d - 1, size - 1);

        // Check the values of the children and their descendants
        for (int j = firstChild; j <= lastChild; j++) {
            if (nodes[j].getValue() < nodes[minVal].getValue()) {
                minVal = j;
            }
        }

        // Swap nodes if necessary
        if (minVal != i) {
            HeapNode temp = nodes[i];
            nodes[i] = nodes[minVal];
            nodes[minVal] = temp;
            heapifyDown(minVal);
        }
    }
}