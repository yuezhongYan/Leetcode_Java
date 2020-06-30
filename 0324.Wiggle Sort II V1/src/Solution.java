import java.util.concurrent.ThreadLocalRandom;

/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]...
	 * 
	 * This function is implemented by the following steps:
	 * 1. Sort the array.
	 * 2. Divide the sorted array into two.
	 * 3. Traverse the two split arrays from end to start and store every element in these two arrays
	 * into aux array.
	 * 4. Copy back from aux array to nums array.
	 * 
	 * Total time complexity is O(n log n + n) = O(n log n), where the first term (n log n) refers to the
	 * time complexity of quick sort in the average case, and the second term is the time complexity to
	 * traverse the two split parts of nums.
	 * 
	 * @param nums given unsorted array
	 */
    public void wiggleSort(int[] nums) {
        int numsSize = nums.length;
        
        quickSort(nums, 0, numsSize - 1);
        
        int middle = (numsSize - 1) >> 1;
        int right = numsSize - 1;
        int[] aux = new int[numsSize];
        
        /*
         * Index for aux.
         */
        int i = 0;
        
        while(middle >= 0 || right > (numsSize - 1) >> 1){
            if((i & 1) == 0){
            	aux[i] = nums[middle];
                middle--;
            }else{
            	aux[i] = nums[right];
                right--;
            }
            i++;
        }
        
        for(int j = 0; j < numsSize; j++){
            nums[j] = aux[j];
        }
    }
    
    /**
     * Implement quick sort.
     * 
     * @param nums given unsorted array
     * @param start start index
     * @param end end index
     */
    private void quickSort(int[] nums, int start, int end){
        if(start >= end){
            return;
        }

        int pivotIndex = ThreadLocalRandom.current().nextInt(start, end + 1);
        int pivot = nums[pivotIndex];
        
        /*
         * Swap nums[pivotIndex] with nums[start]. Now pivot is on the left.
         */
        swap(nums, pivotIndex, start);
        
        int left = start + 1;
        int right = end;
        
        /*
         * Partition.
         */
        while(left <= right){
            while(left <= right && nums[left] <= pivot){
                left++;
            }
            
            while(left <= right && nums[right] > pivot){
                right--;
            }
            
            if(left < right){
                /*
                 * Swap nums[left] with nums[right].
                 */
                swap(nums, left, right);
                
                left++;
                right--;
            }
        }
        
        /*
         * Swap nums[start] with nums[right]. Now pivot is nums[right].
         */
        swap(nums, start, right);
        
        quickSort(nums, start, right - 1);
        quickSort(nums, right + 1, end);
    }
    
    /**
     * Implement swap.
     * 
     * @param nums given unsorted array
     * @param i an index to one element to be swapped
     * @param j an index to another element to be swapped
     */
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}