class Solution {
	/**
	 * Use modified binary search to find the minimum element of the array.
	 * 
	 * If the size of the array is 1 or if the first element is less than the
	 * last(i. e., sorted without being rotated), then the minimum value is the first
	 * element in this array.
	 * 
	 * Compare the middle element with the last. If the middle element is greater
	 * than the last, then the turning point of the array is at the right of the
	 * middle element. Otherwise, it is at the left of that.
	 * 
	 * If the middle element is greater than the next element, then the minimum value
	 * is the next element. If the middle element is smaller than the previous
	 * element, then the minimum value is the previous element
	 * 
	 * @param nums given int array
	 * @return min the minimum integer in the array
	 */
    public int findMin(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int middle;
        int min = -1;
        
        /*
         * If the size of the array is 1 or the array is sorted without being rotated, 
         * then the minimum value is the first element.
         */
        if(nums.length == 1 || nums[i] < nums[j]){
            return nums[i];
        }
        
        while(i < j){
            middle = (i + j) >> 1;
            
            /*
             * Compare the middle element with the last. If the middle element is greater
             * than the last, then the turning point of the array is at the right of the
             * middle element. Otherwise, it is at the left of that.
             */
            if(nums[middle] > nums[j]){
                i = middle + 1;
            }else{
                j = middle - 1;
            }
            
            /*
             * If the middle element of the array is greater than its next element, then the
             * next element is the minimum value of the array. 
             */
            if(nums[middle] > nums[middle + 1]){
                min = nums[middle + 1];
                break;
            }
            
            /*
             * the middle element is the minimum value of the array.
             */
            if(nums[middle] < nums[middle - 1]){
                min = nums[middle];
                break;
            }
        }
        
        return min;
    }
}