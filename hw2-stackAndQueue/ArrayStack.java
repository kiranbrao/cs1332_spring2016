/**
 * Your implementation of a Stack backed by an array.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {
    // Do not add any new instance variables!
    private T[] backingArray;
    private int size;

    /**
     * Construct a Stack with an initial capacity of {@code INITIAL_CAPACITY}.
     *
     * Use constructor chaining.
     */
    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct a Stack with the specified initial capacity of
     * {@code initialCapacity}.
     * @param initialCapacity Initial capacity of the backing array.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        backingArray = (T[]) new Object[initialCapacity];
    }

    @Override
    public void push(T item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Data cannot"
                    + " be null.");
        }
        if (size == backingArray.length) {
            T[] oldArray = backingArray;
            backingArray = (T[]) new Object[(size * 2)];
            for (int i = 0; i < oldArray.length; i++) {
                backingArray[i] = oldArray[i];
            }
        }
        backingArray[size] = item;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The stack is"
                    + " already empty.");
        }
        T returnItem = backingArray[(size - 1)];
        backingArray[(size - 1)] = null;
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
