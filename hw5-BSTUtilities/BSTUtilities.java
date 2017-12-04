/**
 * Class that implements various Utility methods on a BST.
 *
 * @author Kiran Rao
 * @version 1.4
 */
public class BSTUtilities {

    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode root;

    // FOR ALL METHODS BELOW (except add(), which is provided for you):
    //
    // You may NOT write any additional methods, public or private.
    //
    // You may NOT use loops or external data structures
    // (such as arrays, lists, or queues) of any kind
    //  (unless otherwise specified).
    //
    // You may NOT modify the root instance variable to point to
    // another node.
    //
    // You may NOT modify the tree in any way. You may NOT change
    // the method headers of any method (public or private).
    //
    // You MUST use proper constructor chaining for these constructors.
    // Failure to do so will result in significant penalties.

    /**
     * A no argument constructor that should initialize an empty BST.
     * Fill out this constructor USING CONSTRUCTOR CHAINING.
     * (Do not leave it blank.)
     */
    public BSTUtilities() {
        this(null, 0, 0);
    }

    /**
     * Initializes the BST with the data in the int array.
     *
     * Fill out this constructor USING CONSTRUCTOR CHAINING.
     *
     * @param nums The integers to add to the tree. This will never be null.
     */
    public BSTUtilities(int[] nums) {
        this(nums, 0, nums.length);
    }

    /**
     * Initializes the BST with the elements from indices 0 to end (excluding
     * end).
     *
     * Fill out this constructor USING CONSTRUCTOR CHAINING.
     *
     * @param nums The integers to add to the tree. This will never be null.
     * @param end The index to stop adding items from the array.
     * @throws ArrayIndexOutOfBoundsException if end > nums.length.
     */
    public BSTUtilities(int[] nums, int end) {
        this(nums, 0, end);
    }

    /**
     * Initializes the BST with the elements from indices start to end
     * (excluding end).
     *
     * You may use a loop in this constructor only.
     *
     * @param nums The integers to add to the tree. This will never be null.
     * @param start The index to start adding items from.
     * @param end The index to stop adding items.
     * @throws ArrayIndexOutOfBoundsException if
     *         start < 0 or end > nums.length.
     * @throws IllegalArgumentException if start > end.
     */
    public BSTUtilities(int[] nums, int start, int end) {
        if (nums != null) {
            if (start < 0) {
                throw new ArrayIndexOutOfBoundsException("Start index "
                        + "cannot be less than 0");
            }
            if (end > nums.length) {
                throw new ArrayIndexOutOfBoundsException("End must be smaller"
                       + " than number of array elements");
            }
            if (start > end) {
                throw new IllegalArgumentException("Start cannot"
                       +  " be greater than end");
            }
            for (int i = start; i < end; i++) {
                add(nums[i]);
            }
        }
    }

    /**
     * Adds the data item to the BST.
     *
     * This method has been implemented for you.  No need to change it.
     *
     * Duplicates are ignored.
     *
     * @param data Item to add to BST.
     */
    public void add(int data) {
        root = add(data, root);
    }

    /**
     * Private helper method to traverse the tree and place the new data in the
     * correct spot.
     *
     * This method has been implemented for you.  No need to change it.
     *
     * @param data the new data to be added
     * @param current the BSTNode in the tree currently under examination
     * @return the updated BSTNode for the current position in the tree
     */
    private BSTNode add(int data, BSTNode current) {
        if (current == null) {
            return new BSTNode(data);
        } else if (data < current.getData()) {
            current.setLeft(add(data, current.getLeft()));
        } else if (data > current.getData()) {
            current.setRight(add(data, current.getRight()));
        }
        return current;
    }

    // One last warning.
    // DO NOT CHANGE ANY OF THE METHOD HEADERS.
    // DO NOT ADD ANY METHODS, PUBLIC OR PRIVATE
    // Everything you will need is provided to you.

    /**
     * Adds all of the data in the tree.
     *
     * This method can and should be done in one line only.
     *
     * @return sum of data in the tree
     */
    public int dataSum() {
        return dataSum(root);
    }

    /**
     * Adds all of the data in the subtree.
     *
     * This is the helper method to be used for {@code dataSum()}.
     *
     * @param current The current node for this subtree.
     * @return sum of data in the subtree.
     */
    private int dataSum(BSTNode current) {
        if (current != null) {
            return current.getData() + dataSum(current.getLeft())
                    + dataSum(current.getRight());
        }
        return 0;
    }

