/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
	 * 
	 * Divide the array into 2 sub-arrays until each sub-array is single element long and merging these
	 * sublists recursively that results in the final sorted array.
	 * 
	 * Modify mergeSort that takes parameters an array and left and right indices:
	 *     -If left >= right, elements can no longer be broken further and hence return 0.
	 *     -Otherwise, set middle = left + ((right - left) >> 1).
	 *     -Store importantReversePairCount by recursively calling mergeSort on range [left,middle] and
	 *      [mid+1,end] and adding the results. This is the divide step on our routine, breaking it into
	 *      the 2 ranges, and finding the results for each range separately.
	 *     -Now, we have separately calculated the results for ranges [start, middle] and
	 *      [middle + 1, end], but we still have to count the elements in [start, middle] that are
	 *      greater than 2 * elements in [middle + 1, end]. Count all such elements and add the result to
	 *      importantReversePairCount.
	 *     -Finally, merge the array from left to right:
	 *         -Make 2 sub-arrays : elements in range [start, middle] and elements in range
	 *          [middle + 1, end].
	 *         -Keep pointers i and j to left sub-array and right sub-array respectively both initialized
	 *          to start.
	 *         -Iterate over k from left to right and set result[k] to the smaller of nums[i] or nums[j]
	 *          and increment the respective index.
	 * 
	 * Total time complexity is O(n log n), where it takes O(log n) to divide the array into 2 
	 * sub-arrays and O(n) to count the the number of important reverse pairs and to merge the 2
	 * sub-arrays after sorting.
	 * 
	 * @param nums given array
	 * @return the number of important reverse pairs in the given array.
	 */
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }
    
    private int mergeSort(int[] nums, int left, int right){
        if(left >= right){
            return 0;
        }
        
        int middle = left + ((right - left) >> 1);
        
        /*
         * Current count of important reverse pairs.
         */
        int result = 0;
        
        result += mergeSort(nums, left, middle);
        result += mergeSort(nums, middle + 1, right);
        result += merge(nums, left, middle, right);
        return result;
    }
    
    private int merge(int[] nums, int left, int middle, int right){
        /*
         * index for left sub-array.
         */
        int i = left;
        
        /*
         * index for right sub-array.
         */
        int j = middle + 1;
        
        /*
         * Store merged array.
         */
        int[] result = new int[right - left + 1];
        
        /*
         * index for result array.
         */
        int k = 0;
        
        /*
         * Current count of important reverse pairs.
         */
        int importantReversePairCount = 0;
        
        /*
         * Obtain the total count of important reverse pairs. If the element from the left 
         * sub-array is less than the one from the right sub-array, then the index for the * 
         * left sub-array increments. Otherwise, the index for the right sub-array 
         * increments. Convert the elements from int to long from both sub-arrays to avoid 
         * overflow.
         */
        while(i < middle + 1 && j < right + 1){
            if((long) nums[i] > 2 * (long) nums[j]){
                importantReversePairCount += middle - i + 1;
                j++;
            }else{
                i++;
            }
        }
        
        /*
         * Reset the indices for left sub-array and right subarray.
         */
        i = left;
        j = middle + 1;
        
        while(i < middle + 1 && j < right + 1){
            if(nums[i] < nums[j]){
            	/*
            	 * Move the elements of left sub-array at the end of result
            	 */
                result[k] = nums[i];
                i++;
            }else{
            	/*
            	 * Move the elements of right sub-array at the end of result
            	 */
                result[k] = nums[j];
                j++;
            }
            k++;
        }
        
        /*
         * Move the remaining elements of left sub-array to result. Empty remaining of left 
         * sub-array.
         */
        while(i < middle + 1){
            result[k] = nums[i];
            i++;
            k++;
        }
        
        /*
         * Move the remaining elements of right sub-array to result. Empty remaining of 
         * right sub-array.
         */
        while(j < right + 1){
            result[k] = nums[j];
            j++;
            k++;
        }
        
        System.arraycopy(result, 0, nums, left, right - left + 1);
        return importantReversePairCount;
    }
}