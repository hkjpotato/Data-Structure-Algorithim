import java.util.*;

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
            if(element == null) {
                throw new IllegalArgumentException("Input collection has element which is null.");
            }
            add(element);
        }
    }

    @Override
//    public void add(T data) {
//        if (data == null) {
//            throw new IllegalArgumentException("Input data is null.");
//        }
//
////        if(!contains(data)) {
////            root = addH2(data, root);
////
////        }
//
//        if (size == 0) {
//            root = new BSTNode<>(data);
//            size++;
//        } else {
//            addH(data, root);
//        }

    }

    //TODO javadoc/name?
    //FixMe BSTNode or BST itself?
    //FixMe use BSTNode<T extends comparable<? super T>?

//    private BSTNode<T> add(T data, BSTNode<T> p){
//        if (data == null) {
//            throw new IllegalArgumentException("Input data is null.");
//        }
//
//        //TODO too much?
//        if (p == null) {
//            //TODO WTF!!!!!!! BSTNode<T> is not allowed
//            p = new BSTNode(data);
//            size++;
//        } else if (data.compareTo(p.getData()) < 0) {
//             p.setLeft(add(data, p.getLeft()));
//        } else {
//            p.setRight(add(data, p.getRight()));
//        }
//        return p;
//    }

    private BSTNode<T> addH2(T data, BSTNode<T> p) {
        if (p == null) {
            size++;
            return new BSTNode<T> (data);
        }

        if (data.compareTo(p.getData()) < 0) {
            p.setLeft(addH2(data, p.getLeft()));
            return p;
        } else  {
            p.setRight(addH2(data, p.getRight()));
            return p;
        }
    }


    private void addH(T data, BSTNode<T> p) {
//        if (data == null) {
//            throw new IllegalArgumentException("Input data is null.");
//        }

//        if (p == null) {
//            //TODO should I mention node here?
//            throw new IllegalArgumentException("Input node is null.");
//        }

        if (data.compareTo(p.getData()) < 0) {
            if (p.getLeft() != null) {
                addH(data, p.getLeft());
            } else {
                p.setLeft(new BSTNode<T>(data));
                size++;
            }
        } else if (data.compareTo(p.getData()) > 0) {
            if (p.getRight() != null) {
                addH(data, p.getRight());
            } else {
                p.setRight(new BSTNode<T>(data));
                size++;
            }
        }

    }

    @Override
    public T remove(T data) {
        //TODO Do not return the same data
        //TODO that was passed in.  Return the data that was stored in the tree?
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        //TODO is this exception appropriate?
        if (root == null) {
            throw new NoSuchElementException("The tree is empty.");
        }


//        if (data.equals(root.getData())) {
//            size--;
//            //TODO is this what required:return the data stored instead of data input?
//            T ret = root.getData();
//            root = null;
//            return ret;
//        } else {
//            return removeH(data, root, root, false);
//        }

        BSTNode<T> virtualPrev = new BSTNode<>(null);
        virtualPrev.setRight(root);

        T ret = removeH2(data, virtualPrev, root, false);
        root = virtualPrev.getRight();
        return ret;

//        if(data.equals(root.getData())) {
//            if(size == 1) {
//                T ret = root.getData();
//                root = null;
//                size--;
//                return ret;
//            } else if (root.getLeft() != null && root.getRight() != null) {
//
//            } else {
//                if
//            }

    }





    //TODO javadoc
    private T removeH(T data, BSTNode<T> prev, BSTNode<T> curr, boolean isLeftChild) {
        if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                throw new NoSuchElementException("No such element is found.");
            }
            isLeftChild = true;
            prev = curr;
            curr = curr.getLeft();
            return removeH(data, prev, curr, isLeftChild);
        } else if(data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("No such element is found.");
            }
            isLeftChild = false;
            prev = curr;
            curr = curr.getRight();
            return removeH(data, prev, curr, isLeftChild);
        }

