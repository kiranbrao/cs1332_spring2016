/**
 * Your implementation of a max heap.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE}.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (size == (backingArray.length - 1)) {
            T[] oldBackingArray = backingArray;
            backingArray = (T[]) new Comparable[(oldBackingArray.length) * 2];
            for (int i = 1; i < oldBackingArray.length; i++) {
                backingArray[i] = oldBackingArray[i];
            }
        }
        int index = size + 1;
        backingArray[index] = item;
        size++;
        if (size > 1) {
            upHeap(index);
        }
    }

    /**
     * Restructures the heap by recursively comparing the item at
     * the inputted index to its parent node, then swaps nodes if necessary
     * to maintain the integrity of the heap.
     *
     * @param index the item to be added to the heap
     */
    private void upHeap(int index) {
        if (index > 1) {
            if ((index % 2) == 0) {
                if (backingArray[index]
                        .compareTo(backingArray[index / 2]) > 0) {
                    T oldIndexData = backingArray[index];
                    backingArray[index] = backingArray[index / 2];
                    backingArray[index / 2] = oldIndexData;
                    upHeap(index / 2);
                }
            } else if ((index % 2) == 1) {
                if (backingArray[index]
                        .compareTo(backingArray[(index - 1) / 2]) > 0) {
                    T oldIndexData = backingArray[index];
                    backingArray[index] = backingArray[(index - 1) / 2];
                    backingArray[(index - 1) / 2] = oldIndexData;
                    upHeap((index - 1) / 2);
                }
            }
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The heap"
                    + " is already empty");
        }
        T returnItem = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        downHeap(1);
        size--;
        return returnItem;
    }

    /**
     * Restructures the heap by recursively comparing the item at
     * the inputted index to its children nodes, then swaps nodes if necessary
     * to maintain the integrity of the heap.
     *
     * @param index the item to be added to the heap
     */
    private void downHeap(int index) {
        if (((2 * index) < size) && (((2 * index) + 1) < size)) {
            if ((backingArray[(2 * index)] != null)) {
                if (backingArray[(2 * index) + 1] == null) {
                    if (backingArray[index]
                            .compareTo(backingArray[(2 * index)]) < 0) {
                        T oldIndexData = backingArray[(2 * index)];
                        backingArray[(2 * index)] = backingArray[index];
                        backingArray[index] = oldIndexData;
                        downHeap((2 * index));
                    }
                } else {
                    if ((backingArray[index]
                            .compareTo(backingArray[(2 * index)]) < 0)
                            || (backingArray[index]
                            .compareTo(backingArray[(2 * index) + 1]) < 0)) {
                        if (backingArray[(2 * index)]
                                .compareTo(backingArray[(2 * index) + 1]) > 0) {
                            T oldIndexData = backingArray[(2 * index)];
                            backingArray[(2 * index)] = backingArray[index];
                            backingArray[index] = oldIndexData;
                            downHeap((2 * index));
                        } else {
                            T oldIndexData = backingArray[(2 * index) + 1];
                            backingArray[(2 * index) + 1] = backingArray[index];
                            backingArray[index] = oldIndexData;
                            downHeap((2 * index) + 1);
                        }
                    }
                }
            }
        } else if (((2 * index) < size)) {
            if ((backingArray[(2 * index)] != null)) {
                if (backingArray[(2 * index) + 1] == null) {
                    if (backingArray[index]
                            .compareTo(backingArray[(2 * index)]) < 0) {
                        T oldIndexData = backingArray[(2 * index)];
                        backingArray[(2 * index)] = backingArray[index];
                        backingArray[index] = oldIndexData;
                        downHeap((2 * index));
                    }
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
