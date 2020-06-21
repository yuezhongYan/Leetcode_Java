import java.util.TreeSet;

/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given an array of integers, find out whether there are two distinct indices i and j in the array
	 * such that the absolute difference between nums[i] and nums[j] is at most t and the absolute
	 * difference between i and j is at most k.
	 * 
	 * Implement with java.util.TreeSet using AVL Tree(initially empty).
	 * 
	 * e.g., nums = {1, 5, 9, 1, 5, 9}, k = 2, t = 3.
	 * index    nums[index]    AVL Tree
	 *   0           1            1
	 *   
	 *   1           5            1
	 *                             \
	 *                             5
	 *                             
	 *   2           9            5
	 *                           / \
	 *                           1 9
	 *                           
	 *   3           1            5 (1 replaces 1)
	 *                           / \
	 *                           1 9
	 *                           
	 *   4           5            5 (5 replaces 5)
	 *                           / \
	 *                           1 9
	 *                           
	 *   5           9            5 (9 replaces 9)
	 *                           / \
	 *                           1 9
	 * where k is the size of AVL Tree. 
	 * 
	 * Find the least element in the tree greater than or equal to nums[i].
	 * If the absolute difference between nums[i] and nums[j] is at most t and the absolute
	 * difference between i and j is at most k, return true. Otherwise add nums[i] into the tree.
	 * 
	 * Similarly, find the greatest element in the tree smaller than or equal to nums[i].
	 * Perform with exactly the same condition above.
	 * 
	 * If index is greater than or equal to k, then remove nums[i - k] for purpose of saving space. Try
	 * not to use n-size tree set.
	 * 
	 * Time complexity is O(n * log k), where n is the size of nums, and log k is the time to search
	 * using k-size AVL Tree.
	 * 
	 * @param nums given an array of integers
	 * @param k restriction on difference of indices
	 * @param t restriction on difference of values
	 * @return true or false
	 */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /*
         * Use tree set to remove duplicates in the set. The tricky part is to use Long, instead of Integer
         * in <>. I am not sure why it doesn't work when I use Integer.
         */
    	TreeSet<Long> treeSet = new TreeSet<Long>();
        
        boolean isContainedDuplicates = false;
        
        int size = nums.length;
        for(int i = 0; i < size; i++){
            /*
             * The least element in the set greater than or equal to the given one.
             */
            Long ceil = treeSet.ceiling((long)nums[i]);
            
            if(ceil != null && Math.abs(nums[i] - ceil) <= t){
                isContainedDuplicates = true;
                break;
            }
            
            /*
             * The greatest element in the set less than or equal to the given one.
             */
            Long floor = treeSet.floor((long)nums[i]);
            
            if(floor != null && Math.abs(nums[i] - floor) <= t){
                isContainedDuplicates = true;
                break;
            }
            
            treeSet.add((long)nums[i]);
            
            if(i >= k){
                treeSet.remove((long)nums[i - k]);
            }
        }
        
        return isContainedDuplicates;
    }
}