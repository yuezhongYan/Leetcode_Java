/**
 * Solution implementation.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
    /**
     * Given non-negative integer num, count the number of 1's for every integer i in
     * [0, num]. Time complexity is O(n), where while loop takes O(n) and
     * countNumberOfOnes takes O(1).
     * 
     * @param num given number
	 * @return array of number of 1's
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        int numberOfOnes;
        
        int i = 0;
        while(i <= num){
            numberOfOnes = countNumberOfOnes(i);
            result[i] = numberOfOnes;
            i++;
        }
        
        return result;
    }
    
    /**
     * This method is the same as Problem 191 with Hamming Weight.
     * Refer https://en.wikipedia.org/wiki/Hamming_weight.
     * 
     * @param number number used to count the number of 1's
     * @return the number of 1's in the number
     */
    private int countNumberOfOnes(int number){
        final int M1 = 0x55555555; // binary: 01010101010101010101010101010101
        final int M2 = 0x33333333; // binary: 00110011001100110011001100110011
        final int M4 = 0x0F0F0F0F; // binary: 00001111000011110000111100001111
        final int M8 = 0x00FF00FF; // binary: 00000000111111110000000011111111
        final int M16 = 0x0000FFFF; // binary: 00000000000000001111111111111111
        
        number = (number & M1) + ((number >> 1) & M1);
        number = (number & M2) + ((number >> 2) & M2);
        number = (number & M4) + ((number >> 4) & M4);
        number = (number & M8) + ((number >> 8) & M8);
        number = (number & M16) + ((number >> 16) & M16);
        
        return number;
    }
}