    /**
     * Calculates the number of elements in the tree.
     *
     * This method can and should be done in one line only.
     *
     * @return size of the tree
     */
    public int size() {
        return size(root);
    }

    /**
     * The size of the subtree.
     *
     * This is the helper method to be used for {@code size()}.
     *
     * @param current The current node you're looking at.
     * @return The size of this subtree.
     */
    private int size(BSTNode current) {
        if (current != null) {
            return 1 + size(current.getLeft()) + size(current.getRight());
        }
        return 0;
    }

    /**
     * Returns a number calculated from the data in the tree, according to a
     * formula defined in the helper method's Javadoc comment.
     *
     * This method can and should be done in one line only.
     *
     * @return zigAdd value for this tree.
     */
    public int zigAdd() {
        return zigAdd(root);
    }

    /**
     * Returns a number calculated from the data for this subtree, according to
     * a formula.
     *
     * This is the helper method to be used for {@code zigAdd()}.
     *
     * The formula:
     *
     * current node's data + zigAdd(left subtree) - zigAdd(right subtree)
     *
     * @param current The current node you're looking at.
     * @return zigAdd for this subtree
     */
    private int zigAdd(BSTNode current) {
        if (current != null) {
            return current.getData() + zigAdd(current.getLeft())
                    - zigAdd(current.getRight());
        }
        return 0;
    }

    /**
     * Multiplies the left and right subtrees, according to a formula defined
     * in the helper method's Javadoc comment.
     *
     * This method can and should be done in one line only.
     *
     * @return The baseMultiply value of the tree.
     */
    public int baseMultiply() {
        return baseMultiply(root);
    }

    /**
     * Returns a number calculated from the data for this subtree, according
     * to a formula.
     *
     * This is the helper method to be used for {@code baseMultiply()}.
     *
     * The formula:
     *
     * current node's data + 6 * baseMultiply(left subtree)
     *     + 7 * baseMultiply(right subtree)
     *
     * @param current The current node you're looking at.
     * @return The baseMultiply value of this subtree.
     */
    private int baseMultiply(BSTNode current) {
        if (current != null) {
            return current.getData() + (6 * baseMultiply(current.getLeft()))
                    + (7 * baseMultiply(current.getRight()));
        }
        return 0;
    }

    /**
     * Calculate the number of items that are strictly greater than the data
     * passed in.
     *
     * HINT: For this method to be efficient, remember that the data in a BST
     * is ordered in a certain way.  You may not have to traverse an entire
     * subtree.
     *
     * This method can and should be done in one line only.
     *
     * @param data Count the number of items greater than this element.
     * @return number of items strictly greater than the data passed in.
     */
    public int thresholdCount(int data) {
        return thresholdCount(root, data);
    }

    /**
     * Calculate the number of items in this subtree that are strictly greater
     * than the data passed in.
     *
     * This is the helper method to be used for
     * {@code thresholdCount(int data)}.
     *
     * @param current The current node you're looking at.
     * @param data Count the number of items greater than this element.
     * @return number of items in this subtree greater than data.
     */
    private int thresholdCount(BSTNode current, int data) {
        if (current != null) {
            if (current.getData() > data) {
                return 1 + thresholdCount(current.getLeft(), data)
                        + thresholdCount(current.getRight(), data);

            } else {
                return thresholdCount(current.getRight(), data);
            }
        }
        return 0;
    }

    /**
     * Calculates the sum of the nodes only on even levels of the tree.
     * The root is considered to be level 0, (0 is even) and the "number" of
     * the level increases as you go farther down the tree.
     *
     * For example, for a tree that looks like the following:
     *                    8
     *                   / \
     *                  /   \
     *                 6    10
     *                / \    \
     *               /   \    \
     *              4     7    15
     *
     * This will return 8 + 4 + 7 + 15 = 34.
     *
     * This method can and should be done in one line only.
     *
     * @return the sum of the data contained in the nodes only on even levels
     *         of the tree.
     */
    public int sumEvenLevels() {
        return sumEvenLevels(root);
    }

