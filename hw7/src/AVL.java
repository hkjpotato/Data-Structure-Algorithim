import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * Creates an AVL Tree
 *
 * @author KJ
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public AVL() {
    }

    /**
     * Initializes the AVL with the data in the collection. The data
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data is null.");
        }

        for (T element: data) {
            if (element == null) {
                throw new IllegalArgumentException(
                        "The input data has null element."
                );
            }
            this.add(element);
        }

    }

    /**
     * Balance a AVL tree when its root node has a balance factor = +2/-2
     *
     *
     * @param node the root node of the tree
     * @return the root node of the rebalanced tree
     * the return node should have updated height and BF
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        if (node.getBalanceFactor() == 2) {
            if (node.getLeft().getBalanceFactor() == -1) {
                node = lrRotation(node);
            } else {
                // could be 1
                // or 0 when removing
                node = llRotation(node);
            }
        } else {
            if (node.getRight().getBalanceFactor() == 1) {
                node = rlRotation(node);
            } else {
                // could be -1
                // or 0 when removing
                node = rrRotation(node);
            }
        }
        return node;
    }


    /**
     * Update the height of a given AVL tree node
     *
     *
     * @param node the node to be calculated
     */
    private void updateHeight(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.setHeight(0);
        } else if (node.getLeft() != null && node.getRight() == null) {
            node.setHeight(node.getLeft().getHeight() + 1);
        } else if (node.getLeft() == null && node.getRight() != null) {
            node.setHeight(node.getRight().getHeight() + 1);
        } else {
            node.setHeight(Math.max(
                    node.getLeft().getHeight(),
                    node.getRight().getHeight()) + 1
            );
        }
    }

    /**
     * Update the balance factor of a given AVL tree node
     *
     *
     * @param node the node to be calculated
     */
    private void updateBalanceFactor(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.setBalanceFactor(0);
        } else if (node.getLeft() != null && node.getRight() == null) {
            node.setBalanceFactor(node.getLeft().getHeight() - (-1));
        } else if (node.getLeft() == null && node.getRight() != null) {
            node.setBalanceFactor(-1 - node.getRight().getHeight());
        } else {
            node.setBalanceFactor(
                    node.getLeft().getHeight() - node.getRight().getHeight()
            );
        }
    }

    /**
     * Do rotation of a AVL tree in left left case
     *
     *
     * @param root the root node of the AVL tree
     * @return the root node of the updated tree after rotation
     * the return tree should have updated BF and heights
     */
    private AVLNode<T> llRotation(AVLNode<T> root) {
        AVLNode<T> pivot = root.getLeft();
        root.setLeft(pivot.getRight());
        updateBalanceFactor(root);
        updateHeight(root);

        pivot.setRight(root);
        updateBalanceFactor(pivot);
        updateHeight(pivot);
        return pivot;
    }

    /**
     * Do rotation of a AVL tree in left right case
     *
     *
     * @param root the root node of the AVL tree
     * @return the root node of the updated tree after rotation
     * the return tree should have updated BF and heights
     */
    private AVLNode<T> lrRotation(AVLNode<T> root) {
        root.setLeft(rrRotation(root.getLeft()));
        updateBalanceFactor(root);
        updateHeight(root);

        return (llRotation(root));
    }

    /**
     * Do rotation of a AVL tree in right right case
     *
     *
     * @param root the root node of the AVL tree
     * @return the root node of the updated tree after rotation
     * the return tree should have updated BF and heights
     */
    private AVLNode<T> rrRotation(AVLNode<T> root) {
        AVLNode<T> pivot = root.getRight();
        root.setRight(pivot.getLeft());
        updateBalanceFactor(root);
        updateHeight(root);

        pivot.setLeft(root);
        updateBalanceFactor(pivot);
        updateHeight(pivot);

        return pivot;
    }

    /**
     * Do rotation of a AVL tree in right left case
     *
     *
     * @param root the root node of the AVL tree
     * @return the root node of the updated tree after rotation
     * the return tree should have updated BF and heights
     */
    private AVLNode<T> rlRotation(AVLNode<T> root) {
        root.setRight(llRotation(root.getRight()));
        updateBalanceFactor(root);
        updateHeight(root);

        return (rrRotation(root));
    }


    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        root = addHelper(root, data);

    }

    /**
     * Adds the data to a AVL tree. Do nothing if data exists.
     *
     *
     * @param node the root node of the tree
     * @param data data to add to the tree
     * @return the root node of the updated tree with the data added
     */
    private AVLNode<T> addHelper(AVLNode<T> node, T data) {
        if (node == null) {
            node = new AVLNode<>(data);
            size++;
        } else if (data.compareTo(node.getData()) != 0) {
            if (data.compareTo(node.getData()) < 0) {
                node.setLeft(addHelper(node.getLeft(), data));
            } else if (data.compareTo(node.getData()) > 0) {
                node.setRight(addHelper(node.getRight(), data));
            }
            updateBalanceFactor(node);
            updateHeight(node);
            if (Math.abs(node.getBalanceFactor()) > 1) {
                node = balance(node);
            }
        }
        // if data already exits, do nothing
        return node;
    }


    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }


        //the dummy is used to stored the return data
        AVLNode<T> dummy = new AVLNode<>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Removes the data from a tree
     *
     * @throws java.util.NoSuchElementException if the data is not in the AVL
     * @param node the root node of the tree
     * @param data data to remove from the tree
     * @param dummy a dummy node used to store the removed data
     * @return the root node of the updated tree with the data deleted
     */
    private AVLNode<T> removeHelper(AVLNode<T> node, T data, AVLNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException(
                    "The data is not found in the tree"
            );
        } else {
            int comp = node.getData().compareTo(data);

            if (comp == 0) {
                //store the removed data
                dummy.setData(node.getData());

                //check how many children does this node have
                if (node.getLeft() == null && node.getRight() == null) {
                    //no child
                    node = null;
                    size--;
                } else if (node.getLeft() != null && node.getRight() == null) {
                    // only left child
                    node = node.getLeft();
                    size--;
                } else if (node.getLeft() == null && node.getRight() != null) {
                    //only right child
                    node = node.getRight();
                    size--;
                } else {
                   // two children

                    //find predecessor
                    AVLNode<T> predecessor = node.getLeft();
                    while (predecessor.getRight() != null) {
                        predecessor = predecessor.getRight();
                    }

                    //track the position of the current node(root)
                    AVLNode<T> trackCurrentNode = node;

                    //remove the predecessor from the current node(root)
                    AVLNode<T> dummyPredecessor = new AVLNode<>(null);
                    node = removeHelper(
                            node,
                            predecessor.getData(),
                            dummyPredecessor
                    );

                    //after above remove,
                    //the original root might not be the root now.
                    //should update the original node w
                    // ith the data of predecessor.
                    trackCurrentNode.setData(predecessor.getData());
                }
            } else {
                if (comp > 0) {
                    node.setLeft(removeHelper(node.getLeft(), data, dummy));
                } else {
                    node.setRight(removeHelper(node.getRight(), data, dummy));
                }
                updateBalanceFactor(node);
                updateHeight(node);
                if (Math.abs(node.getBalanceFactor()) > 1) {
                    node = balance(node);
                }
            }
            return node;
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data is null");
        }

        if (root == null) {
            throw new NoSuchElementException("The tree is empty.");
        } else {
            return getHelper(data, root);
        }
    }

    /**
     * Get the node with target value from a tree,
     * starting from a given a position
     *
     * @throws java.util.NoSuchElementException if the data is not found.
     * @param data the data to be removed
     * @param p is the starting position(node) for searching
     * @return the data to get
     */
    private T getHelper(T data, AVLNode<T> p) {
        if (p == null) {
            throw new NoSuchElementException("Data is not found.");
        }

        if (data.equals(p.getData())) {
            return p.getData();
        } else if (data.compareTo(p.getData()) < 0) {
            return getHelper(data, p.getLeft());
        } else {
            return getHelper(data, p.getRight());
        }

    }

    @Override
    public boolean contains(T data) {
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> ret = new LinkedList<>();

        if (root != null) {
            preorderHelper(root, ret);
        }

        return ret;
    }

    /**
     * Traverse the part of a tree in preorder, starting from a certain position
     * Add the result into a list
     *
     * @param p the starting position for traversing
     * @param list the list used to stored the traversed result
     */
    private void preorderHelper(AVLNode<T> p, List<T> list) {
        list.add(p.getData());
        if (p.getLeft() != null) {
            preorderHelper(p.getLeft(), list);
        }

        if (p.getRight() != null) {
            preorderHelper(p.getRight(), list);
        }
    }

    @Override
    public List<T> postorder() {
        List<T> ret = new LinkedList<>();

        if (root != null) {
            postorderHelper(root, ret);

        }
        return ret;
    }

    /**
     * Traverse the part of a tree in postorder,
     * starting from a certain position
     * Add the result into a list
     *
     * @param p the starting position for traversing
     * @param list the list used to stored the traversed result
     *
     */
    private void postorderHelper(AVLNode<T> p, List<T> list) {

        if (p.getLeft() != null) {
            postorderHelper(p.getLeft(), list);
        }

        if (p.getRight() != null) {
            postorderHelper(p.getRight(), list);
        }

        list.add(p.getData());
    }

    @Override
    public List<T> inorder() {
        List<T> ret = new LinkedList<>();

        if (root != null) {
            inorderHelper(root, ret);
        }
        return ret;
    }

    /**
     * Traverse the part of a tree in inorder, starting from a certain position
     * Add the result into a list
     *
     * @param p the starting position for traversing
     * @param list the list used to stored the traversed result
     *
     */
    private void inorderHelper(AVLNode<T> p, List<T> list) {

        if (p.getLeft() != null) {
            inorderHelper(p.getLeft(), list);
        }

        list.add(p.getData());

        if (p.getRight() != null) {
            inorderHelper(p.getRight(), list);
        }

    }

    @Override
    public List<T> levelorder() {
        List<T> list = new LinkedList<>();
        Queue<AVLNode<T>> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);

            while (!queue.isEmpty()) {
                AVLNode<T> curr = queue.remove();

                list.add(curr.getData());


                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());

                }

                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            }
        }

        return list;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root != null) {
            return root.getHeight();
        }
        return -1;
    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        int currDepth = 1;
        return depthHelper(data, root, currDepth);
    }


    /**
     * Get the depth of a node with target value in a tree
     * Start from a certain point
     *
     * @throws java.util.NoSuchElementException if the data is not in the tree.
     * @param data the data to be searched
     * @param p the starting position of searching
     * @param currDepth the depth of the starting node
     * @return the depth of the node with the data to search for
     */

    private int depthHelper(T data, AVLNode<T> p, int currDepth) {
        if (p == null) {
            throw new NoSuchElementException("The data is not in the tree.");
        }

        if (data.equals(p.getData())) {
            return currDepth;
        } else if (data.compareTo(p.getData()) < 0) {
            currDepth++;
            return depthHelper(data, p.getLeft(), currDepth);
        } else {
            currDepth++;
            return depthHelper(data, p.getRight(), currDepth);
        }
    }


    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return root;
    }
}
