package SSA.tasks;

import java.util.Scanner;

public class CountingRooms {
    static int n = 5;
    static int m = 8;
    static char[][] building = new char[n][m];
    static boolean[][] isVisited = new boolean[n][m];
    static int rooms = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            String row = sc.nextLine();
            for (int j = 0; j < m; j++) {
                building[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (building[i][j] == '.' && !isVisited[i][j]) {
                    countingRooms(i, j, '.');
                    rooms++;
                }
            }
        }

        System.out.println(rooms);
    }

    private static void countingRooms(int r, int c, char f) {
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return;
        }
        if (isVisited[r][c]) {
            return;
        }
        if (building[r][c] != f) {
            return;
        }
        isVisited[r][c] = true;
        countingRooms(r + 1, c, f);
        countingRooms(r, c + 1, f);
        countingRooms(r - 1, c, f);
        countingRooms(r, c - 1, f);
    }
}
