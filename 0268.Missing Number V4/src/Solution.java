class Solution {
	/**
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find
	 * the one that is missing from the array.
	 * 
	 * Gaussian sum = n * (n + 1) / 2, which calculates the sum of 0 to n.
	 * 
	 * We can compute the sum of nums, and by Gauss' formula, we can compute the sum
	 * of the first n natural numbers. Therefore, the number that is missing is
	 * simply the result of Gauss' formula minus the sum of nums, as nums consists of
	 * the first n natural numbers minus some number.
	 * 
	 * e.g., nums = {3, 0, 1}, then gaussian sum = (3 * 4) / 2 = 6, and 
	 * nums sum = 3 + 0 + 1 = 4. The missing number = 6 - 4 = 2.
	 * 
	 * Time complexity is O(n) because it takes O(1) time to compute Gaussian sum and
	 * O(n) to compute nums sum.
	 * 
	 * @param nums given array
	 * @return the missing number from the array
	 */
    public int missingNumber(int[] nums) {
        int size = nums.length;
        int gaussianSum = size * (size + 1) >> 1;
        int numsSum = 0;
        for(int i = 0; i < size; i++){
            numsSum += nums[i];
        }
        return gaussianSum - numsSum;
    }
}