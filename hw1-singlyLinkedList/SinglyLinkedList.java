/**
 * Your implementation of a SinglyLinkedList
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
    public void addAtIndex(int index, T data) {
        if ((index < 0) || (index > size)) {
            throw new java.lang.IndexOutOfBoundsException("Please choose an"
                    + "index within the data structure.");
        }
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("The data entered is null.");
        }
        if (size == 0) {
            head = new LinkedListNode<T>(data);
            tail = head;
        } else if (index == 0) {
            head = new LinkedListNode<T>(data, head);
        } else {
            LinkedListNode<T> currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            LinkedListNode<T> nextAfterSet = currentNode.getNext();
            currentNode.setNext(new LinkedListNode<T>(data, nextAfterSet));
            if (nextAfterSet == null) {
                tail = currentNode.getNext();
            }
        }
        size++;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new java.lang.IndexOutOfBoundsException("This index is"
                    + " not within the linked list.");
        }
        if (index == 0) {
            return head.getData();
        } else if (index == (size - 1)) {
            return tail.getData();
        } else {
            LinkedListNode<T> currentNode = head;
            int i = 0;
            while (i < index) {
                currentNode = currentNode.getNext();
                i++;
            }
            return currentNode.getData();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new java.lang.IndexOutOfBoundsException("This index is"
                    + " not within the linked list.");
        }
        if (size == 1) {
            LinkedListNode<T> returnNode = head;
            head = null;
            tail = null;
            size--;
            return returnNode.getData();
        } else {
            if (index == 0) {

                LinkedListNode<T> returnNode = head;
                head = head.getNext();
                size--;
                return returnNode.getData();
            } else {
                LinkedListNode<T> currentNode = head;
                for (int i = 1; i < index; i++) {
                    currentNode = currentNode.getNext();
                }
                LinkedListNode<T> returnNode = currentNode.getNext();
                currentNode.setNext(returnNode.getNext());
                size--;
                if (returnNode.getNext() == null) {
                    tail = currentNode;
                }
                return returnNode.getData();
            }
        }
    }

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
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("The data entered is null.");
        }
        int foundData = 0;
        if (size == 0) {
            return false;
        } else if (size == 1) {
            if (head.getData().equals(data)) {
                head = null;
                tail = null;
                size--;
                return true;
            } else {
                return false;
            }
        } else {
            while ((!(head == null)) && (head.getData().equals(data))) {
                head = head.getNext();
                foundData++;
                size--;
            }
            if (head == null) {
                tail = null;
                return (foundData > 0);
            } else {
                LinkedListNode<T> currentNode = head;
                LinkedListNode<T> nextNode = currentNode.getNext();
                while (!(nextNode == null)) {
                    if (nextNode.getData().equals(data)) {
                        currentNode.setNext(nextNode.getNext());
                        if (nextNode.getNext() == null) {
                            tail = currentNode;
                        }
                        foundData++;
                        size--;
                    }
                    if (!(currentNode.getNext() == null)) {
                        currentNode = currentNode.getNext();
                    }
                    nextNode = currentNode.getNext();
                }
            }
            return (foundData > 0);
        }
    }


    @Override
    public Object[] toArray() {
        Object[] returnArray = new Object[size];
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            returnArray[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return returnArray;
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
    public void clear() {
        head = null;
        tail = null;
        size = 0;
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