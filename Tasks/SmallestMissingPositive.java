package exercises;

import java.util.*;

public class SmallestMissingPositive {

    private final int[] array;

    private final Set<Integer> s1;

    SmallestMissingPositive(int[] array) {
        this.array = array;
        this.s1 = new HashSet<>();
    }

    private OptionalInt smallestPositiveInArray() {
        return Arrays.stream(array)
                .filter(x -> x > 0)
                .sorted()
                .findFirst();
    }

    public int firstMissingPositive() {
        int smallestInteger;
        OptionalInt i1 = smallestPositiveInArray();

        if(i1.isPresent()) {
            smallestInteger = i1.getAsInt();
        } else {
            return 1;
        }

        for(int n : array) {
            s1.add(n);
        }

        if(smallestInteger > 1) {
            return 1;
        } else {
            while(true) {
                smallestInteger++;
                if(!s1.contains(smallestInteger)) {
                    return smallestInteger;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] array = {-3,1,2,-8};
        SmallestMissingPositive smp = new SmallestMissingPositive(array);
        int n = smp.firstMissingPositive();
        System.out.println(n);
    }
}
