package SSA.tasks;

public class CheckSubarraySum {

    public static void main(String[] args) {
        int[] nums = {23, 2, 6, 4, 7};
        int k = 13;
        System.out.println(checkSubarraySum(nums, k));

    }

    private static boolean checkSubarraySum(int[] nums, int k) {
        int[] prefixSum = prefixSum(nums);
        int l, r, i = 1, q = 1;
        while (i < nums.length) {
            l = i;
            r = i + q;
            if ((prefixSum[r] - prefixSum[l - 1]) % k == 0) {
                return true;
            }

            if (!(r == nums.length) || !(l == 1)) {
                i = 0;
                q++;
            } else {
                break;
            }
            i++;
        }

        return false;
    }

    private static int[] prefixSum(int[] nums) {
        int[] prefix = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        return prefix;
    }

}
