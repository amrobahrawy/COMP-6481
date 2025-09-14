

/**
 *  * -------------------------------------------------------

 * 	Assignment 3
 *	Programming Part - Smart Priority Queue
 *	Due Date: April 14 2024
 	 * -------------------------------------------------------

 * @author Amro Elbahrawy  ID: 40221760
 * Class of a priority queue whose implementation changes between a minHeap or a maxHeap based on its state.
 * SPQ can perform all the actions of an ordinary priority queue, with the addition of toggling its status.
 * 
 * <pre>
 * Example on how to use it:
 * 		SPQ(Provide the type using the Generics way) spq1 = new SPQ(Generic Typing)();
 * 		spq1.insert(0,4);
 * 		System.out.println(spq1.top());
 * 		System.out.println(spq1.isEmpty());
 * </pre>
 */
public class SPQ<T>{
	final int CAPACITY = 5;
	private Node[] heap;
	String heapType;
	int num_elements;
	
	/**
	 * Base constructor. Initializes the array with the initial capacity and sets the initial implementation to Min heap.
	 */
	public SPQ() {
		this.heap = new Node[CAPACITY]; // initial size
		this.num_elements = 0;
		this.heapType = new String("min");
		
	}
	
	/**
	 * Changes the implementation of the queue to either Min or a Max heap, based on its heapType variable.
	 */
    public void toggle() {
        if(state().equalsIgnoreCase("min")) {
        	System.out.println("The SPQ is currently a MIN heap. Converting it to a MAX heap: ");
        	this.heapType = "max";
        	for(int i = (this.num_elements/2)-1; i >= 0; i--)
        		convertToMax(i);
        	

        }
        else {
        	System.out.println("The SPQ is currently a MAX heap. Converting it to a MIN heap: ");
        	this.heapType = "min";

        	for(int i = (this.num_elements/2)-1; i >= 0; i--)
        		convertToMin(i);
        }
    }
    
