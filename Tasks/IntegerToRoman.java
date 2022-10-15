package main;

import java.util.LinkedHashMap;
import java.util.Map;

public class IntegerToRoman {

    private final int n;

    public int getN() {
        return this.n;
    }

    public IntegerToRoman(int n) {
        this.n = n;
    }

    public static Map<Integer, String> getMap() {
        Map<Integer, String> m1 = new LinkedHashMap<>();
        m1.put(1, "I");m1.put(4, "IV");m1.put(5, "V");m1.put(9, "IX");
        m1.put(10, "X");m1.put(40, "XL");m1.put(50, "L");m1.put(90, "XC");
        m1.put(100, "C");m1.put(400, "CD");m1.put(500, "D");m1.put(900, "CM");
        m1.put(1000, "M");

        return m1;
    }
    public String intToRoman() {
        StringBuilder roman = new StringBuilder();
        Map<Integer, String> m1 = getMap();
        int num = this.getN();

        int t = num / 1000;
        for (int i = 0; i < t; i++) {
            roman.append(m1.get(1000));
        }

        int l = 10000, p = 1000, d9 = 9000, d5 = 5000, d4 = 4000, d1 = 1000;

        // 1994

        for(int j = 0; j < 3; j++) {

            num %= l / 10;

            t = num / (p / 10);

            if(t == 9) {
                roman.append(m1.get(d9 / 10));
            }
            else if(t > 4) {
                roman.append(m1.get(d5 / 10));
                t -= 5;
                for(int i = 0; i < t; i++) {
                    roman.append(m1.get(d1 / 10));
                }
            }
            else if(t == 4) {
                roman.append(m1.get(d4 / 10));
            }
            else {
                for(int i = 0; i < t; i++) {
                    roman.append(m1.get(d1 / 10));
                }
            }

            l /= 10; p /= 10; d9 /= 10; d5 /= 10; d4 /= 10; d1 /= 10;
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman num = new IntegerToRoman(2002);
        String roman = num.intToRoman();
        System.out.println(roman);
    }
}
