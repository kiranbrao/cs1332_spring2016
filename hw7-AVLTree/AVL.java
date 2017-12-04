import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Kiran Rao
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data cannot be"
                    + " null.");
        }
        for (T datum : data) {
            add(datum);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("Data cannot be null.");
        }
        if (root == null) {
            root = new AVLNode<T>(data);
        } else {
            root = add(data, root);
        }
        size++;
    }

    /**
     * Recursively adds a node to the AVL using conditionals to correctly
     * place the new node and its corresponding data value
     *
     * @param data the data to add to the tree
     * @param node the node to which the new node will be added
     * @return the node(s) altered by the addition
     */
    private AVLNode<T> add(T data, AVLNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new AVLNode<T>(data));
            } else {
                node.setLeft(add(data, node.getLeft()));
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new AVLNode<T>(data));
            } else {
                node.setRight(add(data, node.getRight()));
            }
        } else {
            size--;
        }
        node.setHeight(Math.max(height(node.getLeft()),
                height(node.getRight())) + 1);
        node.setBalanceFactor(height(node.getLeft()) - height(node.getRight()));
        node = rebalance(node);
        return node;
    }

    /**
     * Retrieves the height of a given node.
     * If the node is null, the height will be -1.
     *
     * @param node the node whose height will be calculated
     * @return the height of the given node
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }

    /**
     * Checks to see if a node needs to be rebalanced by assessing
     * its balance factor. If the node needs to be rebalanced,
     * the proper rotation method is called in order to rebalance the node
     *
     * @param node the node that will be checked for rebalance
     * @return a balanced version of the node and its children
     */
    private AVLNode<T> rebalance(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() == 1) {
                node = leftRightRotation(node);
                node.setHeight(node.getHeight() + 1);
                node.getLeft().setHeight(node.getHeight() - 1);
                node.getRight().setHeight(node.getHeight() - 1);
                node.setBalanceFactor(height(node.getLeft())
                        - height(node.getRight()));
                node.getLeft().setBalanceFactor(height(node.getLeft()
                        .getLeft()) - height(node.getLeft().getRight()));
                node.getRight().setBalanceFactor(height(node.getRight()
                        .getLeft()) - height(node.getRight().getRight()));
            } else {
                node = leftRotation(node);
                node.getLeft().setHeight(node.getHeight() - 1);
                node.setBalanceFactor(height(node.getLeft())
                        - height(node.getRight()));
                node.getLeft().setBalanceFactor(height(node.getLeft()
                        .getLeft()) - height(node.getLeft().getRight()));
                if (node.getRight() != null) {
                    node.getRight().setBalanceFactor(height(node.getRight()
                            .getLeft()) - height(node.getRight().getRight()));
                }
            }
        }
        if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() == -1) {
                node = rightLeftRotation(node);
                node.setHeight(node.getHeight() + 1);
                node.getLeft().setHeight(node.getHeight() - 1);
                node.getRight().setHeight(node.getHeight() - 1);
                node.setBalanceFactor(height(node.getLeft())
                        - height(node.getRight()));
                node.getLeft().setBalanceFactor(height(node.getLeft()
                        .getLeft()) - height(node.getLeft().getRight()));
                node.getRight().setBalanceFactor(height(node.getRight()
                        .getLeft()) - height(node.getRight().getRight()));
            } else {
                node = rightRotation(node);
                node.getRight().setHeight(node.getHeight() - 1);
                node.setBalanceFactor(height(node.getLeft())
                        - height(node.getRight()));
                if (node.getLeft() != null) {
                    node.getLeft().setBalanceFactor(height(node.getLeft()
                            .getLeft()) - height(node.getLeft().getRight()));
                }
                node.getRight().setBalanceFactor(height(node.getRight()
                        .getLeft()) - height(node.getRight().getRight()));
            }
        }
        return node;
    }

    /**
     * Performs a left rotation on a given node and its children
     *
     * @param node the node on which the left rotation will be performed
     * @return a version of the node and its children after
     * the left rotation is performed
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> currentNode = node;
        AVLNode<T> rightChild = node.getRight();
        AVLNode<T> rightLeftChild = node.getRight().getLeft();
        currentNode.setRight(rightLeftChild);
        node = rightChild;
        node.setLeft(currentNode);
        return node;
    }

    /**
     * Performs a right rotation on a given node and its children
     *
     * @param node the node on which the right rotation will be performed
     * @return a version of the node and its children after
     * the right rotation is performed
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> currentNode = node;
        AVLNode<T> leftChild = node.getLeft();
        AVLNode<T> leftRightChild = node.getLeft().getRight();
        currentNode.setLeft(leftRightChild);
        node = leftChild;
        node.setRight(currentNode);
        return node;
    }

    /**
     * Performs a left rotation, then a right rotation
     * on a given node and its children
     *
     * @param node the node on which the left-right rotation will be performed
     * @return a version of the node and its children after
     * the left-right rotation is performed
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        AVLNode<T> rightChild = node.getRight();
        AVLNode<T> rightLeftChild = node.getRight().getLeft();
        AVLNode<T> rightLeftRightChild = node.getRight().getLeft().getRight();
        rightChild.setLeft(rightLeftRightChild);
        rightLeftChild.setRight(rightChild);
        node.setRight(rightLeftChild);
        node = leftRotation(node);
        return node;
    }

    /**
     * Performs a right rotation, then a left rotation
     * on a given node and its children
     *
     * @param node the node on which the right-left rotation will be performed
     * @return a version of the node and its children after
     * the right-left rotation is performed
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        AVLNode<T> leftChild = node.getLeft();
        AVLNode<T> leftRightChild = node.getLeft().getRight();
        AVLNode<T> leftRightLeftChild = node.getLeft().getRight().getLeft();
        leftChild.setRight(leftRightLeftChild);
        leftRightChild.setLeft(leftChild);
        node.setLeft(leftRightChild);
        node = rightRotation(node);
        return node;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("Data cannot be null.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("This value"
                    + " is not in the tree");
        }
        AVLNode<T> returnNode = new AVLNode<T>(null);
        root = remove(data, root, returnNode);
        if (returnNode.getData() == null) {
            throw new java.util.NoSuchElementException("This value"
                    + " is not in the tree");
        }
        return returnNode.getData();
    }

    /**
     * Recursively removes a node from the AVL using conditionals to correctly
     * identify the node being removed and restructure the tree
     *
     * @param data the data to be removed from AVL
     * @param node the node that is being examined for removal
     * @param returnNode the node that is set equal to
     * the node being removed from the AVL
     * @return a node containing the data being removed from the AVL
     */
    private AVLNode<T> remove(T data, AVLNode<T> node, AVLNode<T> returnNode) {
        if (data.equals(node.getData())) {
            returnNode.setData(node.getData());
            size--;
            if ((node.getRight() == null) && (node.getLeft() == null)) {
                return null;
            } else if ((node.getRight() == null) || (node.getLeft() == null)) {
                if (node.getRight() == null) {
                    return node.getLeft();
                } else {
                    return node.getRight();
                }
            } else {
                AVLNode<T> successor;
                AVLNode<T> rightChild = node.getRight();
                if (rightChild.getLeft() == null) {
                    successor = rightChild;
                    successor.setLeft(node.getLeft());
                    successor.setHeight(Math.max(height(successor.getLeft()),
                            height(successor.getRight())) + 1);
                    successor.setBalanceFactor(height(successor.getLeft())
                            - height(successor.getRight()));
                    successor = rebalance(successor);
                } else {
                    successor = new AVLNode<>(null);
                    rightChild = getSuccessor(rightChild, successor);
                    successor.setRight(rightChild);
                    successor.setLeft(node.getLeft());
                    successor.setHeight(Math.max(height(successor.getLeft()),
                            height(successor.getRight())) + 1);
                    successor.setBalanceFactor(height(successor.getLeft())
                            - height(successor.getRight()));
                }
                return successor;
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                node.setLeft(remove(data, node.getLeft(), returnNode));
            }
        } else {
            if (node.getRight() != null) {
                node.setRight(remove(data, node.getRight(), returnNode));
            }
        }
        node.setHeight(Math.max(height(node.getLeft()),
                height(node.getRight())) + 1);
        node.setBalanceFactor(height(node.getLeft()) - height(node.getRight()));
        node = rebalance(node);
        return node;
    }

    /**
     * Recursively identifies the successor of the node
     * being removed from the AVL
     *
     * @param node the right child of the node that is being
     * examined for a successor
     * @param successor the node that is set equal to
     * the successor of the first parameter node
     * @return the right child of the node being removed,
     * modified without the successor
     */
    private AVLNode<T> getSuccessor(AVLNode<T> node, AVLNode<T> successor) {
        if (node.getLeft() != null) {
            node.setLeft(getSuccessor(node.getLeft(), successor));
        } else {
            successor.setData(node.getData());
            return node.getRight();
        }
        node.setHeight(Math.max(height(node.getLeft()),
                height(node.getRight())) + 1);
        node.setBalanceFactor(height(node.getLeft()) - height(node.getRight()));
        node = rebalance(node);
        return node;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("Data cannot be null.");
        }
        return get(data, root);
    }

    /**
     * Recursively search the AVL and retrieve the data value of the node
     * that matches the input data
     *
     * @param data the data that is searched for in the AVL
     * @param node the node that is being examined for the data
     * @return the data of the node found in the AVL
     */
    private T get(T data, AVLNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("This value"
                    + " is not in the tree");
        }
        if (data.compareTo(node.getData()) == 0) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            return get(data, node.getLeft());
        } else {
            return get(data, node.getRight());
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("Data cannot be null.");
        }
        return contains(data, root);
    }

    /**
     * Recursively search the AVL to determine if there is a data
     * value of the node that matches the input data
     *
     * @param data the data that is searched for in the AVL
     * @param node the node that is being examined for the data
     * @return whether or not the data is contained in that node
     */
    private boolean contains(T data, AVLNode<T> node) {
        if (node == null) {
            return false;
        }
        if (data.compareTo(node.getData()) == 0) {
            return true;
        } else if (data.compareTo(node.getData()) < 0) {
            return contains(data, node.getLeft());
        } else {
            return contains(data, node.getRight());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> returnList = new ArrayList<T>();
        return preorder(root, returnList);
    }

    /**
     * Recursively traverse the AVL starting at an initial node
     * in order to add data using preorder
     *
     * @param node the node at which the traversal begins
     * @param returnList list of the data from the AVL using preorder
     * that grows as the tree is traversed
     * @return a list of data from the AVL using preorder
     */
    private List<T> preorder(AVLNode<T> node, List<T> returnList) {
        if (node != null) {
            returnList.add(node.getData());
            preorder(node.getLeft(), returnList);
            preorder(node.getRight(), returnList);
        }
        return returnList;
    }

    @Override
    public List<T> postorder() {
        List<T> returnList = new ArrayList<T>();
        return postorder(root, returnList);
    }

    /**
     * Recursively traverse the AVL starting at an initial node
     * in order to add data using postorder
     *
     * @param node the node at which the traversal begins
     * @param returnList list of the data from the AVL using postorder
     * that grows as the tree is traversed
     * @return a list of data from the AVL using postorder
     */
    private List<T> postorder(AVLNode<T> node, List<T> returnList) {
        if (node != null) {
            postorder(node.getLeft(), returnList);
            postorder(node.getRight(), returnList);
            returnList.add(node.getData());
        }
        return returnList;
    }

    @Override
    public List<T> inorder() {
        List<T> returnList = new ArrayList<T>();
        return inorder(root, returnList);
    }

    /**
     * Recursively traverse the AVL starting at an initial node
     * in order to add data using inorder
     *
     * @param node the node at which the traversal begins
     * @param returnList list of the data from the AVL using inorder
     * that grows as the tree is traversed
     * @return a list of data from the AVL using inorder
     */
    private List<T> inorder(AVLNode<T> node, List<T> returnList) {
        if (node != null) {
            inorder(node.getLeft(), returnList);
            returnList.add(node.getData());
            inorder(node.getRight(), returnList);
        }
        return returnList;
    }

    @Override
    public List<T> levelorder() {
        List<T> returnList = new ArrayList<T>();
        LinkedList<AVLNode<T>> linkedList = new LinkedList<AVLNode<T>>();
        linkedList.addLast(root);
        while (!(linkedList.isEmpty())) {
            AVLNode<T> removedNode = linkedList.removeFirst();
            if (removedNode != null) {
                returnList.add(removedNode.getData());
                linkedList.addLast(removedNode.getLeft());
                linkedList.addLast(removedNode.getRight());
            }
        }
        return returnList;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Compares two AVLs and checks to see if the trees are the same.  If
     * the trees have the same data in a different arrangement, this method
     * should return false.  This will only return true if the tree is in the
     * exact same arrangement as the other tree.
     *
     * You may assume that you won't get an AVL with a different generic type.
     * For example, if this AVL holds Strings, then you will not get as an input
     * an AVL that holds Integers.
     * 
     * Be sure to also implement the other general checks that .equals() should
     * check as well.
     * 
     * @param other the Object we are comparing this AVL to
     * @return true if other is equal to this AVL, false otherwise.
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AVL)) {
            return false;
        }
        AVL<T> otherTree = (AVL<T>) other;
        return equals(root, otherTree.root);
    }

    /**
     * Recursively traverses through AVL to determine if nodes are equal
     *
     * @param node the first node of comparison
     * @param otherNode the second node that is being compared to the first
     * @return whether or not the nodes and all children node
     * of the nodes are equal
     */
    private boolean equals(AVLNode<T> node, AVLNode<T> otherNode) {
        if (node == otherNode) {
            return true;
        }
        if ((node == null) || (otherNode == null)) {
            return false;
        }
        if (node.getData().compareTo(otherNode.getData()) == 0) {
            boolean leftTrue = equals(node.getLeft(), otherNode.getLeft());
            boolean rightTrue = equals(node.getRight(), otherNode.getRight());
            return (leftTrue && rightTrue);
        }
        return false;
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