//        if (!data.equals(curr.getData())) {
//            return removeH(data, prev, curr, isLeftChild);
//        }
            else {
            //case1 no child
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
            }
            //TODO how to make 1 & 0 boolean
            //case2 2 child
            else if (curr.getLeft() != null && curr.getRight() != null) {
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

                //Fixme Can I declare it here?
//                curr.setData(tempData);
                //TODO how to deal with the return value???
                removeH(sucData, prevSucc, successor, succIsLeftChild);
                curr.setData(sucData);
                return ret;
            }

            //case3 1 children
            else {
                T ret = curr.getData();
                if(curr.getLeft()!=null) {
                    if(isLeftChild) {
                        prev.setLeft(curr.getLeft());
                    } else {
                        prev.setRight(curr.getLeft());
                    }
                 } else {
                    if(isLeftChild) {
                        prev.setLeft(curr.getRight());
                    } else {
                        prev.setRight(curr.getRight());
                    }
                }
                size--;
                return ret;
            }

        }
    }

    private T removeH2(T data, BSTNode<T> prev, BSTNode<T> curr, boolean isLeftChild) {
        if (data.equals(curr.getData())) {
            //case1 0 child
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
            }

            //case2 2 children
            else if (curr.getLeft() != null && curr.getRight() != null) {
//                BSTNode<T> successor = curr.getRight();
//                BSTNode<T> preSucc = curr;
//
//                while (successor.getLeft() != null) {
//                    preSucc = successor;
//                    successor = successor.getLeft();
//                }
//
//                T ret = curr.getData();
//                T sucData = successor.getData();
//                curr.setData(sucData);
//
//                removeH(sucData, preSucc, successor, isLeftChild);
//                return ret;

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


                removeH(sucData, prevSucc, successor, succIsLeftChild);
                curr.setData(sucData);
                return ret;


            }

            //case3 1 child
            else {
                T ret = curr.getData();
                if(curr.getLeft()!=null) {
                    if(isLeftChild) {
                        prev.setLeft(curr.getLeft());
                    } else {
                        prev.setRight(curr.getLeft());
                    }
                } else {
                    if(isLeftChild) {
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
            return removeH(data, prev, curr, isLeftChild);
        } else {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("No such element is found.");
            }
            isLeftChild = false;
            prev = curr;
            curr = curr.getRight();
            return removeH(data, prev, curr, isLeftChild);
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

    //TODO javadoc
    private T getH(T data, BSTNode<T> p) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        //TODO is this exception appropriate?
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


        return containsH(data, root);

    }

    private boolean containsH(T data, BSTNode<T> p) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        if (p == null) {
            return false;
        } else if (data.equals(p.getData())) {
            return true;
        } else if (data.compareTo(p.getData()) < 0) {
                return containsH(data, p.getLeft());
        } else  {
            return containsH(data, p.getRight());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> ret = new LinkedList<>();
        //TODO do I need to conisder the case when it's empty
        if (root != null) {
            preorderH(root, ret);
        }

        return ret;
    }

    //TODO can I just use void?
    private void preorderH(BSTNode<T> p, List<T> list) {
        list.add(p.getData());
        if (p.getLeft() != null) {
            preorderH(p.getLeft(), list);
        }

        if (p.getRight() != null) {
            preorderH(p.getRight(), list);
        }
    }

    @Override
    public List<T> postorder() {
        List<T> ret = new LinkedList<>();
//            throw new NoSuchElementException("The tree is empty.");

        if (root != null) {
            postorderH(root, ret);

        }
        return ret;
    }

    private void postorderH(BSTNode<T> p, List<T> list) {

        if (p.getLeft() != null) {
            postorderH(p.getLeft(), list);
        }

        if (p.getRight() != null) {
            postorderH(p.getRight(), list);
        }

        list.add(p.getData());
    }

    @Override
    public List<T> inorder() {
        List<T> ret = new LinkedList<>();

        if (root != null) {
//            throw new NoSuchElementException("The tree is empty.");
            inorderH(root, ret);
        }
        return ret;
    }

    private void inorderH(BSTNode<T> p, List<T> list) {

        if (p.getLeft() != null) {
            preorderH(p.getLeft(), list);
        }

        list.add(p.getData());

        if (p.getRight() != null) {
            preorderH(p.getRight(), list);
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
//            throw new NoSuchElementException("The tree is empty.");
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
        if(root == null) {
            return -1;
        }
        return heightH(root);
    }

    private int heightH(BSTNode<T> p) {
        if (p == null) {
            return 0;
        }

        if (p.getLeft()== null && p.getRight() == null) {
            return 0;
        } else {
            return (Math.max(heightH(p.getLeft()),heightH(p.getRight())) + 1);
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

    private int depthH(T data, BSTNode<T> p, int currDepth) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        if(p == null) {
            return -1;
        }

        if(data.equals(p.getData())) {
            return currDepth;
        } else if(data.compareTo(p.getData()) < 0) {
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
