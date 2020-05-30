import java.util.Arrays;

public class SolutionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Solution solution = new Solution();
        int[] nums = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
	}

}
