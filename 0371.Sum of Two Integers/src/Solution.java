/**
 * Implements solution.
 * 
 * @author YuezhongYan
 *
 */
class Solution {
	/**
	 * Calculate the sum of two integers a and b without "+" or "-" sign.
	 * 
	 * Bit operation:
	 * First, obtain the sum without carry: a XOR b.
	 * e.g., a = 1 = 0001b, b = 2 = 0010b, then
	 *  1					 0001
	 * +2				 XOR 0010
	 * ---				 ---------
	 *  3(without carry)     1100
	 *
	 * Second, retrieve the carry: (a AND b) << 1
	 * e.g., a = 5 = 0101b, b = 7 = 0111b, then
	 *  5		0101		0101
	 * +7	AND	0111	<<	   1
	 * ---	---------   ---------
	 * 12       0101        1010 (12 in decimal)
	 * where 5 + 7 = 2(without carry) and 5 + 7 = 10(carry).
	 * 
	 * Iterate the 2 steps above by replacing a with the sum without carry and b with
	 * carry until carry = 0.
	 * 
	 * @param a integer
	 * @param b the other integer
	 * @return sum of two integers a and b
	 */
    public int getSum(int a, int b) {
        int additionWithoutCarry;
        int carry;
        
        while(b != 0){
            additionWithoutCarry = a ^ b;
            carry = (a & b) << 1;
            
            a = additionWithoutCarry;
            b = carry;
        }
        
        return a;
    }
}