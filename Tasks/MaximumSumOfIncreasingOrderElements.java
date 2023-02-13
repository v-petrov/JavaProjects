package SSA.tasks;

import java.util.Arrays;

public class MaximumSumOfIncreasingOrderElements {

    static int[][] array = {{3,5,7,8,4}, // 3,4,5,7,8
                            {8,7,4,3,6}, // 3,4,6,7,8
                            {4,5,10,7,1}};// 1,4,5,7,10

    static int m = array[0].length;

    public static void main(String[] args) {
        int res = maxSum();
        System.out.println(res);
    }

    private static void sort(int row) {
        for (int i = 0; i < m - 1; i++) {
            if (array[row][i] > array[row][i + 1]) {
                int temp = array[row][i];
                array[row][i] = array[row][i + 1];
                array[row][i + 1] = temp;
            }
        }
    }

    private static int maxSum() {
        int sum, i, j;

        Arrays.sort(array[0]);
        for (int a = 1; a < array.length; a++) {
            sort(a);
        }

        for (i = m - 1; i >= 0; i--) {
            sum = 0;
            int n = array[0][i];
            sum += n;
            for (j = 1; j < array.length; j++) {
                if (n < array[j][m - 1]) {
                    n = array[j][m - 1];
                    sum += n;
                } else {
                    break;
                }
            }
            if (j == array.length) {
                return sum;
            }
        }

        return 0;
    }

}
