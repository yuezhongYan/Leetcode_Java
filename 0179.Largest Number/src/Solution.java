class Solution {
	/**
	 * Given a list of non negative integers, arrange them such that they form the largest number with
	 * merge sort.
	 * 
	 * e.g., Input: [3,30,34,5,9]
     *       Output: "9534330"
     * 
     * Time complexity is O(n log n) because merge sort takes O(n log n) in the worst case.
	 * 
	 * @param nums given non negative integers
	 * @return the largest number as a String
	 */
    public String largestNumber(int[] nums) {
        /*
         * Handle the case where all numbers are 0's. Here, the output is 0.
         */
        boolean isAllZeros = true;
        for(int num : nums){
            if(num != 0){
                isAllZeros = false;
            }
        }
        
        if(isAllZeros){
            return Integer.toString(0);
        }
        
        mergeSort(nums, 0, nums.length - 1);
        
        StringBuilder sb = new StringBuilder();
        for(int number : nums){
            sb.append(number);
        }
        
        return sb.toString();
    }
    
    /**
     * Implement merge sort.
     * 
     * @param nums given non negative integers
     * @param left left pointer of the given non negative integers
     * @param right right pointer of the given non negative integers
     */
    private void mergeSort(int[] nums, int left, int right){
        if(nums.length < 2){
            return;
        }
        
        /*
         * Create left sub-array.
         */
        int leftSubarraySize = nums.length >> 1;
        int[] leftSubarray = new int[leftSubarraySize];
        for(int i = 0; i < leftSubarraySize; i++){
            leftSubarray[i] = nums[i];
        }
        
        /*
         * Create right sub-array.
         */
        int rightSubarraySize = nums.length - leftSubarraySize;
        int[] rightSubarray = new int[rightSubarraySize];
        for(int i = 0; i < rightSubarraySize; i++){
            rightSubarray[i] = nums[i + leftSubarraySize];
        }
        
        int middle = left + (right - left) >> 1;
        mergeSort(leftSubarray, left, middle);
        mergeSort(rightSubarray, middle + 1, right);
        merge(leftSubarray, leftSubarraySize, rightSubarray, rightSubarraySize, nums);
    }
    
    /**
     * Implement modified merge.
     * 
     * @param leftSubarray left sub-array
     * @param leftSubarraySize left sub-array size
     * @param rightSubarray right sub-array
     * @param rightSubarraySize right sub-array size
     * @param result result array containing the elements from left sub-array and right sub-array sorted
     */
    private void merge(int[] leftSubarray, int leftSubarraySize, int[] rightSubarray, 
                       int rightSubarraySize, int[] result){
        /*
         * index for left sub-array.
         */
        int i = 0;
        
        /*
         * index for right sub-array.
         */
        int j = 0;
        
        /*
         * index for result array.
         */
        int k = 0;
        
        while(i < leftSubarraySize && j < rightSubarraySize){
        	/*
        	 * Implement modified merging process with compare method that obtains the larger combination. 
        	 * e.g., Input: [3, 30], then there are two combinations, 330 and 303. To obtain 330, 3 comes into
        	 * result array first then 30.
        	 */
            if(compare(leftSubarray[i], rightSubarray[j])){
            	/*
            	 * Move the elements of left sub-array at the end of result
            	 */
                result[k] = leftSubarray[i];
                i++;
            }else{
            	/*
            	 * Move the elements of right sub-array at the end of result
            	 */
                result[k] = rightSubarray[j];
                j++;
            }
            k++;
        }
        
        /*
         * Move the remaining elements of left sub-array to result
         */
        while(i < leftSubarraySize){
            result[k] = leftSubarray[i];
            i++;
            k++;
        }
        
        /*
         * Move the remaining elements of right sub-array to result
         */
        while(j < rightSubarraySize){
            result[k] = rightSubarray[j];
            j++;
            k++;
        }
    }
    
    /**
     * Compare combinations of the number from left sub-array with the one from right. If the combination
     * of the number from left sub-array and the one from right is greater than the combination of the
     * number from right sub-array and the one from left, return true. Otherwise, return false.
     * 
     * @param numberFromLeft number from left sub-array
     * @param numberFromRight number from right sub-array
     * @return true if the left-right combination is greater than the right-left one, false otherwise
     */
    private boolean compare(int numberFromLeft, int numberFromRight){
        StringBuilder leftRightCombination = new StringBuilder();
        leftRightCombination.append(numberFromLeft);
        leftRightCombination.append(numberFromRight);
        
        StringBuilder rightLeftCombination = new StringBuilder();
        rightLeftCombination.append(numberFromRight);
        rightLeftCombination.append(numberFromLeft);
        
        return Long.parseLong(leftRightCombination.toString()) > 
            Long.parseLong(rightLeftCombination.toString());
    }
}