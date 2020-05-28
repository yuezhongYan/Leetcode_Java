/**
 * Solution implementation
 * 
 * @author YuezhongYan
 *
 */
public class Solution {
    // you need treat n as an unsigned value
	/**
	 * Returns the value obtained by reversing the order of the bits in the two's
	 * complement binary representation of the specified int value.
	 * 
	 * Right-shift input value with m bits and do AND operation, then use the same
	 * input value, do AND operation, and do left-shift operation. Finally, apply OR
	 * operation to obtain the result and assign this result to the input value.
	 * 
	 * Do the above step using m = 1, 2, 4, 8, 16. 
	 * For AND operation, use
	 * M1 = 01010101010101010101010101010101b
	 * M2 = 00110011001100110011001100110011b
	 * M4 = 00001111000011110000111100001111b
	 * M8 = 00000000111111110000000011111111b
	 * M16 = 00000000000000001111111111111111b
	 * to split bits into group of m.
	 * 
	 * e.g., n = 43261596 = 00000010100101000001111010011100b
	 * n = ((n >> 1) & M1) | ((n & M1) << 1): = 00000001011010000010110101101100b
	 * 
	 * Time complexity is O(1) in the worst case because it has total of 25 bit
	 * operations no matter how large the input value is.
	 * 
	 * @param n the value to be reversed
	 * @return the value obtained by reversing order of the bits in the specified int
	 *     value.
	 */
    public int reverseBits(int n) {
        final int M1 = 0x55555555; // binary: 01010101010101010101010101010101
        final int M2 = 0x33333333; // binary: 00110011001100110011001100110011
        final int M4 = 0x0F0F0F0F; // binary: 00001111000011110000111100001111
        final int M8 = 0x00FF00FF; // binary: 00000000111111110000000011111111
        final int M16 = 0x0000FFFF; // binary: 00000000000000001111111111111111
    
        /*
         * reverse evens and odds.
         */
        n = ((n >> 1) & M1) | ((n & M1) << 1);
    
        /*
         * reverse consecutive pairs.
         */
        n = ((n >> 2) & M2) | ((n & M2) << 2);
    
        /*
         * reverse 4-bit long pairs
         */
        n = ((n >> 4) & M4) | ((n & M4) << 4);
    
        /*
         * reverse 8-bit long pairs
         */
        n = ((n >> 8) & M8) | ((n & M8) << 8);
    
        /*
         * reverse 16-bit long pairs
         */
        n = ((n >> 16) & M16) | ((n & M16) << 16);
    
        return n;
    }
}