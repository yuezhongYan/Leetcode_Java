import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Given two arrays, write a function to compute their intersection. Each element in the result
	 * should appear as many times as it shows in both arrays.
	 * 
	 * This algorithm uses HashMap and ArrayList and has the following steps:
	 * 1. Store each element from the first array as keys and the frequencies of each element as values
	 * into a map.
	 * 2. Create an empty list to store their intersection that appears as many times as it shows in both
	 * arrays.
	 * 3. Traverse the second array. If an element from this array is contained in the map in step 1,
	 * then add this element to the list in step 2 and decrement its corresponding frequency until the
	 * frequency reaches 0.
	 * 4. Extract the elements from the list into a new array, which is the returned array.
	 * 
	 * Time complexity for this algorithm is O(m + n), where m, n are the sizes of the two given arrays.
	 * 
	 * @param nums1 first given array
	 * @param nums2 second given array
	 * @return the intersection of nums1 and nums2, showing as many times as it shows in both arrays.
	 */
    public int[] intersect(int[] nums1, int[] nums2) {
        /*
         * Declare and store Map<Element, frequency>
         */
        Map<Integer, Integer> nums1Map = new HashMap<>();
        for(int element : nums1){
            nums1Map.put(element, nums1Map.getOrDefault(element, 0) + 1);
        }
        
        List<Integer> intersection = new ArrayList<Integer>();
        
        for(int element : nums2){
            if(nums1Map.containsKey(element) && nums1Map.get(element) > 0){
                intersection.add(element);
                
                /*
                 * Decrement the frequency to hand the cases where the frequencies for nums2 
                 * are greater than the ones from nums1. The return size needs to have less 
                 * frequent elements selected between nums1 and nums2.
                 */
                nums1Map.put(element, nums1Map.get(element) - 1);
            }
        }
        
        int resultSize = intersection.size();
        int[] result = new int[resultSize];
        
        for(int i = 0; i < resultSize; i++){
            result[i] = intersection.get(i);
        }
        
        return result;
    }
}