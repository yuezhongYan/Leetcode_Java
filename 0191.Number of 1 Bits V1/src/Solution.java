/**
 * Solution implementation
 * 
 * @author YuezhongYan
 *
 */
public class Solution {
    // you need to treat n as an unsigned value
	/**
	 * Naive implementation of counting the number of 1 bits with Hamming Weight.
	 * It is naive because it needs 5 * 4 = 20 arithmetic operations.
	 * Refer: https://en.wikipedia.org/wiki/Hamming_weight
	 * 
	 * @param n number
	 * @return number of 1 bits
	 */
    public int hammingWeight(int n) {
        final int M1 = 0x55555555; //binary: 01010101010101010101010101010101
        final int M2 = 0x33333333; //binary: 00110011001100110011001100110011
        final int M4 = 0x0F0F0F0F; //binary: 00001111000011110000111100001111
        final int M8 = 0x00FF00FF; //binary: 00000000111111110000000011111111
        final int M16 = 0x0000FFFF; // binary: 00000000000000001111111111111111
        
        /*
         * Put count of each m bits into those m bits for m = 2, 4, 8, 16, 32
         */
        n = (n & M1) + ((n >> 1) & M1); // m = 2
        n = (n & M2) + ((n >> 2) & M2); // m = 4
        n = (n & M4) + ((n >> 4) & M4); // m = 8
        n = (n & M8) + ((n >> 8) & M8); // m = 16
        n = (n & M16) + ((n >> 16) & M16); // m = 32
        
        return n;
    }
}