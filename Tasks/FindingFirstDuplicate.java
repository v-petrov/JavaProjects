package exercises;

import java.util.HashMap;
import java.util.Map;

public class FindingFirstDuplicate {

//    public char letter(String s) {
//        StringBuilder sb = new StringBuilder(String.valueOf(s.charAt(0)));
//        for(int i = 1; i < s.length(); i++) {
//            if(sb.toString().contains(String.valueOf(s.charAt(i)))) {
//                return s.charAt(i);
//            } else {
//                sb.append(s.charAt(i));
//            }
//        }
//        return '-';
//    }

        public char letter(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            if(counts.containsKey(s.charAt(i))) {
                return s.charAt(0);
            } else {
                counts.put(s.charAt(i), 1);
            }
        }
        return '-';
    }

    public static void main(String[] args) {
        FindingFirstDuplicate ffd = new FindingFirstDuplicate();
        char c = ffd.letter("DBCADB");
        System.out.println(c);
    }
}
