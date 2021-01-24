package s0600;

public class N0641DesignCircularDeque {
    Object[] elements;
    int head;
    int tail;
    int size;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public N0641DesignCircularDeque(int k) {
        elements = new Object[k];
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        if (isEmpty()) return insertLast(value);

        elements[head = (head - 1 + elements.length) % elements.length] = value;
        size++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;

        elements[tail] = value;
        tail = (tail + 1 + elements.length) % elements.length;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;

        head = (head + 1 + elements.length) % elements.length;
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;

        tail = (tail - 1 + elements.length) % elements.length;
        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) return -1;

        return (int) elements[head];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) return -1;

        return (int) elements[(tail - 1 + elements.length) % elements.length];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == elements.length;
    }
}
