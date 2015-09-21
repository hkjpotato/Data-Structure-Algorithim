import java.util.List;

/**
 * The interface for an AVL Tree.
 * @version 1.0
 */
public interface AVLInterface<T extends Comparable<? super T>> {
    /**
     * Add the data as a leaf in the AVL.  Should traverse the tree to find the
     * appropriate location. If the data being added already exists in the tree,
     * do nothing.
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    void add(T data);

    /**
     * Removes the data from the tree.  There are 3 cases to consider:
     * 1: the data is a leaf.  In this case, simply remove it
     * 2: the data has one child.  In this case, simply replace it with its
     * child, then remove the child.
     * 3: the data has 2 children.  There are generally two approaches:
     * replacing the data with either the next smallest element in the tree
     * (commonly called the predecessor), or replacing it with the next
     * largest element in the tree (commonly called the successor). For this
     * assignment, use the predecessor.
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the AVL
     * @param data data to remove from the tree
     * @return the data removed from the tree.
     */
    T remove(T data);

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use .equals or ==?).
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the AVL
     * @param data data to get in the AVL tree
     * @return the data in the tree equal to the parameter.
     */
    T get(T data);

    /**
     * Returns whether or not the parameter is contained within the tree.
     * @throws IllegalArgumentException if key is null
     * @param data data to find in the AVL tree
     * @return whether or not the parameter is contained within the tree
     */
    boolean contains(T data);

    /**
     * Get the number of elements in the tree.
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Get the preorder traversal of the tree.
     * @return a preorder traversal of the tree, or an empty list
     */
    List<T> preorder();

    /**
     * Get the postorder traversal of the tree.
     * @return a postorder traversal of the tree, or an empty list
     */
    List<T> postorder();

    /**
     * Get the inorder traversal of the tree.
     * @return an inorder traversal of the tree, or an empty list
     */
    List<T> inorder();

    /**
     * Get the level order traversal of the tree.
     * @return a level order traversal of the tree, or an empty list
     */
    List<T> levelorder();

    /**
     * Clear the tree.
     */
    void clear();

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as max(left.height, right.height) + 1. A leaf node has
     * a height of 0.
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    int height();

    /**
     * Calculate the depth of the node containing the data.
     * The depth of the node is defined as (parent node's depth)+1. The depth of
     * the root node is 1.
     * @throws IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree.
     * @param data data to calculate the depth of
     * @return the depth of the node containing the data if it is in the tree
     */
    int depth(T data);
}
