import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the collection. The data in the BST
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }



        for (T element : data) {
            if (element == null) {
                throw new IllegalArgumentException(
                        "Input collection has element which is null."
                );
            }
            add(element);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

//        if (!contains(data)) {
//            root = addHelper(data, root);
//        }
        root = addHelper2(data, root);
    }


    private BSTNode<T> addHelper2(T data, BSTNode<T> p) {
        if (p == null) {
            p = new BSTNode<T>(data);
            size++;
        } else if (data.compareTo(p.getData()) < 0) {
            p.setLeft(addHelper2(data, p.getLeft()));

        } else if (data.compareTo(p.getData()) > 0) {
            p.setRight(addHelper2(data, p.getRight()));
        }
        //what if data == p.getData();
        return p;
    }

    /**
     * Add the data as a new leaf to a tree
     * Starting from a certain position in the tree
     * Input data is assumed not to be null
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     * @param p the starting position(node) for adding
     * @return the updated node at the input position with the data added
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> p) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        if (p == null) {
            size++;
            p = new BSTNode<T>(data);
        } else if (data.compareTo(p.getData()) < 0) {
            p.setLeft(addHelper(data, p.getLeft()));

        } else {
            p.setRight(addHelper(data, p.getRight()));
        }
        return p;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

//        if (root == null) {
//            throw new NoSuchElementException("The tree is empty.");
//        }


//        BSTNode<T> virtualPrev = new BSTNode<>(null);
//        virtualPrev.setRight(root);
//
//        T ret = removeHelper(data, virtualPrev, root, false);
//        root = virtualPrev.getRight();
//        return ret;

        //the dummy is used to stored the return data
        BSTNode<T> dummy = new BSTNode<>(null);

        root = removeHelper2(root, data, dummy);
        return dummy.getData();
    }




//    private BSTNode<T> removeHH(T data, BSTNode<T> p){
//        if (data == null) {
//            throw new IllegalArgumentException("Input data is null.");
//        }
//
//        if (p == null) {
//            throw new IllegalArgumentException("Input node is null.");
//        }
//
//        if (data.equals(p.getData())) {
//            if (p.getLeft() == null && p.getRight() == null) {
//                return null;
//            } else if (p.getLeft() != null && p.getRight() != null) {
//                return null;
//
//            } else {
//                if(p.getLeft() != null) {
//                    p = p.getLeft();
//                    return p;
//                } else {
//                    p = p.getRight();
//                    return p;
//                }
//            }
//
//        } else if (data.compareTo(p.getData()) < 0) {
//            p.setLeft(removeHH(data, p.getLeft()));
//            return p;
//        } else {
//            p.setRight(removeHH(data, p.getRight()));
//            return p;
//        }
//    }

    /* remove a node contains the data from a given BSTNode.


     */
    private BSTNode<T> removeHelper2 (BSTNode<T> node, T data, BSTNode<T> dummy) throws NullPointerException {
        try {
            throw new Throwable("The data is not found in the tree");

        } catch (Throwable e ) {

        }

        if (node == null) {
            throw new NullPointerException("The data is not found in the tree");
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

                    //find successor
                    BSTNode<T> successor = node.getRight();
                    while (successor.getLeft() != null) {
                        successor = successor.getLeft();
                    }

                    //remove successor
//                    注意 这样是不行的 等于successor的reference update了但在树结构中那个位置并没有update
//                    BSTNode<T> removedSuccessor = new BSTNode<>(null);
//                    successor = removeHelper2(successor, successor.getData(),removedSuccessor);

                    BSTNode<T> dummySuccessor = new BSTNode<>(null);
                    node = removeHelper2(node, successor.getData(), dummySuccessor);
                    node.setData(dummySuccessor.getData());


                }

            } else if (comp > 0) {
                node.setLeft(removeHelper2(node.getLeft(), data, dummy));
            } else {
                node.setRight(removeHelper2(node.getRight(), data, dummy));
            }
            return node;
        }
    }





    /**
     * Remove a node with the target data from a certain position in a tree.
     * Use two reference node to track the current node and the previous node
     * Input data is assumed not to be null
     *
     * @throws IllegalArgumentException if the data is null.
     * @throws IllegalArgumentException if the prev is null.
     * @throws IllegalArgumentException if the curr is null.
     * @throws java.util.NoSuchElementException if the data is not found.
     * @param data the data to be removed
     * @param prev a reference to the parent node of the current node
     * @param curr a reference node pointing to the current node
     * @param isLeftChild a boolean value to check
     *        if the current node is the left child of the previous node
     * @return the data removed from the tree.
     */
    private T removeHelper(
            T data, BSTNode<T> prev, BSTNode<T> curr, boolean isLeftChild) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        if (prev == null) {
            throw new IllegalArgumentException(
                    "The previous node used by helper method is null."
            );
        }
        if (curr == null) {
            throw new IllegalArgumentException(
                    "The current node used by helper method is null."
            );
        }
        if (data.equals(curr.getData())) {
            if (curr.getLeft() == null && curr.getRight() == null) {
                T ret = curr.getData();
                if (isLeftChild) {
                    prev.setLeft(null);
                    size--;
                    return ret;
                } else {
                    prev.setRight(null);
                    size--;
                    return ret;
                }
            } else if (curr.getLeft() != null && curr.getRight() != null) {
                BSTNode<T> successor = curr.getRight();
                boolean succIsLeftChild = false;
                BSTNode<T> prevSucc = curr;
                while (successor.getLeft() != null) {
                    prevSucc = successor;
                    successor = successor.getLeft();
                    succIsLeftChild = true;
                }

                T ret = curr.getData();
                T sucData = successor.getData();


                removeHelper(sucData, prevSucc, successor, succIsLeftChild);
                curr.setData(sucData);
                return ret;

            } else {
                T ret = curr.getData();
                if (curr.getLeft() != null) {
                    if (isLeftChild) {
                        prev.setLeft(curr.getLeft());
                    } else {
                        prev.setRight(curr.getLeft());
                    }
                } else {
                    if (isLeftChild) {
                        prev.setLeft(curr.getRight());
                    } else {
                        prev.setRight(curr.getRight());
                    }
                }
                size--;
                return ret;
            }




        }  else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                throw new NoSuchElementException("No such element is found.");
            }
            isLeftChild = true;
            prev = curr;
            curr = curr.getLeft();
            return removeHelper(data, prev, curr, isLeftChild);
        } else {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("No such element is found.");
            }
            isLeftChild = false;
            prev = curr;
            curr = curr.getRight();
            return removeHelper(data, prev, curr, isLeftChild);
        }

    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        if (root == null) {
            throw new NoSuchElementException("The tree is empty.");
        } else {
            return getH(data, root);
        }
    }

    /**
     * Get the node with target value from a tree,
     * starting from a given a position
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found.
     * @param data the data to be removed
     * @param p is the starting position(node) for searching
     * @return the data to get
     */
    private T getH(T data, BSTNode<T> p) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        if (p == null) {
            throw new NoSuchElementException("Data is not found.");
        }

        if (data.equals(p.getData())) {
            return p.getData();
        } else if (data.compareTo(p.getData()) < 0) {
            return getH(data, p.getLeft());
        } else {
            return getH(data, p.getRight());
        }

    }


    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }


        return containsHelper(data, root);

    }



    /**
     * Check if a certain data exists in a tree,
     * starting from a certain position
     *
     * @throw IllegalArgumentException if the input data is null
     * @param data the data to search for
     * @param p the starting position for checking
     * @return whether or not the parameter is contained within the tree
     */
    private boolean containsHelper(T data, BSTNode<T> p)  {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        if (p == null) {
            return false;
        } else if (data.equals(p.getData())) {
            return true;
        } else if (data.compareTo(p.getData()) < 0) {
            return containsHelper(data, p.getLeft());
        } else  {
            return containsHelper(data, p.getRight());
        }
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
    private void preorderHelper(BSTNode<T> p, List<T> list) {
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
    private void postorderHelper(BSTNode<T> p, List<T> list) {

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
    private void inorderHelper(BSTNode<T> p, List<T> list) {

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
        Queue<BSTNode<T>> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);

            while (!queue.isEmpty()) {
                BSTNode<T> curr = queue.remove();

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
        int ret;
        if (root == null) {
            return -1;
        }
        return heightH(root);
    }



    /**
     * Get the height of a node in a tree
     *
     *
     * @param p the node to be checked
     * @return the height of the node
     */
    private int heightH(BSTNode<T> p) {
        if (p == null) {
            return 0;
        }

        if (p.getLeft() == null && p.getRight() == null) {
            return 0;
        } else {
            return (Math.max(heightH(p.getLeft()), heightH(p.getRight())) + 1);
        }

    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        int currDepth = 1;
        return depthH(data, root, currDepth);
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

    private int depthH(T data, BSTNode<T> p, int currDepth) {
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
            return depthH(data, p.getLeft(), currDepth);
        } else {
            currDepth++;
            return depthH(data, p.getRight(), currDepth);
        }
    }



    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }
}