    /**
     * Used in toggle() method to convert the queue to a Max heap implementation
     * @param index: the key of an entry.
     */
    private void convertToMax(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        if (left < this.num_elements && heap[left].key > heap[largest].key) {
            largest = left;
        }

        if (right < this.num_elements && heap[right].key > heap[largest].key) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            convertToMax(largest);
        }
    }
    
    /**
     * Used in toggle() method to convert the queue to a Min heap implementation
     * @param index: the key of an entry.
     */
    private void convertToMin(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;

        if (left < this.num_elements && heap[left].key < heap[smallest].key) {
        	smallest = left;
        }

        if (right < this.num_elements && heap[right].key < heap[smallest].key) {
        	smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            convertToMin(smallest);
        }
    }
	

	
	/**
	 * Internally used in restoreOrder(), converToMax(), and convertToMin() to quickly swap two entries
	 * @param key1: key of the first entry
	 * @param key2: key of the second entry
	 */
    private void swap(int key1, int key2) {
        Node temp = heap[key1];
        heap[key1] = heap[key2];
        heap[key2] = temp;
    	
    }
    
    /**
     * Removes the first entry in the queue and returns its key and value.
     * @return Key and value of the topmost entry, or a message stating that the queue is empty.
     */
    public String removeTop() {
        if (isEmpty()) {
            throw new IllegalStateException("The SPQ is currently empty.");
        }
        
        Node top = heap[0];
        heap[0] = heap[this.num_elements - 1];
        this.num_elements--;
        if (state().equalsIgnoreCase("min")) {
            convertToMin(0);
        } else {
            convertToMax(0);
        }
        return "Removing the first entry in the SPQ: " + top.toString();
    }
    
    /**
     * 
     * @param key of the entry to be added.
     * @param value of the entry to be added.
     * @return The key and value of the added entry.
     * 
     * If the queue is empty, then it is automatically extended by 50% of its current capacity.
     */
    public String insert(int key, T value) {
    	
    	if(this.num_elements == this.heap.length) {
    		System.out.println("The SPQ is currently full, extending it by 50% of the current size.");
    		extendArray();
        	System.out.println(size());

    	}
    	
    	Node entry = new Node(key, value);
        heap[this.num_elements] = entry;
        this.num_elements++;
        
        restoreOrder(this.num_elements - 1);
        
        return "Entry added: " + entry.toString();
    }
    
    /**
     * Internally used to increase the size of the queue by 50% in case the queue is full and we want to add a new entry.
     */
    private void extendArray() {
    	Node[] biggerHeap = new Node[this.heap.length + (this.heap.length/2)];
    	for(int i = 0; i < this.heap.length; i++) {
    		biggerHeap[i] = this.heap[i]; 
    	}
    	this.heap = biggerHeap;
    }
    
    /**
     * Internally used after removing or replacing keys of entries.
     * Restores the order of the queue based on the current state of the queue whether its a MAX or a MIN heap.
     * @param key: the key of an entry.
     */
    private void restoreOrder(int key) {
        if (state().equalsIgnoreCase("min")) {
            while (key > 0 && heap[(key-1)/2].key > heap[key].key) {
                swap((key-1)/2, key);
                key = (key-1)/2;
            }
        } else {
            while (key > 0 && heap[(key-1)/2].key < heap[key].key) {
                swap((key-1)/2, key);
                key = (key-1)/2;
            }
        }
    }
    
    /**
     * Shows the first entry in the queue without removing it.
     * @return The key and value of the first entry in the queue.
     */
    public String top() {
        if (isEmpty()) {
            return "The SPQ is currently empty.";
        }
        return "The first item in the SPQ is: " +  this.heap[0].toString();
    }
    
    /**
     * Removes a specific entry in the queue and restores the order of the queue based on its current state.
     * @param key: key of the entry to be removed.
     * @param value: value of the entry to be removed.
     * @return The key and value of the removed entry, or an error message if the given entry does not exist.
     */
    public String remove(int key, T value) {
        int index = -1;
        for (int i = 0; i < this.num_elements; i++) {
            if (heap[i].key == key && heap[i].value == value) {
                index = i;
                break;
            }
        }
        if (index == -1) {
        	return "There is no such entry in the queue with key " + key + " and value " + value;
        }
        Node removedEntry = heap[index];
        heap[index] = heap[this.num_elements - 1];
        this.num_elements--;
        if (state().equalsIgnoreCase("min")) {
            convertToMin(index);
        } else {
            convertToMax(index);
        }
        return "Entry found and removed. Entry information: " + removedEntry.toString();
    }
    
    /**
     * Replaces the value of the given entry with a new value.
     * Finds the entry with the given key and value first, then replaces its key and restores the heap order based on its current state.
     * @param key of the entry.
     * @param  value: old value of the entry.
     * @param newKey: The new key that replaces the old key for the entry.
     * @return The entry with its new key and value, or an error message in case the given entry does not exist in the entry.
     */
    public String replaceKey(int key,T value, int newKey) {
    	Node n = getNode(key, value);
    	if(n == null)
    		return "There is no such entry in the queue with the key " + key;
    	
   
        n.key = newKey;
        if (state().equalsIgnoreCase("min")) {
            restoreOrder(getNodeIndex(n.key));
        } else {
            convertToMin(getNodeIndex(n.key));
        }
        return "The old key: " + key + " was replaced with the new key: " + newKey + ". The SPQ was re-organized accordingly.";
    }
    
    /**
     * Replaces the value of the given entry with a new value.
     * Finds the entry with the given key and value first, then replaces its value.
     * @param key of the entry.
     * @param  value: old value of the entry.
     * @param newValue: The new value that replaces the old value for the entry.
     * @return The entry with its key and new value, or an error message in case the given entry does not exist in the entry.
     */
    public String replaceValue(int key , T value, T newValue) {
    	Node n = getNode(key, value);
    	if(n == null)
    		return "There is no such entry in the queue with the key " + key;
    	
    	
    	
        Object oldValue = n.value;
        n.value = newValue;
        return "The entry with key " + key + " had its value changed to " + newValue;
    }
    
    /**
     * Gets the queue entry based on key only. Used in the restoreOrder() method when removing or replacing keys.
     * @param Given key of the entry.
     * @return The entry if it exists in the queue, or NULL if it does not.
     */
    private Node getNode(int key) {
    	for(int i = 0; i < this.num_elements; i++) {
            if (heap[i].key == key) {
                return heap[i];
            }
    	}
    	return null;
    }
    
    /**
     * Method used to quickly access an entry in the queue for replaceKey() and replaceValue() methods.
     * @param Given key of the entry.
     * @param Given value of the entry.
     * @return The entry if it exists in the queue, or NULL if it does not.
     */
    private Node getNode(int key, T value) {
    	for(int i = 0; i < this.num_elements; i++) {
            if (heap[i].value == value && heap[i].key == key) {
                return heap[i];
            }
    	}
    	return null;
    }
    
    /**
     * 
     * @return The number of entries in the queue.
     */
	public String size() {
		return "The queue currently has " + this.num_elements + " entries.";
	}
	
	/**
	 * 
	 * @return The current state of the queue (Min or Max heap)
	 */
	public String state() {
		return this.heapType;
	}
	
	/**
	 * 
	 * @return true or false based on if the queue is empty or not.
	 */
	public boolean isEmpty() {
		return this.num_elements == 0;
	}
	
	
    
    private int getNodeIndex(int key) {
        for (int i = 0; i < this.num_elements; i++) {
            if (heap[i].key == key) {
                return i;
            }
        }
        return -1;
    }
    
    
	
    
	
	private class Node<T>{
		int key;
		T value;
		
		public Node(int key, T value) {
			this.key = key;
			this.value = value;
		}
		

		
		public String toString() {
			return "Key:" + this.key + " | Value:" + this.value + ".";
		}
		
	}
	
}
