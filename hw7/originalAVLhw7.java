import java.util.*;

/**
 * Creates an AVL Tree
 *
 * @author YOUR_NAME HERE
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
            //FIXME do we need this add?
            if (element == null) {
                throw new IllegalArgumentException("The input data has null element.");
            }
            this.add(element);
        }

    }


    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        AVLNode<T> BFCheck = new AVLNode<>(data);
        BFCheck.setBalanceFactor(-1);

        root = addHelper(root, data, BFCheck);

    }


    private AVLNode<T> addHelper(AVLNode<T> node, T data, AVLNode<T> BFCheck) {
        if (node == null) {
            node = new AVLNode<>(data);
            size++;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(node.getLeft(), data, BFCheck));

            // check if you need to adjust the BF
            if (BFCheck.getBalanceFactor() != 0) {
                //once change is still needed, the current BF can only be increased by 1
                //it is impossible to have an unchanged BF in such case

//                resetBalanceFactor(node);
                node.setBalanceFactor(node.getBalanceFactor() + 1);
                int currentBF = node.getBalanceFactor();

                if (currentBF == 0) {
                    BFCheck.setBalanceFactor(0);
                } else if (currentBF == 1) {
                    node.setHeight(node.getHeight() + 1);
                } else {
                    //current BF can only be 2
                    if (node.getLeft().getBalanceFactor() == 1) {
                        node = LLrotation(node);
                        BFCheck.setBalanceFactor(0);
                    } else {
                        // in this case, BF of left is -1
                        node = LRrotation(node);
                        BFCheck.setBalanceFactor(0);

                    }
                }
            }

        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addHelper(node.getRight(), data, BFCheck));

            if (BFCheck.getBalanceFactor() != 0) {
                node.setBalanceFactor(node.getBalanceFactor() - 1);
                int currentBF = node.getBalanceFactor();

                if (currentBF == 0) {
                    BFCheck.setBalanceFactor(0);

                } else if (currentBF == -1) {
                    node.setHeight(node.getHeight() + 1);
                } else {
                    if (node.getRight().getBalanceFactor() == -1) {
                        node = RRrotation(node);
                        BFCheck.setBalanceFactor(0);

                    } else {
                        node = RLrotation(node);
                        BFCheck.setBalanceFactor(0);

                    }
                }
            }

        } else {
            //the data already exits, no need to check whether change BF or not
            BFCheck.setBalanceFactor(0);
        }

        return node;
    }

    

    private void resetHeight(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.setHeight(0);
        } else if (node.getLeft() != null && node.getRight() == null) {
            node.setHeight(node.getLeft().getHeight() + 1);
        } else if (node.getLeft() == null && node.getRight() != null) {
            node.setHeight(node.getRight().getHeight() + 1);
        } else {
            node.setHeight(Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1);
        }
    }

    private void resetBalanceFactor(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.setBalanceFactor(0);
        } else if (node.getLeft() != null && node.getRight() == null) {
            node.setBalanceFactor(node.getLeft().getHeight() - (-1));
        } else if (node.getLeft() == null && node.getRight() != null) {
            node.setBalanceFactor(-1 - node.getRight().getHeight());
        } else {
            node.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
        }
    }

    private AVLNode<T> LLrotation(AVLNode<T> root) {
        AVLNode<T> pivot = root.getLeft();
        root.setLeft(pivot.getRight());
        resetBalanceFactor(root);
        resetHeight(root);

        pivot.setRight(root);
        resetBalanceFactor(pivot);
        resetHeight(pivot);
        return pivot;
    }

    private AVLNode<T> LRrotation(AVLNode<T> root) {
        root.setLeft(RRrotation(root.getLeft()));
        resetBalanceFactor(root);
        resetHeight(root);

        return (LLrotation(root));
    }

    private AVLNode<T> RRrotation(AVLNode<T> root) {
        AVLNode<T> pivot = root.getRight();
        root.setRight(pivot.getLeft());
        resetBalanceFactor(root);
        resetHeight(root);

        pivot.setLeft(root);
        resetBalanceFactor(pivot);
        resetHeight(pivot);

        return pivot;
    }

    private AVLNode<T> RLrotation(AVLNode<T> root) {
        root.setRight(LLrotation(root.getRight()));
        resetBalanceFactor(root);
        resetHeight(root);

        return (RRrotation(root));
    }

    @Override
    public T remove(T data) {

        //the dummy is used to stored the return data
        AVLNode<T> dummy = new AVLNode<>(null);
        dummy.setBalanceFactor(-1);

        root = removeHelper2(root, data, dummy);
        return dummy.getData();
    }

    private AVLNode<T> removeHelper2(AVLNode<T> node, T data, AVLNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("The data is not found in the tree");
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
                } else if (node.getLeft() !=null && node.getRight() == null) {
                    // only left child
                    node = node.getLeft();
                    size--;
                } else if (node.getLeft() == null && node.getRight() != null) {
                    //only right child
                    node = node.getRight();
                    size--;
                } else {
                    // two children
                    //FIXME pre or succ
                    //find predecessor
                    AVLNode<T> successor = node.getLeft();
                    AVLNode<T> curr = node;
                    boolean isLeftChild = true;
                    while (successor.getRight() != null) {
                        curr = successor;
                        successor = successor.getRight();
                        isLeftChild = false;
                    }

                    //remove successor
//                    注意 这样是不行的 等于successor的reference update了但在树结构中那个位置并没有update
//                    BSTNode<T> removedSuccessor = new BSTNode<>(null);
//                    successor = removeHelper2(successor, successor.getData(),removedSuccessor);

                    AVLNode<T> dummySuccessor = new AVLNode<>(null);
//                    dummySuccessor.setBalanceFactor(-1);
//
//                    successor = removeHelper2(successor, successor.getData(), dummySuccessor);
//                    if (isLeftChild) {
//                        curr.setLeft(successor);
//                    } else {
//                        curr.setRight(successor);
//                    }
//
//                    node.setData(dummySuccessor.getData());

                    //FIXME can you start the delete at sucessor
                    AVLNode<T> trackCurrentNode = node;
                    dummySuccessor.setBalanceFactor(-1);
                    node = removeHelper2(node, successor.getData(), dummySuccessor);



                    //after above remove, node maybe somewhere else
                    trackCurrentNode.setData(dummySuccessor.getData());

                }

            } else if (comp > 0) {
                node.setLeft(removeHelper2(node.getLeft(), data, dummy));

                // check if you need to adjust the BF
                if (dummy.getBalanceFactor() != 0) {
                    //once change is still needed, adjust the BF

//                resetBalanceFactor(node);
                    node.setBalanceFactor(node.getBalanceFactor() - 1);
                    int currentBF = node.getBalanceFactor();

                    if (currentBF == -1) {
                        dummy.setBalanceFactor(0);
                    } else if (currentBF == 0) {
                        node.setHeight(node.getHeight() - 1);
                    } else {
                        //current BF can only be -2
                        if (node.getRight().getBalanceFactor() == -1) {
                            node = RRrotation(node);
                        } else if (node.getRight().getBalanceFactor() == 1) {
                            node = RLrotation(node);
                        } else {
                            // in this case, BF of of right is 0
                            node = RRrotation(node);
                            dummy.setBalanceFactor(0);
                        }
                    }
                }

            } else {
                node.setRight(removeHelper2(node.getRight(), data, dummy));

                if (dummy.getBalanceFactor() != 0) {

                    node.setBalanceFactor(node.getBalanceFactor() + 1);
                    int currentBF = node.getBalanceFactor();

                    if (currentBF == 1) {
                        dummy.setBalanceFactor(0);
                    } else if (currentBF == 0) {
                        node.setHeight(node.getHeight() - 1);
                    } else {
                        //current BF can only be +2
                        if (node.getLeft().getBalanceFactor() == 1) {
                            node = LLrotation(node);
                        } else if (node.getLeft().getBalanceFactor() == -1) {
                            node = LRrotation(node);
                        } else {
                            // in this case, BF of of right is 0
                            node = LLrotation(node);
                            dummy.setBalanceFactor(0);
                        }
                    }
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
        //FIXME it neccessary?
        if (data == null) {
            throw new IllegalArgumentException("The input data is null");
        }

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
    //TODO depth needed to be implemented
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
     * @throw IllegalArgumentException if the input data is null
     * @param data the data to be searched
     * @param p the starting position of searching
     * @param currDepth the depth of the starting node
     * @return the depth of the node with the data to search for
     */

    private int depthHelper(T data, AVLNode<T> p, int currDepth) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        if (p == null) {
            return -1;
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
