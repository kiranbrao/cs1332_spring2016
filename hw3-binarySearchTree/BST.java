import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
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
            root = new BSTNode<T>(data);
        } else {
            add(data, root);
        }
        size++;
    }

    /**
     * Recursively adds a node to the BST using conditionals to correctly
     * place the new node and its corresponding data value
     *
     * @param data the data to add to the tree
     * @param node the node to which the new node will be added
     */
    private void add(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<T>(data));
            } else {
                add(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<T>(data));
            } else {
                add(data, node.getRight());
            }
        } else {
            size--;
        }
    }

    /*
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
        if (data.compareTo(root.getData()) == 0) {
            T returnVal = root.getData();
            if ((root.getRight() == null) && (root.getLeft() == null)) {
                root = null;
            } else if ((root.getRight() == null) || (root.getLeft() == null)) {
                if (root.getRight() == null) {
                    root = root.getLeft();
                } else {
                    root = root.getRight();
                }
            } else {
                BSTNode<T> successor = root.getRight();
                if (successor.getLeft() == null) {
                    successor.setLeft(root.getLeft());
                    root = successor;
                    //node.getLeft().setRight(successor.getRight());
                } else {
                    while (successor.getLeft().getLeft() != null) {
                        successor = successor.getLeft();
                    }
                    BSTNode<T> beforeSuccessor = successor;
                    successor = successor.getLeft();
                    root = successor;
                    beforeSuccessor.setLeft(successor.getRight());
                }
            }
            size--;
            return returnVal;
        } else {
            T returnT = remove(data, root);
            size--;
            return returnT;
        }
    }

    /**
     * Recursively removes a node to the BST using conditionals to correctly
     * identify the node being removed and restructure the tree
     *
     * @param data the data to be removed from BST
     * @param node the node that is being examined for removal
     * @return the data of the node being removed from the BST
     */
    /*
    private T remove(T data, BSTNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("This value"
                    + " is not in the tree");
        }
        if ((node.getLeft() != null)
                && (data.compareTo(node.getLeft().getData()) == 0)) {
            T returnVal = node.getLeft().getData();
            if ((node.getLeft().getRight() == null)
                    && (node.getLeft().getLeft() == null)) {
                node.setLeft(null);
            } else if ((node.getLeft().getRight() == null)
                    || (node.getLeft().getLeft() == null)) {
                if (node.getLeft().getRight() == null) {
                    node.setLeft(node.getLeft().getLeft());
                } else {
                    node.setLeft(node.getLeft().getRight());
                }
            } else {
                BSTNode<T> successor = node.getLeft().getRight();
                if (successor.getLeft() == null) {
                    successor.setLeft(node.getLeft().getLeft());
                    node.setLeft(successor);
                    node.getLeft().setRight(successor.getRight());
                } else {
                    while (successor.getLeft().getLeft() != null) {
                        successor = successor.getLeft();
                    }
                    BSTNode<T> beforeSuccessor = successor;
                    successor = successor.getLeft();
                    beforeSuccessor.setLeft(successor.getRight());
                    successor.setLeft(node.getLeft().getLeft());
                    successor.setRight(node.getLeft().getRight());
                    node.setLeft(successor);
                }
            }
            return returnVal;
        } else if ((node.getRight() != null)
                && (data.compareTo(node.getRight().getData()) == 0)) {
            T returnVal = node.getRight().getData();
            if ((node.getRight().getRight() == null)
                    && (node.getRight().getLeft() == null)) {
                node.setRight(null);
            } else if ((node.getRight().getRight() == null)
                    || (node.getRight().getLeft() == null)) {
                if (node.getRight().getRight() == null) {
                    node.setRight(node.getRight().getLeft());
                } else {
                    node.setRight(node.getRight().getRight());
                }
            } else {
                BSTNode<T> successor = node.getRight().getRight();
                if (successor.getLeft() == null) {
                    successor.setLeft(node.getRight().getLeft());
                    node.setRight(successor);
                    node.getLeft().setRight(successor.getRight());
                } else {
                    while (successor.getLeft().getLeft() != null) {
                        successor = successor.getLeft();
                    }
                    BSTNode<T> beforeSuccessor = successor;
                    successor = successor.getLeft();
                    beforeSuccessor.setLeft(successor.getRight());
                    successor.setLeft(node.getRight().getLeft());
                    successor.setRight(node.getRight().getRight());
                    node.setRight(successor);
                }
            }
            return returnVal;
        } else if (data.compareTo(node.getData()) < 0) {
            return remove(data, node.getLeft());
        } else {
            return remove(data, node.getRight());
        }
    }
*/

    /*@Override
    public T remove(T data) {
        if (data == null) {
            throw new java.lang
                    .IllegalArgumentException("Data cannot be null.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("This value"
                    + " is not in the tree");
        }
        BSTNode<T> returnNode = new BSTNode<T>(null);
        root = remove(data, root, returnNode);
        if (returnNode.getData() == null) {
            throw new java.util.NoSuchElementException("This value"
                    + " is not in the tree");
        }
        return returnNode.getData();
    }

    private BSTNode<T> remove(T data, BSTNode<T> node, BSTNode<T> returnNode) {
        if (data.compareTo(node.getData()) == 0) {
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
                BSTNode<T> successor = node.getRight();
                if (successor.getLeft() == null) {
                    successor.setLeft(node.getLeft());
                } else {
                    while (successor.getLeft().getLeft() != null) {
                        successor = successor.getLeft();
                    }
                    BSTNode<T> beforeSuccessor = successor;
                    successor = successor.getLeft();
                    beforeSuccessor.setLeft(successor.getRight());
                    successor.setLeft(node.getLeft());
                    successor.setRight(node.getRight());
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
        return node;
    } */

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
        BSTNode<T> returnNode = new BSTNode<T>(null);
        root = remove(data, root, returnNode);
        if (returnNode.getData() == null) {
            throw new java.util.NoSuchElementException("This value"
                    + " is not in the tree");
        }
        return returnNode.getData();
    }

    private BSTNode<T> remove(T data, BSTNode<T> node, BSTNode<T> returnNode) {
        if (data.compareTo(node.getData()) == 0) {
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
                BSTNode<T> successor;
                BSTNode<T> rightChild = node.getRight();
                if (rightChild.getLeft() == null) {
                    successor = rightChild;
                    successor.setLeft(node.getLeft());
                } else {
                    successor = new BSTNode<>(null);
                    rightChild = getSuccessor(rightChild, successor);
                    successor.setRight(rightChild);
                    successor.setLeft(node.getLeft());
                }
                return successor;
                /*AVLNode<T> successor = node.getRight();
                if (successor.getLeft() == null) {
                    successor.setLeft(node.getLeft());
                } else {
                    //int counter = 0;
                    while (successor.getLeft().getLeft() != null) {
                        successor.setHeight(successor.getHeight() - 1);
                        //successor.setBalanceFactor(successor.getLeft().getHeight() - successor.getRight().getHeight());
                        successor = successor.getLeft();
                        //counter++;
                    }
                    AVLNode<T> beforeSuccessor = successor;
                    successor = successor.getLeft();
                    beforeSuccessor.setLeft(successor.getRight());
                    beforeSuccessor.setHeight(Math.max(height(beforeSuccessor.getLeft()), height(beforeSuccessor.getRight())) + 1);
                    beforeSuccessor.setBalanceFactor(height(beforeSuccessor.getLeft()) - height(beforeSuccessor.getRight()));
                    //beforeSuccessor.setBalanceFactor(0);
                    successor.setLeft(node.getLeft());
                    successor.setRight(node.getRight());
                    successor.setHeight(node.getHeight() - 1);
                    successor.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
                }
                return successor; */
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
        return node;
    }

    private BSTNode<T> getSuccessor(BSTNode<T> node, BSTNode<T> successor) {
        if (node.getLeft() != null) {
            node.setLeft(getSuccessor(node.getLeft(), successor));
        } else {
            successor.setData(node.getData());
            return node.getRight();
        }
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
     * Recursively search the BST and retrieve the data value of the node
     * that matches the input data
     *
     * @param data the data that is searched for in the BST
     * @param node the node that is being examined for the data
     * @return the data of the node found in the BST
     */
    private T get(T data, BSTNode<T> node) {
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
     * Recursively search the BST to determine if there is a data
     * value of the node that matches the input data
     *
     * @param data the data that is searched for in the BST
     * @param node the node that is being examined for the data
     * @return whether or not the data is contained in that node
     */
    private boolean contains(T data, BSTNode<T> node) {
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
     * Recursively traverse the BST starting at an initial node
     * in order to add data using preorder
     *
     * @param node the node at which the traversal begins
     * @param returnList list of the data from the BST using preorder
     */
    private List<T> preorder(BSTNode<T> node, List<T> returnList) {
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
     * Recursively traverse the BST starting at an initial node
     * in order to add data using postorder
     *
     * @param node the node at which the traversal begins
     * @param returnList list of the data from the BST using postorder
     */
    private List<T> postorder(BSTNode<T> node, List<T> returnList) {
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
     * Recursively traverse the BST starting at an initial node
     * in order to add data using inorder
     *
     * @param node the node at which the traversal begins
     * @param returnList list of the data from the BST using inorder
     */
    private List<T> inorder(BSTNode<T> node, List<T> returnList) {
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
        LinkedList<BSTNode<T>> linkedList = new LinkedList<BSTNode<T>>();
        linkedList.addLast(root);
        while (!(linkedList.isEmpty())) {
            BSTNode<T> removedNode = linkedList.removeFirst();
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
        if (root == null) {
            return -1;
        }
        return height(root);
    }

    /**
     * Recursively determines the height of a given node in the BST
     *
     * @param node the node whose height is determined
     * @return an int representing the height of the node
     */
    private int height(BSTNode<T> node) {
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            return 0;
        } else if ((node.getLeft() == null) || (node.getRight() == null)) {
            if (node.getLeft() == null) {
                return (height(node.getRight()) + 1);
            } else {
                return (height(node.getLeft()) + 1);
            }
        } else {
            return (Math.max(height(node.getRight()), height(node.getLeft()))
                    + 1);
        }
    }

    /**
     * Compares two BSTs and checks to see if the trees are the same.  If
     * the trees have the same data in a different arrangement, this method
     * should return false.  This will only return true if the tree is in the
     * exact same arrangement as the other tree.
     *
     * You may assume that you won't get a BST with a different generic type.
     * For example, if this BST holds Strings, then you will not get as an input
     * a BST that holds Integers.
     * 
     * Be sure to also implement the other general checks that .equals() should
     * check as well.
     *
     * Should have a running time of O(n).
     * 
     * @param other the Object we are comparing this BST to
     * @return true if other is equal to this BST, false otherwise.
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BST)) {
            return false;
        }
        BST<T> otherTree = (BST<T>) other;
        return equals(root, otherTree.root);
    }

    /**
     * Recursively traverses through BST to determine if nodes are equal
     *
     * @param node the first node of comparison
     * @param otherNode the second node that is being compared to the first
     * @return whether or not the nodes and all children node
     * of the nodes are equal
     */
    private boolean equals(BSTNode<T> node, BSTNode<T> otherNode) {
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
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
