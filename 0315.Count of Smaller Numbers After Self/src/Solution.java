import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * You are given an integer array nums and you have to return a new counts array. The counts array
	 * has the property where counts[i] is the number of smaller elements to the right of nums[i].
	 * 
	 * Traverse from nums[n - 1] to nums [0]. Build a binary search tree to store the following
	 * attributes:
	 * 1. key: store nums[i].
	 * 2. left: store left child of key.
	 * 3. right: store right child of key.
	 * 4. count: store the number of elements to the right of node. If key == root.key, there will be
	 * count numbers of smaller numbers to the right.
	 * 
	 * e.g., nums = [5, 2, 6, 1], then we have 1 count[0] = 0
	 *                                          \
	 *                                          6 count[1] = 1
	 *                                         /
	 *                                         2 count[2] = 1
	 *                                          \
	 *                                          5 count[3] = 2
	 * count = [0, 1, 1, 2]. After being reversed, it turns out to be [2, 1, 1, 0].
	 * 
	 * Total time complexity is O(n log n + n), where the first term refers to n iterations with each
	 * log n number of key comparisons and the second term is the time complexity of
	 * Collections.reverse() of reversed counts array. 
	 * 
	 * @param nums an integer array
	 * @return counts array
	 */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<Integer>();
        
        if(nums.length == 0){
            return counts;
        }
        
        /*
         * Handle the case of 0 at the beginning of counts array to avoid 0 missing at the end after being
         * reversed.
         */
        counts.add(0);
        
        int numsSize = nums.length;
        ModifiedBinarySearchTree root = new ModifiedBinarySearchTree(nums[numsSize - 1]);
        
        /*
         * Traverse
         */
        for(int i = numsSize - 2; i >= 0; i--){
            int count = root.insertNode(root, nums[i]);
            counts.add(count);
        }
        
        /*
         * Reverse
         */
        Collections.reverse(counts);
        
        return counts;
    }
}

/**
 * Implement modified binary search tree.
 * 
 * @author YuezhongYan
 *
 */
class ModifiedBinarySearchTree{
	/**
	 * Value of current node.
	 */
    int key;
    
    /**
     * Left child of current node.
     */
    ModifiedBinarySearchTree left;
    
    /**
     * Right child of current node.
     */
    ModifiedBinarySearchTree right;
    
    /**
     * Counter to count the number of smaller elements to the right of the current node.
     */
    int count = 1;
    
    public ModifiedBinarySearchTree(int key){
        this.key = key;
    }
    
    /**
     * Implement insert node.
     * 
     * @param root current node of binary search tree
     * @param keyToBeInserted key to be inserted
     * @return the number of smaller elements to the right of the current node
     */
    int insertNode(ModifiedBinarySearchTree root, int keyToBeInserted){
    	/*
    	 * The number of smaller elements to the right of the current node.
    	 */
        int currentCount = 0;
        
        while(true){
            if(root.key < keyToBeInserted){
                currentCount += root.count;
                if(root.right == null){
                    root.right = new ModifiedBinarySearchTree(keyToBeInserted);
                    break;
                }else{
                    root = root.right;
                }
            }else{
                root.count++;
                if(root.left == null){
                    root.left = new ModifiedBinarySearchTree(keyToBeInserted);
                    break;
                }else{
                    root = root.left;
                }
            }
        }
        
        return currentCount;
    }
}