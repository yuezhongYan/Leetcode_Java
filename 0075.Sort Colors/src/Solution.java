import java.security.SecureRandom;

/**
 * Solution implementation
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given an array with n objects colored red, white or blue, sort them in-place
	 * so that objects of the same color are adjacent, with the colors in the order
	 * red, white and blue. Here, we will use the integers 0, 1, and 2 to represent
	 * the color red, white, and blue respectively.
	 * 
	 * Since colors are represented in integers, this problem converts to sorting
	 * problem. Quick sort will be implemented here.
	 * 
	 * Time complexity of quick sort is O(n log n) in average and O(n^2) is the worst
	 * case.
	 * 
	 * @param nums given array
	 */
    public void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    
    /**
     * Implement quick sort.
     * 
     * @param nums given array
     * @param start start index of given array
     * @param end end index of given array
     */
    private void quickSort(int[] nums, int start, int end){
        if(start >= end){
            return;
        }
        
        SecureRandom sr = new SecureRandom();
        
        /*
		 * end - start is exclusive, so add 1 to make it inclusive.
		 * Add start at the end because start may be other values in addition to 0.
		 */
        int pivotIndex = sr.nextInt(end - start + 1) + start;
        
        int pivot = nums[pivotIndex];
        
        /*
         * Swap the pivot with the 0th element. Now pivot is left
         */
        int temp = nums[pivotIndex];
        nums[pivotIndex] = nums[start];
        nums[start] = temp;
        
        int left = start + 1;
        int right = end;
        
        /*
         * Partition
         */
        while(left <= right){
            /*
             * Find the first element that is greater than the pivot.
             */
            while(left <= right && nums[left] <= pivot){
                left++;
            }
            
            /*
             * Find the last element that is smaller than or equal to the pivot. 
             */
            while(left <= right && nums[right] > pivot){
                right--;
            }
            
            /*
             * Swap nums[left] and nums[right].
             */
            if(left < right){
                int temp2 = nums[left];
                nums[left] = nums[right];
                nums[right] = temp2;
                
                left++;
                right--;
            }
        }
        
        /*
         * Swap 0th element with array[right]. Now pivot is array[right].
         */
        int temp3 = nums[start];
        nums[start] = nums[right];
        nums[right] = temp3;
        
        quickSort(nums, start, right - 1);
        quickSort(nums, right + 1, end);
    }
}