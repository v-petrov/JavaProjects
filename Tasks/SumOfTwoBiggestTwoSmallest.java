package SSA.tasks;

public class SumOfTwoBiggestTwoSmallest {

    public static void main(String[] args) {
        m2();
    }

    private static void m1() {
        int n = 11;
        int[] a = {30, 24, 33, 15, 27, 7, 14, 4, 21, 11, 35};
        int largest = a[0], secLargest = a[1];
        int smallest = a[1], secSmallest = a[0];
        int temp, sum;

        for (int i = 0; i < n; i++) {
            if (a[i] > secLargest) {
                secLargest = a[i];
                if (secLargest > largest) {
                    temp = secLargest;
                    secLargest = largest;
                    largest = temp;
                }
            }

            if (a[i] < secSmallest) {
                secSmallest = a[i];
                if (secSmallest < smallest) {
                    temp = secSmallest;
                    secSmallest = smallest;
                    smallest = temp;
                }
            }
        }

        System.out.println("Largest: " + largest + ", secLargest: " + secLargest);
        System.out.println("Smallest: " + smallest + ", secSmallest: " + secSmallest);

        sum = largest + secLargest + smallest + secSmallest;
        System.out.println(sum);
    }

    private static void m2() {
        // Xn+1 = 2Xn + 3;    X0 = 2;
        int x = 2;
        int y = 7;

        do {
            if (x <= 100) {
                x = 2 * x + 3;
            }
            y = 2 * y + 3;
        } while (y <= 10_000);

        int sum = 0;
        for (int i = x; i <= y; i++) {
            if (i % 2 == 1) {
                System.out.print(i + " ");
                sum += i;
            }
        }

        System.out.println();
        System.out.println(sum);
    }
}
