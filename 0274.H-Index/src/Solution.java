import java.util.Random;

/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given an array of citations (each citation is a non-negative integer) of a researcher, compute the
	 * researcher's h-index.
	 * A scientist has index h if h of his/her N papers have at least h citations each, and the other
	 * N - h papers have no more than h citations each.
	 * 
	 * This algorithm has 2 steps to compute h-index:
	 * 1. Sort citations array in reversed order.
	 * 2. Traverse the sorted array. If the current citation is greater than the current index, h-index 
	 * increments.
	 * 
	 * The time complexity for reversed quick sort is O(n log n) in average case, O(n^2) in the worst
	 * case if the pivot in reversed quick sort is always picked at both ends. The time complexity for
	 * traversal is O(n). Thus, the total time complexity is O(n log n) + O(n) = O(n log n).
	 * 
	 * @param citations array of citations
	 * @return h-index
	 */
    public int hIndex(int[] citations) {
        int size = citations.length;
        reversedQuickSort(citations, 0, size - 1);
        
        /*
         * Traverse the reversed-ordered citations. If the current citation is greater than
         * the current index, h index increments.
         */
        int hIndex = 0;
        for(int i = 0; i < size; i++){
            if(citations[i] > i){
                hIndex++;
            }
        }
        
        return hIndex;
    }
    
    private void reversedQuickSort(int[] citations, int start, int end){
        if(start >= end){
            return;
        }
        
        Random random = new Random();
        int pivotIndex = random.nextInt(end - start + 1) + start;
        int pivot = citations[pivotIndex];
        
        /*
         * Swap citations[pivotIndex] with citations[start]. Now pivot is on the left.
         */
        int temp = citations[pivotIndex];
        citations[pivotIndex] = citations[start];
        citations[start] = temp;
        
        int left = start + 1;
        int right = end;
        
        /*
         * Partition
         */
        while(left <= right){
            while(left <= right && citations[left] > pivot){
                left++;
            }
            
            while(left <= right && citations[right] <= pivot){
                right--;
            }
            
            if(left < right){
                /*
                 * Swap citations[left] with citations[right].
                 */
                int temp2 = citations[left];
                citations[left] = citations[right];
                citations[right] = temp2;
                
                left++;
                right--;
            }
        }
        
        /*
         * Swap citations[right] with citations[start]. Now pivot is on the right.
         */
        int temp3 = citations[right];
        citations[right] = citations[start];
        citations[start] = temp3;
        
        reversedQuickSort(citations, start, right - 1);
        reversedQuickSort(citations, right + 1, end);
    }
}