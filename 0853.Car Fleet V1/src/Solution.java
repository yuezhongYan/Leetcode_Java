import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
	 * Total time complexity is O(n log n) in average case and O(n^2) in the worst case, where n is the
	 * number of cars. The time complexity is dominated by sorting operation. The worst case happens if
	 * the pivot index is always picked at both ends.
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
         * Put position and speed to the map.
         */
        for(int i = 0; i < positionSize; i++){
            positionSpeedMap.put(position[i], (double) (target - position[i]) / speed[i]);
        }
        
        quickSort(position, 0, positionSize - 1);
        
        carFleetCount = 1;
        for(int i = positionSize - 1; i > 0; i--){
        	/*
        	 * If the car with initial position i - 1 arrives sooner, it cannot be caught. Else, the car with
        	 * initial position i - 1 arrives at the same speed as the car with initial position i.
        	 */
            if(positionSpeedMap.get(position[i - 1]) > positionSpeedMap.get(position[i])){
                carFleetCount++;
            }else{
                positionSpeedMap.put(position[i - 1], positionSpeedMap.get(position[i]));
            }
        }
        
        return carFleetCount;
    }
    
    /**
     * Implement quick sort.
     * 
     * @param position array that contains initial positions of each car
     * @param start start index
     * @param end end index
     */
    private void quickSort(int[] position, int start, int end){
        if(start >= end){
            return;
        }
        
        Random random = new Random();
        int pivotIndex = random.nextInt(end - start + 1) + start;
        int pivot = position[pivotIndex];
        
        /*
         * Swap position[pivotIndex] with position[start]. Now pivot is left.
         */
        swap(position, pivotIndex, start);
        
        int left = start + 1;
        int right = end;
        
        /*
         * Partition.
         */
        while(left <= right){
            while(left <= right && position[left] <= pivot){
                left++;
            }
            
            while(left <= right && position[right] > pivot){
                right--;
            }
            
            if(left < right){
                /*
                 * Swap position[left] with position[right].
                 */
                swap(position, left, right);
                
                left++;
                right--;
            }
        }
        
        /*
         * Swap position[right] with position[start]. Now pivot is position[right].
         */
        swap(position, right, start);
        
        quickSort(position, start, right - 1);
        quickSort(position, right + 1, end);
    }
    
    /**
     * Implement swap.
     * 
     * @param position position array that contains initial positions of each car
     * @param i index to be swapped with
     * @param j the other index to be swapped with
     */
    private void swap(int[] position, int i, int j){
        int temp = position[i];
        position[i] = position[j];
        position[j] = temp;
    }
}