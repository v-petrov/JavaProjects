package SSA.tasks;

public class MinimumInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(findMin(nums));
    }

    private static int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int m;

        while (l <= r) {
            m = l + ((r - l) / 2);
            if (m == 0 || l == r || (nums[m] < nums[m + 1] && nums[m] < nums[m - 1])) {
                return nums[m];
            } else if (nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return 0;
    }
}
