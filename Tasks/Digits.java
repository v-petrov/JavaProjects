package SSA.tasks;

import java.util.Scanner;

public class Digits {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int digitCount = 0, digit = 0, num = A;

        while (num != Integer.MAX_VALUE) {
            num++;
            if (isSameDigit(num) && num % A == 0) {
                digit = num % 10;
                while (num != 0) {
                    num = num / 10;
                    digitCount++;
                }
                break;
            }
        }
        if (digitCount != 0) {
            System.out.println(digitCount);
        }
        System.out.println(digit);
    }

    private static boolean isSameDigit(int num) {
        if (num <= 10) {
            return false;
        }
        while (true) {
            int lastDigit = num % 10;
            num = num / 10;
            if (num == 0) {
                break;
            }
            if (num % 10 != lastDigit) {
                return false;
            }
        }
        return true;
    }
}
