/**
 * Your implementation of a Stack backed by a LinkedList.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class LinkedListStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    // Do not modify this variable.
    private LinkedListInterface<T> backingList;
    
    /**
     * Initialize the Stack.
     */
    public LinkedListStack() {
        backingList = new SinglyLinkedList<T>();
    }

    @Override
    public void push(T item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Data cannot"
                    + " be null.");
        }
        backingList.addToFront(item);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The stack is"
                    + " already empty.");
        }
        return backingList.removeFromFront();
    }

    @Override
    public int size() {
        return backingList.size();
    }

    @Override
    public boolean isEmpty() {
        return backingList.isEmpty();
    }
    
    /**
     * Used for testing your code.
     * DO NOT USE THIS METHOD!
     *
     * @return the backing list of this stack.
     */
    public LinkedListInterface<T> getBackingList() {
        return backingList;
    }

}
