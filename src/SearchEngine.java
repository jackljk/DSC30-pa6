/*
 * Name: Jack Kai Lim
 * PID:  A16919063
 */

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Search Engine implementation.
 * 
 * @author Jack Kai Lim
 * @since  05/04/2022
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String[] cast = scanner.nextLine().split(" ");
                String[] studios = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();
                /* Uses a helper function to populate the trees */
                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees
                populateSearchTreeHelper(movieTree, cast, movie);
                populateSearchTreeHelper(studioTree, studios, movie);
                populateSearchTreeHelper(ratingTree, cast, rating);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper function for populating the trees
     *
     * @param tree The tree to populate
     * @param keys The keys to be added
     * @param value THe value correlated to the key
     */
    private static void populateSearchTreeHelper(BSTree<String> tree, String[] keys,
                                                    String value){
        value = value.toLowerCase();
        for (String key : keys) {
            key = key.toLowerCase();
            //Since the keys are in arrays we will iterate through the keys
            if (tree.findKey(key)) {
                //If the key already exist
                if (!tree.findDataList(key).contains(value)) {
                    //If the value is not in the Linked-list of the key adds it to the list
                    tree.insertData(key, value);
                }
            } else {
                //Key is not in the tree, therefore adds the key
                tree.insert(key);
                if (!tree.findDataList(key).contains(value)) {
                    //If the value is not in the Linked-list of the key adds it to the list
                    tree.insertData(key, value);
                }
            }
        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        /* Searches the binary search tree for the values of the keys which is given by the query */
        // process query
        String[] keys = query.toLowerCase().split(" ");
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        LinkedList<String> arrInter = new LinkedList<String>();
        try{
            arrInter.addAll(searchTree.findDataList(keys[0]));
            for (int i = 1;i<keys.length;i++){
                arrInter.retainAll(searchTree.findDataList(keys[i]));
            }
            print(query, arrInter);
        } catch (IllegalArgumentException e) {
            print(query, null);
        }

        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        if (keys.length > 1){//Only searches individual outputs if there is more than one key
            LinkedList<String> arr = new LinkedList<String>();
            for (String key : keys) {
                try{
                    //Loops through the keys arr if it has more than one value
                    arr.addAll(searchTree.findDataList(key));//Adds all key values to the arr
                    arr.removeAll(arrInter);//Removes all values that overlap with the intersected arr
                    if (!arr.isEmpty()){
                        print(key, arr);//prints the results
                        arrInter.addAll(arr);
                    }
                } catch (IllegalArgumentException e) {
                    print(key, null);
                }
            }
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /* Implements main for the search engine */
        // initialize search trees
        BSTree<String> movieTree = new BSTree<String>();
        BSTree<String> studioTree = new BSTree<String>();
        BSTree<String> ratingTree = new BSTree<String>();

        // process command line arguments
        int ARG_QUERY_START = 2;
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = "";
        for (int i = ARG_QUERY_START;i<args.length;i++){
            //Adds all the query's into one string
            query = query + args[i] + " ";
        }
        query = query.toLowerCase();


        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        // choose the right tree to query
        int ACTOR_RATING = 2;
        if (searchKind == 0){
            searchMyQuery(movieTree, query);
        } else if (searchKind == 1){
            searchMyQuery(studioTree, query);
        } else if (searchKind == ACTOR_RATING){
            searchMyQuery(ratingTree, query);
        }

    }
}
