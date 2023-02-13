package SSA.tasks;

public class CommonDivisors {

    public static void main(String[] args) {
        int[] array = {7,14,3,15,2};
        System.out.println(largestGCD(array));
    }

    private static int largestGCD(int[] array) {
        int largestNum = -1;
        for (int i = 0; i < array.length - 1; i++) {
            if (largestNum < array[i]) {
                for (int j = i + 1; j < array.length; j++) {
                    if (largestNum < Math.min(array[i], array[j])) {
                        int n = gcd(array[i], array[j]);
                        largestNum = Math.max(largestNum, n);
                    }
                }
            }
        }

        return largestNum;
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
