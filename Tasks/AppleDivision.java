package SSA.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleDivision {

    public static void main(String[] args) {
	// test buddy
        int[] appleWeights = {3, 2, 7, 4, 1};
        Arrays.sort(appleWeights);
        List<Integer> g1 = new ArrayList<>();
        List<Integer> g2 = new ArrayList<>();

        for (int i = 0; i < appleWeights.length; i++) {
            if (i == appleWeights.length - 1) {
                g2.add(appleWeights[i]);
            } else {
                g1.add(appleWeights[i]);
            }
        }

        int minDiff = minDifference(g1, g2, 1000000000);

        System.out.println(minDiff);
    }

    private static int minDifference(List<Integer> g1, List<Integer> g2, int diff) {
        int minDiff = diff;
        int g1Sum = sumOfG1(g1);
        int g2Sum = sumOfG2(g2);

        int currDiff = Math.abs(g1Sum - g2Sum);
        diff = Math.min(diff, currDiff);


        if (!(diff == 1 || diff == minDiff)) {
            int fEl = g1.remove(0);
            g2.add(fEl);
            diff = minDifference(g1, g2, diff);
        }

        return diff;
    }
    private static int sumOfG1(List<Integer> g1) {
        return g1.stream().mapToInt(Integer::valueOf).sum();
    }

    private static int sumOfG2(List<Integer> g2) {
        return g2.stream().mapToInt(Integer::valueOf).sum();
    }
}
