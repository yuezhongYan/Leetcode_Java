import java.util.HashMap;
import java.util.Map;

/**
 * Implement solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Compute the number of car fleets that will arrive at the destination using Hash Map.
	 * 
	 * N cars are going to the same destination along a one lane road. The destination is target miles
	 * away.
	 * 
	 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i]
	 * miles towards the target along the road.
	 * 
	 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to
	 * bumper at the same speed.
	 * 
	 * The distance between these two cars is ignored - they are assumed to have the same position.
	 * 
	 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that
	 * a single car is also a car fleet.
	 * 
	 * If a car catches up to a car fleet right at the destination point, it will still be considered as
	 * one car fleet.
	 * 
	 * Algorithm:
	 * A car is a (position, speed) which implies some arrival time (target - position) / speed. Bind 
	 * (position, arrival time) with hash map. Sort the cars by position.
	 * 
	 * If the car behind the lead car would arrive earlier, then this car forms a fleet with the lead
	 * car. Otherwise, the fleet is final as no car can catch up to it.
	 * 
	 * If the lead fleet drives away, then count it and continue. Otherwise, merge the fleets and
	 * continue.
	 * 
	 * Total time complexity is O(n * k), where n is the number of cars and k is the number of digits.
	 * The time complexity is dominated by sorting operation.
	 * 
	 * @param target distance between the destination and 0
	 * @param position array that contains initial positions of each car
	 * @param speed array that contains speeds of each car
	 * @return the number of car fleet.
	 */
    public int carFleet(int target, int[] position, int[] speed) {
        int carFleetCount = 0;
        int positionSize = position.length;
        
        if(positionSize == 0 || speed.length == 0){
            return carFleetCount;
        }
        
        Map<Integer, Double> positionSpeedMap = new HashMap<>();
        
        /*
         * Add position and speed to the map.
         */
        for(int i = 0; i < positionSize; i++){
            positionSpeedMap.put(position[i], (double) (target - position[i]) / speed[i]);
        }
        
        radixSort(position);
        
        carFleetCount = 1;
        for(int i = positionSize - 1; i > 0; i--){
            if(positionSpeedMap.get(position[i - 1]) > positionSpeedMap.get(position[i])){
                carFleetCount++;
            }else{
                positionSpeedMap.put(position[i - 1], positionSpeedMap.get(position[i]));
            }
        }
        
        return carFleetCount;
    }
    
    /**
     * Implement radix sort.
     * 
     * @param position array that contains initial positions of each car
     */
    private void radixSort(int[] position){
        int positionSize = position.length;
        int maxNumber = position[0];
        final int RADIX = 10;
        
        /*
         * Obtain the maximum number in position array.
         */
        for(int i = 1; i < positionSize; i++){
            maxNumber = Math.max(maxNumber, position[i]);
        }
        
        int digit = 1;
        
        /*
         * Count the number of elements in each bucket.
         */
        int numberOfElementsPerBucket;
        
        /*
         * Temporary array to store elements being sorted by each digit.
         */
        int[] tmp = new int[positionSize];
        
        while(maxNumber / digit > 0){
            int[] buckets = new int[RADIX];
            
            for(int i = 0; i < positionSize; i++){
                /*
                 * Count the number of elements in each bucket.
                 */
                numberOfElementsPerBucket = (position[i] / digit) % RADIX;
                
                buckets[numberOfElementsPerBucket]++;
            }
            
            /*
             * Assign locations from tmp array to each bucket.
             */
            for(int i = 1; i < RADIX; i++){
                buckets[i] += buckets[i - 1];
            }
            
            /*
             * Collect all elements from bucket to tmp array.
             */
            for(int i = positionSize - 1; i >= 0; i--){
                numberOfElementsPerBucket = (position[i] / digit) % RADIX;
                tmp[buckets[numberOfElementsPerBucket] - 1] = position[i];
                buckets[numberOfElementsPerBucket]--;
            }
            
            /*
             * Copy all elements from tmp array to position array.
             */
            for(int i = 0; i < positionSize; i++){
                position[i] = tmp[i];
            }
            
            digit *= 10;
        }
    }
}