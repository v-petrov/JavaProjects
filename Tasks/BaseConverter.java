package SSA.tasks;

import java.util.Map;

public class BaseConverter {

    private StringBuilder res = new StringBuilder();
    private final Map<Integer, Character> chars = Map.of(10, 'A', 11, 'B', 12, 'C', 13, 'D', 14, 'E'
            , 15, 'F');

    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();

        String num = "ABC";
        int numBase = 16;
        int b = 2;

        bc.converterToTenBase(num, numBase, b);
        System.out.println(bc.res);
    }

    private void converterToTenBase(String num, int a, int b) {
        if (a == b) {
            res = new StringBuilder(num);
            return;
        }

	if (a == 10) {
	    converterToBBase(Integer.parseInt(num), b);
	    return;
	}

        int numInTen = 0;
        int power = 0;
        int currDigit;

        for (int i = num.length() - 1; i >=0; i--) {
            if (chars.containsValue(num.charAt(i))) {
                currDigit = num.charAt(i) - 55;
            } else {
                currDigit = num.charAt(i) - '0';
            }

            numInTen += currDigit * Math.pow(a, power++);
        }

        converterToBBase(numInTen, b);
    }

    private void converterToBBase(int a, int b) {
        if (b == 10) {
            res = new StringBuilder(a);
            return;
        }
        if (a == 0) {
            return;
        }

        int rem = a % b;
        converterToBBase(a / b, b);
        if (chars.containsKey(rem)) {
            res.append(chars.get(rem));
        } else {
            res.append(rem);
        }
    }
}
