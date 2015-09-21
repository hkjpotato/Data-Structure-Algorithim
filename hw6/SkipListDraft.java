import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    // Do not add any additional instance variables
    private CoinFlipper coinFlipper;
    private int size;
    private Node<T> head;

    /**
     * Constructs a SkipList object that stores data in ascending order.
     * When an item is inserted, the flipper is called until it returns a tails.
     * If, for an item, the flipper returns n heads, the corresponding node has
     * n + 1 levels.
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        head = new Node<T>(null,1);
    }

    @Override
    public T first() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
        Node<T> curr = head;
        while (curr.getDown() != null) {
            curr = curr.getDown();
        }

        return curr.getNext().getData();

    }

    @Override
    public T last() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }

        Node<T> curr = head;

        boolean reachLast = false;
        while (!reachLast) {
            while(curr.getNext() != null) {
                curr = curr.getNext();
            }

            if (curr.getDown()!= null) {
                curr = curr.getDown();
            } else {
                reachLast = true;
            }
        }

        return curr.getData();
    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data is null");
        }


        Node<T> insertAt = search(data);
        if (insertAt.getData() != null && insertAt.getData().equals(data)) {
            insertAt.setData(data);
            while(insertAt.getUp() != null) {
                insertAt = insertAt.getUp();
                insertAt.setData(data);
            }

        } else {
//            int numHeads = 0;
//            while (coinFlipper.flipCoin() != CoinFlipper.Coin.TAILS) {
//                numHeads++;
//            }
//
//
//            int height = head.getLevel();
//
//            if (numHeads >= height) {
//                for (int i = height + 1; i <= numHeads + 1; i++) {
//                    Node<T> newNode = new Node<T>(null, i, null, null, null, head);
//                    head.setUp(newNode);
//                    head = newNode;
//                }
//            }
//
//            Node<T> prevAddNode = null;
//            Node<T> newAddNode;
//
//            Node<T> insertAfter;
//            insertAfter = insertAt;
//
//            for (int level = 1; level <= numHeads + 1; level++) {
//                newAddNode = new Node<T>(data, level);
//                newAddNode.setPrev(insertAfter);
//
//                if (insertAfter.getNext() != null) {
//                    newAddNode.setNext(insertAfter.getNext());
//                    insertAfter.getNext().setPrev(newAddNode);
//                }
//
//                insertAfter.setNext(newAddNode);
//                if (prevAddNode != null) {
//                    newAddNode.setDown(prevAddNode);
//                    prevAddNode.setUp(newAddNode);
//                }
//                prevAddNode = newAddNode;
//                while (insertAfter.getUp() == null && level < numHeads + 1) {
//                    insertAfter = insertAfter.getPrev();
//                }
//                insertAfter = insertAfter.getUp();
//            }
//            size++;

            //add the node to the bottom layer
            Node<T> newAddNode;

            Node<T> insertAfter;
            insertAfter = insertAt;
            newAddNode = new Node<T>(data, 1);




            if (insertAfter.getNext() != null) {
                newAddNode.setNext(insertAfter.getNext());
                insertAfter.getNext().setPrev(newAddNode);
            }

            insertAfter.setNext(newAddNode);
            newAddNode.setPrev(insertAfter);

            Node<T> prevAddNode = newAddNode;


            int numHeads = 0;
            while (coinFlipper.flipCoin() != CoinFlipper.Coin.TAILS) {

                numHeads++;

                //move to the newLevel
                int newLevel = numHeads + 1;

                //check if it exceed the current level
                //if so, create a new level with only head node
                if (newLevel > head.getLevel()) {
                    Node<T> newHead = new Node<> (null, newLevel);
                    newHead.setDown(head);
                    head.setUp(newHead);
                    head = newHead;
                }

                //update the insert position
                while (insertAfter.getUp() == null) {
                    insertAfter = insertAfter.getPrev();
                }
                insertAfter = insertAfter.getUp();

                //insert the new node in the corresponding position
                newAddNode = new Node<T>(data, newLevel);



                if (insertAfter.getNext() != null) {
                    newAddNode.setNext(insertAfter.getNext());
                    insertAfter.getNext().setPrev(newAddNode);
                }

                insertAfter.setNext(newAddNode);
                newAddNode.setPrev(insertAfter);

                newAddNode.setDown(prevAddNode);
                prevAddNode.setUp(newAddNode);

                prevAddNode = newAddNode;
            }

            size++;
        }
    }



    /**
     * Search for the data.
     * If the data exists in the list, return the corresponding node at the bottom level
     * If the data does not exist, return the node with largest data value
     * which is smaller than input data.
     *
     *
     * @param data the data to be searched
     */
    private Node<T> search (T data) {
        Node<T> curr;
        curr = head;
        boolean findPosition = false;

//        //FIXME so weird
//        while (!findPosition) {
//            if (curr.getNext() != null) {
//                int comp = data.compareTo(curr.getNext().getData());
//                if (comp == 0) {
//                    curr = curr.getNext();
//                    while (curr.getDown() != null) {
//                        curr = curr.getDown();
//                    }
//                    findPosition = true;
//                } else if (comp > 0) {
//                    curr = curr.getNext();
//                } else {
//                    if (curr.getDown()!=null) {
//                        curr = curr.getDown();
//                    } else {
//                        findPosition = true;
//                    }
//                }
//            } else {
//                if (curr.getDown()!=null) {
//                    curr = curr.getDown();
//                } else {
//                    findPosition = true;
//                }
//            }
//        }


        while (!findPosition) {
            Node<T> next = curr.getNext();
            if (next == null || (next != null && data.compareTo(next.getData()) < 0)) {
                if (curr.getDown() != null) {
                    curr = curr.getDown();
                } else {
                    findPosition = true;
                }
            } else {
                int comp = data.compareTo(next.getData());
                if (comp == 0) {
                    curr = next;
                    while (curr.getDown() != null) {
                        curr = curr.getDown();
                    }
                    findPosition = true;
                } else {
                    curr = next;
                }
            }
        }

        return curr;
    }


    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data is null");
        }

