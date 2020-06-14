/**
 * Implement Solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Use least-significant digit radix sort to sort the array. Then find the maximum
	 * gap. Time complexity is O(n * k), where k is the number of digits that the
	 * maximum number has, which is a constant.
	 * 
	 * @param nums
	 * @return
	 */
    public int maximumGap(int[] nums) {
        int size = nums.length;
        int maxGap = 0;
        
        if(size < 2){
            return maxGap;
        }
        
        leastSignificantDigitRadixSort(nums, size);
        
        maxGap = nums[1] - nums[0];
        for(int i = 2; i < size; i++){
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        
        return maxGap;
    }
    
    /**
     * Implement radix sort with least significant digit.
     * 
     * @param array
     * @param size
     */
    private void leastSignificantDigitRadixSort(int[] array, int size){
        /*
         * Obtain the maximum number in array.
         */
        int maxNumber = array[0];
        for(int i = 1; i < size; i++){
            maxNumber = Math.max(maxNumber, array[i]);
        }
        
        /*
         * 10 digits.
         */
        final int RADIX = 10;
        
        /*
         * Array to store values for each iteration.
         */
        int[] arrayToStoreIterativeValues = new int[size];
        
        /*
         * Radix sort from least significant digit to most significant digit.
         */
        for(int divisor = 1; maxNumber/divisor > 0; divisor *= 10){
            /*
             * Array to store the frequency of each digit of each number in variable array
             */
            int[] count = new int[RADIX];
            
            /*
             * Count the frequency of each digit of each number. Digit is the index to 
             * access count array. 
             */
            for(int i = 0; i < size; i++){
                count[obtainDigit(array[i], divisor)]++;
            }
            
            /*
             * Sum up the count array. Now the count array stores the indices of 
             * arrayToStoreIterativeValues.
             */
            for(int i = 1; i < RADIX; i++){
                count[i] += count[i - 1];
            }
            
            /*
             * Store the numbers being sorted based on the current digit using 
             * arrayToStoreIterativeValues. Digit is the index to access count array.
             */
            for(int i = size - 1; i >= 0; i--){
                arrayToStoreIterativeValues[--count[obtainDigit(array[i], divisor)]] = 
                    array[i];
            }
            
            /*
             * Copy back from arrayToStoreIterativeValues to array.
             */
            for(int i = 0; i < size; i++){
                array[i] = arrayToStoreIterativeValues[i];
            }
        }
    }
    
    /**
     * Obtain digit of a number given divisor.
     * 
     * @param number given number
     * @param divisor divisor
     * @return digit of a number based on divisor
     */
    private int obtainDigit(int number, int divisor){
        return (number / divisor) % 10;
    }
}