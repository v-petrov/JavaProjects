package SSA;

import java.util.Scanner;

public class MatrixSum100 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += matrix[i][i];
        }

        if (sum >= 100) { // matrix[0][3] n = 4;
                          // matrix[1][2]
                          // matrix[2][1]
                          // matrix[3][0]
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = matrix[i][n - i - 1];
            }

            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = i + 1; j < n; j++) {
                    if (array[i] == array[j]) {
                        cnt++;
                    }
                }
                if (cnt == 0) {
                    for (int k = 0; k < i; k++) {
                        if (array[k] == array[i]) {
                            cnt++;
                        }
                    }
                    if (cnt == 0) {
                        System.out.print(array[i] + " ");
                    }
                }
            }
        } else {
            System.out.println("Sum less than 100");
        }
    }
}
