import java.util.Set;
import java.util.HashSet;
/**
 * Your implementation of a skip list.
 * 
 * @author Kiran Rao
 * @version 1.0
 */
public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    // Do not add any additional instance variables
    private CoinFlipper coinFlipper;
    private int size;
    private SkipListNode<T> head;

    /**
     * Constructs a SkipList object that stores data in ascending order.
     * When an item is inserted, the flipper is called until it returns a tails.
     * If, for an item, the flipper returns n heads, the corresponding node has
     * n + 1 levels.
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        head = new SkipListNode<T>(null, 1);
    }

    @Override
    public T first() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The skip "
                    + "list is empty.");
        }
        SkipListNode<T> current = head;
        while (current.getLevel() > 1) {
            current = current.getDown();
        }
        return current.getNext().getData();
    }

    @Override
    public T last() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The skip "
                    + "list is empty.");
        }
        return last(head);
    }

    /**
     * Finds and returns the last data item in the skip list using
     * iteration and recursion. The skip property is implemented
     * in order to avoid traversing the entire skip list
     *
     * @param current the node of the skip list being traversed
     * @return the data of the last item in the skip list
     */
    private T last(SkipListNode<T> current) {
        if (current.getNext() != null) {
            while (current.getNext() != null) {
                current = current.getNext();
            }
        }
        if (current.getLevel() > 1) {
            return last(current.getDown());
        }
        return current.getData();
    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        int headCounter = 0;
        if (size == 0) {
            head.setUp(new SkipListNode<T>(null,
                    (head.getLevel() + 1), null, null, null, head));
            head = head.getUp();
        }
        while (coinFlipper.flipCoin() != CoinFlipper.Coin.TAILS) {
            headCounter++;
            if (headCounter >= (head.getLevel() - 1)) {
                head.setUp(new SkipListNode<T>(null,
                        (head.getLevel() + 1), null, null, null, head));
                head = head.getUp();
            }
        }
        SkipListNode<T> headCounterNode
                = new SkipListNode<T>(null, headCounter);
        put(head.getDown(), data, headCounterNode);
        size++;
    }

    /**
     * Adds a new data item to the appropriate location in a skip list using
     * iteration and recursion. A coin is flipped and the number of heads
     * will determine the number of levels in which an added data item will
     * exist
     *
     * @param current the node of the skip list being traversed
     * @param data the data to be added to the skip list
     * @param headCounterNode a node being used to store the height counter
     * @return the skip list node added to a given level of the skip list
     */
    private SkipListNode<T> put(SkipListNode<T> current,
                                T data, SkipListNode<T> headCounterNode) {
        while ((current.getNext() != null)
                && (data.compareTo(current.getNext().getData()) == 1)) {
            current = current.getNext();
        }
        SkipListNode<T> previous;
        if (current.getLevel() > 1) {
            previous = put(current.getDown(), data, headCounterNode);
        } else {
            SkipListNode<T> addedNode = new SkipListNode<T>(data,
                    current.getLevel(), current, current.getNext(), null, null);
            if (current.getNext() != null) {
                current.getNext().setPrev(addedNode);
            }
            current.setNext(addedNode);
            return addedNode;
        }
        if ((current.getLevel() > 1) && (headCounterNode.getLevel() > 0)) {
            SkipListNode<T> addedNode = new SkipListNode<T>(data,
                    current.getLevel(), current, current.getNext(), null, null);
            addedNode.setDown(previous);
            if (previous != null) {
                previous.setUp(addedNode);
            }
            if (current.getNext() != null) {
                current.getNext().setPrev(addedNode);
            }
            current.setNext(addedNode);
            headCounterNode.setLevel(headCounterNode.getLevel() - 1);
            return addedNode;
        }
        return null;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        size--;
        SkipListNode<T> returnNode = new SkipListNode<T>(null, 1);
        remove(head.getDown(), data, returnNode);
        return returnNode.getData();
    }

    /**
     * Removes a data item from the skip list using iteration and recursion.
     * If an item is removed, it is removed on all levels. If a level is empty
     * it is completely removed.
     *
     * @param current the node of the skip list being traversed
     * @param data the data to be removed to the skip list
     * @param returnNode a node being used to store the return data
     * @throws java.util.NoSuchElementException if the item is not in skip list
     */
    private void remove(SkipListNode<T> current,
                        T data, SkipListNode<T> returnNode) {
        while ((current.getNext() != null)
                && (data.compareTo(current.getNext().getData()) == 1)) {
            current = current.getNext();
        }
        if (current.getLevel() > 1) {
            remove(current.getDown(), data, returnNode);
            if (current.getNext() != null) {
                if (data.equals(current.getNext().getData())) {
                    if (current.getNext().getNext() != null) {
                        current.getNext().getNext().setPrev(current);
                    }
                    current.setNext(current.getNext().getNext());

                }
            }
            if ((current.getData() == null) && (current.getNext() == null)) {
                current.getUp().setDown(current.getDown());
                if (current.getDown() != null) {
                    current.getDown().setUp(current.getUp());
                }
                head.setLevel(head.getLevel() - 1);
            }
        } else {
            if (current.getNext() != null) {
                if (data.equals(current.getNext().getData())) {
                    returnNode.setData(current.getNext().getData());
                    if (current.getNext().getNext() != null) {
                        current.getNext().getNext().setPrev(current);
                    }
                    current.setNext(current.getNext().getNext());
                }
            } else {
                size++;
                throw new java.util.NoSuchElementException("The data is not "
                        + "in the skip list.");
            }
            if ((current.getData() == null) && (current.getNext() == null)) {
                current.getUp().setDown(null);
                head.setLevel(head.getLevel() - 1);
            }
        }
    }



    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        return ((size != 0) && (contains(head.getDown(), data)));
    }

    /**
     * Traverses a skip list using iteration and recursion and determines
     * whether or not an inputted data item is contained in the skip list.
     * The skip property is employed such that if a data item is found on
     * a higher level of the skip list, traversal stops.
     *
     * @param current the node of the skip list being traversed
     * @param data the data being tested for existence in the skip list
     * @return whether or not the inputted data item is contained by
     * the skip list
     */
    private boolean contains(SkipListNode<T> current, T data) {
        while ((current.getNext() != null)
                && (data.compareTo(current.getNext().getData()) == 1)) {
            current = current.getNext();
        }
        if (current.getLevel() > 1) {
            return ((current.getNext() != null)
                    && (data.equals(current.getNext().getData()))
                    || (contains(current.getDown(), data)));
        } else {
            return ((current.getNext() != null)
                    && data.equals(current.getNext().getData()));
        }
    }


    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        if (size == 0) {
            throw new java.util.NoSuchElementException("The data is not "
                    + "in the skip list.");
        }
        return get(head.getDown(), data);
    }

    /**
     * Traverses a skip list using iteration and recursion and attempts to
     * retrieve an inputted data item from the skip list.
     * The skip property is employed such that if a data item is found on
     * a higher level of the skip list, traversal stops.
     *
     * @param current the node of the skip list being traversed
     * @param data the data being attempting to be retrieved
     * @return the data being retrieved from the skip list
     * @throws java.util.NoSuchElementException if the item is not in skip list
     */
    private T get(SkipListNode<T> current, T data) {
        while ((current.getNext() != null)
                && (data.compareTo(current.getNext().getData()) == 1)) {
            current = current.getNext();
        }
        if (current.getLevel() > 1) {
            if ((current.getNext() != null)
                    && (data.equals(current.getNext().getData()))) {
                return current.getNext().getData();
            } else {
                return get(current.getDown(), data);
            }
        } else {
            if ((current.getNext() != null)
                    && (data.equals(current.getNext().getData()))) {
                return current.getNext().getData();
            } else {
                throw new java.util.NoSuchElementException("The data is not "
                        + "in the skip list.");
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = new SkipListNode<T>(null, 1);
        size = 0;
    }

    @Override
    public Set<T> dataSet() {
        Set<T> returnSet = new HashSet<>();
        if (size > 0) {
            SkipListNode<T> current = head;
            while (current.getLevel() > 1) {
                current = current.getDown();
            }
            while (current.getNext() != null) {
                current = current.getNext();
                returnSet.add(current.getData());
            }
        }
        return returnSet;
    }

    @Override
    public SkipListNode<T> getHead() {
        return head;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("**********************\n");
        builder.append(String.format("SkipList (size = %d)\n", size()));
        SkipListNode<T> levelCurr = getHead();

        while (levelCurr != null) {
            SkipListNode<T> curr = levelCurr;
            int level = levelCurr.getLevel();
            builder.append(String.format("Level: %2d   ", level));

            while (curr != null) {
                builder.append(String.format("(%s)%s", curr.getData(),
                            curr.getNext() == null ? "\n" : ", "));
                curr = curr.getNext();
            }
            levelCurr = levelCurr.getDown();
        }
        builder.append("**********************\n");
        return builder.toString();
    }

}