//        Node<T> curr =head;
//        boolean reachBottom = false;
//
//        while (!reachBottom) {
//            if (curr.getNext() != null) {
//                int comp = data.compareTo(curr.getNext().getData());
//                if (comp == 0) {
//
//                } else if (comp > 0) {
//                    curr = curr.getNext();
//                } else {
//                    if (curr.getDown() != null) {
//                        curr = curr.getDown();
//                    } else {
//                        reachBottom = true;
//                    }
//                }
//            } else {
//                if (curr.getDown() != null) {
//                    curr = curr.getDown();
//                } else {
//                    reachBottom = true;
//                }
//            }
//
//        }



        Node<T> position = search(data);
        if(position.getData() != null && position.getData().compareTo(data) == 0) {
            Node<T> curr = position;
            curr.getPrev().setNext(curr.getNext());
            if (curr.getNext() != null) {
                curr.getNext().setPrev(curr.getPrev());
            }

            // to delete the above position
            while (curr.getUp() != null) {
                Node<T> currUp = curr.getUp();
                if (currUp.getPrev().equals(head) && currUp.getNext() == null) {
                    int level= curr.getLevel();
                    for (int i = head.getLevel(); i > level; i--) {
                        head = head.getDown();
                    }
                    head.setUp(null);
                    curr.setUp(null);
                } else {
                    currUp.getPrev().setNext(currUp.getNext());
                    if (currUp.getNext() != null) {
                        currUp.getNext().setPrev(currUp.getPrev());
                    }
                    curr = currUp;
                }
            }

            size--;
            return curr.getData();







//            Node<T> topPosition = position;

            //delete from the topmost layer
//            while (topPosition.getUp() != null) {
//                topPosition = topPosition.getUp();
//            }
//
//
//            while(topPosition!= null) {
//                //only 1 node case
//                if (topPosition.getPrev().equals(head) && topPosition.getNext() == null) {
//                    topPosition.getDown().setUp(null);
//                    head.getDown().setUp(null);
//                    head = head.getDown();
//                } else {
//                    topPosition.getPrev().setNext(topPosition.getNext());
//                    if (topPosition.getNext() != null) {
//                        topPosition.getNext().setPrev(topPosition.getPrev());
//                    }
//                    if (topPosition.getDown() != null) {
//                        topPosition.getDown().setUp(null);
//
//                    }
//                }
//                topPosition = topPosition.getDown();
//            }
//            size--;

        } else {
            throw new NoSuchElementException("The data is not in the list");
        }

    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data is null");
        }

        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;

//        boolean reachBottom = false;
//        boolean findData = false;
//
//        Node<T> curr = head;
//
//
//
//        while (!findData && !reachBottom) {
//            if (curr.getNext() != null) {
//                int comp = data.compareTo(curr.getNext().getData());
//                if (comp == 0) {
//                    findData = true;
//                } else if (comp > 0) {
//                    curr = curr.getNext();
//                } else {
//                    if (curr.getDown() != null) {
//                        curr = curr.getDown();
//                    } else {
//                        reachBottom = true;
//                    }
//                }
//            } else {
//                if (curr.getDown() != null) {
//                    curr = curr.getDown();
//                } else {
//                    reachBottom = true;
//                }
//            }
//        }
//
//        return findData;

    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data is null");
        }

//        boolean reachBottom = false;
//
//
//        Node<T> curr = head;
//
//
//        while (!reachBottom) {
//            if (curr.getNext() != null) {
//                int comp = data.compareTo(curr.getNext().getData());
//                if (comp == 0) {
//                    return curr.getNext().getData();
//                } else if (comp > 0) {
//                    curr = curr.getNext();
//                } else {
//                    if (curr.getDown() != null) {
//                        curr = curr.getDown();
//                    } else {
//                        reachBottom = true;
//                    }
//                }
//            } else {
//                if (curr.getDown() != null) {
//                    curr = curr.getDown();
//                } else {
//                    reachBottom = true;
//                }
//            }
//        }


        Node<T> node = search(data);
        if (node.getData() != null && node.getData().equals(data)) {
            return node.getData();
        }

        throw new NoSuchElementException("The data is not in the list");

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = new Node<>(null,1);
        size = 0;
    }

    @Override
    public Set<T> dataSet() {
        Set<T> set =  new HashSet<>();
        Node<T> curr = head;
        while (curr.getDown() != null) {
            curr = curr.getDown();
        }

        while (curr.getNext() != null) {
            curr = curr.getNext();
            set.add(curr.getData());
        }
        return set;
    }

    @Override
    public Node<T> getHead() {
        return head;
    }

}