    /**
     * Calculates the sum of the nodes only on even levels of the subtree.
     *
     * This is the helper method to be used for {@code sumEvenLevels()}.
     *
     * @param current The current node you are looking at.
     * @return the sum of the nodes only on even levels of the subtree.
     */
    private int sumEvenLevels(BSTNode current) {
        if (current != null) {
            if ((current.getLeft() == null) && (current.getRight() == null)) {
                return current.getData();
            } else if (current.getLeft() == null) {
                return current.getData()
                        + sumEvenLevels(current.getRight().getLeft())
                        + sumEvenLevels(current.getRight().getRight());
            } else if (current.getRight() == null) {
                return current.getData()
                        + sumEvenLevels(current.getLeft().getLeft())
                        + sumEvenLevels(current.getLeft().getRight());
            } else {
                return current.getData()
                        + sumEvenLevels(current.getLeft().getLeft())
                        + sumEvenLevels(current.getLeft().getRight())
                        + sumEvenLevels(current.getRight().getLeft())
                        + sumEvenLevels(current.getRight().getRight());
            }
        }
        return 0;
    }

    /**
     * Finds the smallest item in the tree and returns it.
     *
     * This method can and should be done in five lines or less.
     *
     * @return smallest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    public Integer getMin() {
        if (root == null) {
            return null;
        } else {
            return getMin(root);
        }
    }

    /**
     * Finds the smallest item in the tree and returns it.
     *
     * This is the helper method to be used for {@code getMin()}.
     *
     * @param current The current node you're looking at.
     * @return smallest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    private Integer getMin(BSTNode current) {
        if (current.getLeft() != null) {
            return getMin(current.getLeft());
        }
        return current.getData();
    }

    /**
     * Finds the largest item in the tree and returns it.
     *
     * This method can and should be done in five lines or less.
     *
     * @return largest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    public Integer getMax() {
        if (root == null) {
            return null;
        } else {
            return getMax(root);
        }
    }

    /**
     * Finds the largest item in the tree and returns it.
     *
     * This is the helper method to be used for {@code getMax()}.
     *
     * @param current The current node you're looking at.
     * @return largest item in the tree. If the tree is empty,
     * return {@code null}.
     */
    private Integer getMax(BSTNode current) {
        if (current.getRight() != null) {
            return getMax(current.getRight());
        }
        return current.getData();
    }

    /**
     * Calculates the height of the tree.
     *
     * This method can and should be done in one line only.
     *
     * @return height of the tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Calculates the height of the tree.
     *
     * This is the helper method to be used for {@code height()}.
     *
     * The height of a tree is defined as:
     *
     * max(height(left subtree), height(right subtree)) + 1
     *
     * The height of a {@code null} node is -1, and the height of a leaf
     * node is 0.
     *
     * @param current The current node you're looking at.
     * @return height of the tree
     */
    private int height(BSTNode current) {
        if (current == null) {
            return -1;
        }
        if ((current.getLeft() == null) && (current.getRight() == null)) {
            return 0;
        } else if ((current.getLeft() == null)) {
            return (height(current.getRight()) + 1);
        } else if (current.getRight() == null) {
            return (height(current.getLeft()) + 1);
        } else {
            return (Math.max(height(current.getRight()),
                    height(current.getLeft())) + 1);
        }
    }

    /**
     * Returns a string representation of the tree. The string should be
     * formatted as follows:
     *        [currentData, leftSubtree, rightSubtree]
     *
     * For example, for a tree that looks like the following:
     *                    8
     *                   / \
     *                  /   \
     *                 6    10
     *                / \    \
     *               /   \    \
     *              4     7    15
     *
     * The string should be:
     *      [8, [6, [4, [], []], [7, [], []]], [10, [], [15, [], []]]
     *
     * An empty tree should return an empty set of brackets, i.e. [].
     *
     * This method can and should be done in one line only.
     *
     * @return String representation of the tree
     */
    public String toString() {
        return toString(root);
    }

    /**
     * Returns the string representation of this subtree, using the format
     * specified in the in the public method Javadoc comment.
     *
     * This is the helper method to be used for {@code toString()}.
     *
     * @param current The current node you're looking at.
     * @return string representation of the subtree.
     */
    private String toString(BSTNode current) {
        if (current != null) {
            return "[" + current.getData() + ", "
                    + toString(current.getLeft()) + ", "
                    + toString(current.getRight()) + "]";
        }
        return "[]";
    }

    /**
     * DO NOT USE THIS METHOD.  For testing purposes only.
     * @return The root of the tree.
     */
    public BSTNode getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
