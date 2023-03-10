package SSA.tasks;

public class GetKthPermutation {

    static int[] digits = new int[10];
    static int n = 5;
    static int k = 5;
    static boolean ff = true;
    static String permRes;

    public static void main(String[] args) {
        for (int i = 1; i <= n; i++) {
            digits[i] = 1;
        }

        getKPermutation("");
        System.out.println(permRes);
    }

    private static void getKPermutation(String num) {
        if (!ff) {
            return;
        }
        if (num.length() == n) {
            k--;
            if (k == 0) {
                ff = false;
                permRes = num;
            }
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (digits[i] == 1) {
                digits[i]--;
                getKPermutation(num + i);
                digits[i]++;
            }
        }
    }
}
