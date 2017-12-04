/**
 * Your implementation of a Queue backed by an array.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {
    
    // Do not add instance variables.
    private T[] backingArray;
    private int size;
    private int front;
    private int back;

    /**
     * Construct a Queue with an initial capacity of {@code INITIAL_CAPACITY}.
     *
     * Use Constructor Chaining
     */
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct a Queue with the specified initial capacity of
     * {@code initialCapacity}.
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayQueue(int initialCapacity) {
        backingArray = (T[]) new Object[initialCapacity];
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Data cannot"
                    + " be null.");
        }
        if (size == backingArray.length) {
            T[] oldArray = backingArray;
            backingArray = (T[]) new Object[(size * 2)];
            if (front < back) {
                for (int i = front; i < oldArray.length; i++) {
                    backingArray[i] = oldArray[i];
                }
            } else {
                for (int i = front, j = 0; i < oldArray.length; i++, j++) {
                    backingArray[j] = oldArray[i];
                }
                for (int i = 0; i <= back; i++) {
                    backingArray[(i + (oldArray.length - front))] = oldArray[i];
                }
                front = 0;
                back = size;
            }
        }
        if ((back == backingArray.length) && (size < backingArray.length)) {
            back = 0;
        }
        backingArray[back] = item;
        back++;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The queue is"
                    + " already empty.");
        }
        if (front == backingArray.length) {
            front = 0;
        }
        T returnItem = backingArray[front];
        backingArray[front] = null;
        front++;
        size--;
        return returnItem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Used for testing your code.
     * DO NOT USE THIS METHOD!
     *
     * @return the backing array of this queue.
     */
    public Object[] getBackingArray() {
        return backingArray;
    }

}
