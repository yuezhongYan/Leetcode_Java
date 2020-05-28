import java.util.HashSet;
import java.util.Set;

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
	 * First, we insert nums into a hash set. Then, we use an identifier i that goes
	 * from 0 to n to check if i is in the hash set. The missing number must
	 * somewhere between (but not including) 0 and n. To find it, we ensure that the
	 * number we expect to be at each index is indeed there. As soon as we find an
	 * unexpected number, we can simply return the expected number. Time complexity
	 * is O(n), where insertion takes O(n) and for loop takes O(n) with contains()
	 * taking O(1) since it is a set. The sum of these operations is O(2n) => O(n).
	 * 
	 * @param nums given array
	 * @return the missing number from the array
	 */
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        
        int size = nums.length;
        for(int i = 0; i < size; i++){
            set.add(nums[i]);
        }
        
        int missingNumber = -1;
        boolean isBreak = false; 
        for(int i = 0; i < size; i++){
            if(!set.contains(i)){
                missingNumber = i;
                isBreak = true;
                break;
            }
        }
        
        if(!isBreak){
            missingNumber = size;
        }
        
        return missingNumber;
    }
}