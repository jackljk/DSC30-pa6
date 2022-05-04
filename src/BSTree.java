/*
 * Name: Jack Kai Lim
 * PID:  A16919063
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Jack Kai Lim
 * @since  05/03/2022
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            /* Initializes a BST Node */
            this.key = key;
            this.dataList = dataList;
            this.left = left;
            this.right = right;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            /* Initializes a BST Node with no data-list */
            this.dataList = new LinkedList<T>();
            this.key = key;
            this.left = left;
            this.right = right;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            /* Getter method thta returns the key of the node */
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            /* Getter method that returns the left node */
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            /* Getter method that returns the right node */
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            /* Getter Method that returns the data-list of the node */
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            /* Setter method that sets the left node to a new node */
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            /* Setter method that sets the right node to a new right node */
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            /* Setter method that sets the data list to a new data list */
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            /* Adds new data to the data-list linked to this node */
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            /* Searches the Linkedlist to check if the data is in the list, if it is re  */
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        /* Initializes a new BST */
        this.nelems = 0;
        this.root = null;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        /* Getter method of the root of the tree */
        if (this.nelems == 0){
            return null;
        } else {
            return this.root;
        }
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        /* Getter method that returns the size of the BST */
        return this.nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        /* Inserts the new node at its correct position */
        if (key == null){
            //Throws if the key input is null
            throw new NullPointerException();
        } else {
            if (findKey(key)){
                //If key is in the BST return false
                return false;
            } else if (this.getSize() == 0){
                //If BSt is empty insert new node as the root
                this.root = new BSTNode(null, null, new LinkedList<T>(), key);
                this.nelems += 1;
                return true;
            } else {
                BSTNode curr = this.getRoot();
                while (curr != null){
                    /*
                    Traverses down the BST going left if the key is less than the current node
                    and right if it is larger than.
                    Stops when it reaches a null node.
                     */
                    if (key.compareTo(curr.getKey()) < 0){
                        //Less than the current key so checks to see if it can be added
                        if (curr.getLeft() != null){
                            //If node is not null means BST can traverse further therefore traverses
                            curr = curr.getLeft();
                        } else {
                            //If the node is null, then the new node can be inserted
                            curr.left = new BSTNode(null, null, key);
                            this.nelems += 1;
                            return true;
                        }
                    } else {
                        //Node is more than the current node therefore checks to see if it can be
                        // added on the right
                        if (curr.getRight() != null){
                            //If right is not null, therefore node can traverse further.
                            curr = curr.getRight();
                        } else {
                            //Node is null, therefore the new node can be added
                            curr.right = new BSTNode(null, null, key);
                            this.nelems += 1;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        /* Searches the BST to see of the key exist in the BST */
        if (key == null){
            //Throws exceptions if the key is null
            throw new NullPointerException();
        } else {
            BSTNode curr = this.getRoot();
            while (curr != null){
                //Traverses the node going left if key is less than curr and right otherwise
                if (curr.getKey() == key){
                    //If key of current is equal to key then returns true and stops the loop
                    return true;
                } else if (key.compareTo(curr.getKey()) < 0){
                    curr = curr.getLeft();
                } else {
                    curr = curr.getRight();
                }
            }
        }
        //Traversal ended and the key was not found therefore returns false
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        /* Inserts data to the keys data-list */
        if (key == null || data == null) {
            //Throws if key or data is null
            throw new NullPointerException();
        } else {
            BSTNode curr = this.getRoot();
            while (curr != null) {
                //Traverses the node going left if key is less than curr and right otherwise
                if (curr.getKey() == key) {
                    //If key of current is equal to key then accesses the Linked-list associated
                    // with it and adds the data into the Linked-list
                    curr.addNewInfo(data);
                    return;
                } else if (key.compareTo(curr.getKey()) < 0) {
                    curr = curr.getLeft();
                } else {
                    curr = curr.getRight();
                }
            }
            //Throws when the code exits the while loop meaning that the key does not exist in
            // the BST
            throw new IllegalArgumentException();
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        /* Returns the Linked-list associated with the node of the given key */
        if (key == null) {
            //Throws if key or data is null
            throw new NullPointerException();
        } else {
            BSTNode curr = this.getRoot();
            while (curr != null) {
                //Traverses the node going left if key is less than curr and right otherwise
                if (curr.getKey() == key) {
                    //If key of current is equal to key then returns the Linked-list associated
                    // with the Node.
                    return curr.getDataList();
                } else if (key.compareTo(curr.getKey()) < 0) {
                    curr = curr.getLeft();
                } else {
                    curr = curr.getRight();
                }
            }
            //Throws when the code exits the while loop meaning the key does not exist in the BST
            throw new IllegalArgumentException();
        }
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        /* If the root is null, the BSt is empty therefore returns -1 */
        if (this.getRoot() == null){
            return -1;
        } else {
            return findHeightHelper(this.getRoot()) - 1;
        }
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        /* Recurssively goes through the left and right of the tree, adding one each layer it
        passes, and returns the Max value gotten which will be the height of the tree. */
        if (root == null){
            return 0;
        } else {
            return 1 + Math.max(findHeightHelper(root.getLeft()),
                    findHeightHelper(root.getRight()));
        }
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        private Stack<BSTNode> nodes;

        /**
         * Constructor to initialize the stack for the iterator
         */
        public BSTree_Iterator() {
            /* Initializes the Iterators stack */
            this.nodes = new Stack<BSTNode>();
            BSTNode curr = getRoot();
            while (curr != null){
                this.nodes.push(curr);
                curr = curr.getLeft();
            }
        }

        /**
         * Checks to see if there is anything left to iterate through
         * @return false if stack is empty, true otherwise
         */
        public boolean hasNext() {
            /* Checks if there are any nodes left to iterate through */
            if (this.nodes.empty()){
                return false;
            } else {
                return true;
            }
        }

        /**
         * Gets the next element in the iteration through the BST
         * @return The next element
         */
        public T next() {
            /* Gets the next element by popping the top of the list and checks if there is a
            right branch to traverse */
            if (!hasNext()){
                //Throws if the stack is empty and there is nothing else to iterate
                throw new NoSuchElementException();
            } else {
                BSTNode popped = this.nodes.pop();//Gets the next node
                if (popped.getRight() != null){
                    //Checks if there is a right branch to traverse and traverses
                    BSTNode curr = popped.getRight();
                    while (curr != null){
                        this.nodes.push(curr);
                        curr = curr.getLeft();
                    }
                }
                //Returns the iterated node
                return popped.getKey();
            }
        }
    }

    /**
     * Allows us to access the iterator class to be used in the BST class
     * @return New BST iterator.
     */
    public Iterator<T> iterator() {
        /* To allow us to call the iterator class as part of the BST class */
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
