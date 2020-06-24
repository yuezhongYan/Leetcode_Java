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
	 * This algorithm finds h-index by implementing bucket sort. 
	 * 
	 * 1st loop: Traverse citations array.
	 * This algorithm first creates a bucket, which stores the numbers of occurrences of each citation in
	 * citations array. Its index numbers are from 0 to n corresponding to each citation from citations
	 * array with (n + 1) size. The number of occurrences is initially 0. If a citation is greater than
	 * the size of citations array, then the number of occurrences increments at the highest index of
	 * buckets array(i.e. the highest index of buckets array is equal to the size of citations array).
	 * Otherwise, increment the number of occurrences at the index number of buckets array corresponding
	 * to the citation from citations array.
	 * 
	 * 2nd loop: Traverse buckets array.
	 * Then, traverse the buckets array from end to start. Sum up each element from the buckets array.
	 * The total sum is actually the h-index. If the total sum is greater than or equal to the current
	 * index of the bucket array, then the h-index is the current index of the bucket array. The reason
	 * why to traverse from end to start is to find the greatest index.
	 * 
	 * e.g., citations = {3, 0, 6, 1, 5}. Then buckets = {1, 1, 0, 1, 0, 2}
	 *                                    bucket index =  0  1  2  3  4  5
	 *                                    
	 * Bucket index corresponds to 3, 0, 6, 1, 5 from citations array. The numbers of occurrences for
	 * each bucket index are 1, 1, 0, 1, 0, 2. buckets[5] = 2 because it increments twice when
	 * citation = 6 then 5.
	 * 
	 * The total time complexity is O(n + n) = O(n), where the first n is the time to traverse citations
	 * array and the second n is the time to traverse buckets array.
	 * 
	 * @param citations array of citations
	 * @return h-index
	 */
    public int hIndex(int[] citations) {
        int size = citations.length;
        int[] buckets = new int[size + 1];
        
        /*
         * Traverse citations array.
         */
        for(int citation : citations){
            if(citation > size){
            	/*
            	 * If a citation is greater than the size of citations array, then the number of occurrences
            	 * increments at the highest index of buckets array(i.e. the highest index of buckets array is equal
            	 * to the size of citations array).
            	 */
                buckets[size]++;
            }else{
            	/*
            	 * Otherwise, increment the number of occurrences at the index number of buckets array corresponding
	             * to the citation from citations array.
            	 */
                buckets[citation]++;
            }
        }
        
        int hIndex = 0;
        
        /*
         * Traverse buckets array.
         */
        for(int i = size; i >= 0; i--){
            hIndex += buckets[i];
            
            /*
             * If the total sum(h-index) is greater than or equal to the current index of the bucket array, then
             * the h-index is the current index of the bucket array.
             */
            if(hIndex >= i){
                hIndex = i;
                break;
            }
        }
        
        return hIndex;
    }
}