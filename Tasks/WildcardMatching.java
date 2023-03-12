package SSA.tasks;

import java.util.Arrays;

public class WildcardMatching {
    public static void main(String[] args) {
        String s = "gadceb";
        String p = "?a*b";
        System.out.println(isMatch(s, p));
    }

    private static boolean isMatch(String s, String p) {
        if (p.length() > s.length()) {
            return false;
        }

        int sIndex = 0;
        boolean[] dpS = new boolean[s.length()];
        boolean[] dpP = new boolean[p.length()];

        for (int i = 0; i < p.length(); i++) {
            if (dpS[s.length() - 1] && !dpP[p.length() - 1]) {
                return false;
            }
            dpP[i] = true;
            if (p.charAt(i) == '*') {
                if (i + 1 == p.length()) {
                    return true;
                }
                String nextChar = String.valueOf(p.charAt(i + 1));
                if (!s.contains(nextChar)) {
                    return false;
                }
                int sPos = s.indexOf(nextChar, sIndex + 1);
                if (sPos == -1) {
                    return false;
                }
                Arrays.fill(dpS, sIndex, sPos, true);
                sIndex = sPos - 1;
            } else if (p.charAt(i) == '?') {
                dpS[sIndex] = true;
            } else if (s.charAt(sIndex) != p.charAt(i)) {
                return false;
            } else {
                dpS[sIndex] = true;
            }
            sIndex++;
        }

        return dpS[s.length() - 1];
    }
}
