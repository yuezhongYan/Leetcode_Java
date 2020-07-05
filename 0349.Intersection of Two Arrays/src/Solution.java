import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given two arrays, compute their intersection by following steps:
	 * 1. Remove duplicates for given arrays.
	 * 2. Compute their intersection.
	 * 
	 * Total time complexity is O(n + m), where n, m are the lengths of two given arrays.
	 * 
	 * @param nums1 a given array
	 * @param nums2 the other given array
	 * @return an array that stores the intersection of the given arrays.
	 */
    public int[] intersection(int[] nums1, int[] nums2) {
    	/*
    	 * Remove duplicates using Set.
    	 */
        Set<Integer> set1 = convert(nums1);
        Set<Integer> set2 = convert(nums2);
        
        /*
         * Compute the intersection of given arrays.
         */
        set1.retainAll(set2);
        
        int[] result = new int[set1.size()];
        int i = 0;
        Iterator<Integer> iterator = set1.iterator();
        while(iterator.hasNext()){
            result[i] = iterator.next();
            i++;
        }
        
        return result;
    }
    
    /**
     * Convert array into Set.
     * 
     * @param nums given array
     * @return Set that stores elements from the array without duplicates
     */
    private Set<Integer> convert(int[] nums){
        Set<Integer> set = new HashSet<>();
        int numsSize = nums.length;
        for(int i = 0; i < numsSize; i++){
            set.add(nums[i]);
        }
        return set;
    }
}