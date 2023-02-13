package SSA.tasks;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        String[] str = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(str));
    }
    private static List<List<String>> groupAnagrams(String[] str) {
        Set<Long> uniqueValues = new HashSet<>();

        for (String s : str) {
            long value = stringValue(s);
            uniqueValues.add(value);
        }

        List<List<String>> anagrams = new ArrayList<>();
        int index_row = 0;

        for (Long uniqueVal : uniqueValues) {
            anagrams.add(index_row, new ArrayList<>());
            for (String s : str) {
                if (Objects.equals(uniqueVal, stringValue(s))) {
                    anagrams.get(index_row).add(s);
                }
            }
            index_row++;
        }


        return anagrams;
    }
    private static long stringValue(String s) {
        long value = 0;
        for (char c : s.toCharArray()) {
            value += c;
        }

        return value;
    }
}
