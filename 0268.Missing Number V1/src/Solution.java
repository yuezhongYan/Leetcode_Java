import java.util.Arrays;

/**
 * Solution implementation
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find
	 * the one that is missing from the array.
	 * 
	 * First, we sort nums. Then, we ensure that 0 is at the beginning and that n is
	 * at the end. The missing number must somewhere between (but not including) 0
	 * and n. To find it, we ensure that the number we expect to be at each index is
	 * indeed there. As soon as we find an unexpected number, we can simply return
	 * the expected number. Time complexity is O(n log n), where sort() takes
	 * O(n log n) and for loop takes O(n).
	 * 
	 * @param nums given array
	 * @return the missing number from the array
	 */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        
        int size = nums.length;
        int missingNumber = -1;
        boolean isBreak = false;
        
        /*
         * If we find the unexpected number, then the index is the missing number.
         */
        for(int i = 0; i < size; i++){
            if(nums[i] != i){
                missingNumber = i;
                isBreak = true;
                break;
            }
        }
        
        /*
         * Handle the sorted array with missing number being 0 or n.
         */
        if(!isBreak){
            missingNumber = size;
        }
        
        return missingNumber;
    }
}