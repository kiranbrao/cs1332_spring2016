/**
 * Your implementation of a SinglyLinkedList.  You may reuse your code from HW1,
 * but make sure to only include the methods in the interface given in this
 * assignment.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("The data entered is null.");
        }
        LinkedListNode<T> oldHead = head;
        head = new LinkedListNode<T>(data, oldHead);
        if (size == 0) {
            tail = head;
        }
        if (size == 1) {
            tail = oldHead;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("Data cannot be null.");
        }

        LinkedListNode<T> newTail = new LinkedListNode<T>(data);
        if (size == 0) {
            head = newTail;
            tail = newTail;
            size++;
        } else {
            tail.setNext(newTail);
            tail = newTail;
            size++;
        }
    }

    @Override
    public T removeFromFront() {
        LinkedListNode<T> returnNode = head;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            head = head.getNext();
            size--;
        }
        return returnNode.getData();
    }

    @Override
    public T removeFromBack() {
        LinkedListNode<T> returnNode = tail;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            LinkedListNode<T> currentNode = head;
            for (int i = 2; i < size; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(null);
            tail = currentNode;
            size--;
        }
        return returnNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}