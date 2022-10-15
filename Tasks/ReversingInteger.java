public class ReversingInteger {

    private final Integer x;
    private final int[] digitsArray;

    public ReversingInteger(int x) {
        this.x = x;
        digitsArray = new int[numOfDigits(this.x)];
    }

    public static int numOfDigits(int x) {
        int cnt = 0;
        while(x != 0) {
            x /= 10;
            cnt++;
        }
        return cnt;
    }

    public void arrayOfReverseDigits() {
        int num = this.x;

        int digit;
        for(int i = 0; i < digitsArray.length; i++) {
            digit = num % 10;
            digitsArray[i] = Math.abs(digit);
            num /= 10;
        }
    }

    public int reverseInteger() {
        if(digitsArray.length == 1) return this.x;

        this.arrayOfReverseDigits();

        StringBuilder stringNum = new StringBuilder();
        if(this.x < 0) {
            stringNum = new StringBuilder("-");
        }

        for(int n : digitsArray) {
            stringNum.append(n);
        }

        int reverseNum;
        try {
            reverseNum = Integer.parseInt(stringNum.toString());
        } catch (NumberFormatException e) {
            System.out.println("Out of range converting number:");
            reverseNum = 0;
        }

        return reverseNum;
    }

    public static void main(String[] args) {
        ReversingInteger num = new ReversingInteger(2133923894);
        int n = num.reverseInteger();
        System.out.println(n);
    }
}
