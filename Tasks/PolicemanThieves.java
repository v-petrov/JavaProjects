package SSA.tasks;

public class PolicemanThieves {

    static int policeCount = 0;
    static int thievesCount = 0;
    static int k = 3;
    static char[] array = {'P', 'T', 'P', 'T', 'T', 'P'};
    static boolean[] isThiefCaught = new boolean[array.length];

    public static void main(String[] args) {
        int result = thievesCaught();
        System.out.println(result);
    }

    private static int thievesCaught() {
        if (k == 0) {
            return 0;
        } else if (k >= array.length) {
            countElements(array);
            return Math.min(thievesCount, policeCount);
        }

        int caughtThieves = 0, u, i, indexOfCurrT, ff;

        for (int e = 0; e < array.length; e++) {
            i = e;
            ff = 0;
            u = k;
            indexOfCurrT = -1;
            if (array[e] == 'P') {
                while (i >= 1) {
                    i--;
                    if (array[i] == 'T' && !isThiefCaught[i]) {
                        indexOfCurrT = i;
                    }
                    u--;
                    if (u == 0) {
                        if (indexOfCurrT == -1) {
                            break;
                        }
                        isThiefCaught[indexOfCurrT] = true;
                        caughtThieves++;
                        ff = 1;
                        break;
                    }
                }
                i = e;
                u = k;
                while (i < array.length - 1 && u != 0 && ff == 0) {
                    i++;
                    if (array[i] == 'T' && !isThiefCaught[i]) {
                        isThiefCaught[i] = true;
                        caughtThieves++;
                        break;
                    }
                    u--;
                }
            }
        }
        return caughtThieves;
    }

    private static void countElements(char[] array) {
        for (char p : array) {
            if (p == 'P') {
                policeCount++;
            } else {
                thievesCount++;
            }

        }
    }
}
