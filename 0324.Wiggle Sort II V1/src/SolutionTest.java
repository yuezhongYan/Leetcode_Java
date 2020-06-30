import java.util.Arrays;

public class SolutionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Solution solution = new Solution();
        int[] nums = {1, 3, 2, 2, 3, 1};
        solution.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
	}

}
