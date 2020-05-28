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
	 * Because we know that nums contains n numbers and that it is missing exactly
	 * one number on the range [0..n-1], we know that n definitely replaces the
	 * missing number in nums. Therefore, if we initialize an integer to n and XOR it
	 * with every index and value, we will be left with the missing number.
	 * Mathematically, it is n = n ^ (nums[i] ^ i), where i is the counter for nums.
	 * 
	 * e.g., nums = {3, 0, 1}, then missing number is 2, which is n ^ (nums[i] ^ i)
	 * with n = 3 initially.
	 * 
	 * Time complexity is O(n) in the worst case because it takes O(1) for each
	 * iteration.
	 * 
	 * @param nums
	 * @return
	 */
    public int missingNumber(int[] nums) {
        int size = nums.length;
        int missingNumber = nums.length;
        for(int i = 0; i < size; i++){
            missingNumber ^= i ^ nums[i];
        }
        return missingNumber;
    }
}