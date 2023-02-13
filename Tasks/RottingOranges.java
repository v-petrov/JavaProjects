package SSA.tasks;

public class RottingOranges {

    public static void main(String[] args) {
        int[][] grid = {{2,1,0,2},{0,1,1,1},{1,0,2,0},{1,1,1,1}};
        System.out.println(orangeRotting(grid));
    }

    private static int orangeRotting(int[][] grid) {
        int min = 0;
        boolean[][] gridTF;

        if (!canFinish(grid)) {
            return -1;
        }

        while (!isFinished(grid)) {
            int currR, currC;
            gridTF = new boolean[grid.length][grid[0].length];

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {
                    currC = c;
                    currR = r;
                    if (grid[r][c] == 2 && !gridTF[r][c]) {
                        currR++;
                        if (currR < grid.length && grid[currR][c] == 1) {
                            gridTF[currR][c] = true;
                            grid[currR][c] = 2;
                        }
                        currR = currR - 2;
                        if (currR >= 0 && grid[currR][c] == 1) {
                            gridTF[currR][c] = true;
                            grid[currR][c] = 2;
                        }
                        currC++;
                        if (currC < grid[r].length && grid[r][currC] == 1) {
                            gridTF[r][currC] = true;
                            grid[r][currC] = 2;
                        }
                        currC = currC - 2;
                        if (currC >= 0 && grid[r][currC] == 1) {
                            gridTF[r][currC] = true;
                            grid[r][currC] = 2;
                        }
                    }
                }
            }
            min++;
        }

        return min;
    }

    private static boolean canFinish(int[][] grid) {
        int currR, currC;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                currC = c;
                currR = r;
                if (grid[r][c] == 1) {
                    currR++;
                    if (currR < grid.length && grid[currR][c] != 0) {
                        continue;
                    }
                    currR = currR - 2;
                    if (currR >= 0 && grid[currR][c] != 0) {
                        continue;
                    }
                    currC++;
                    if (currC < grid[r].length && grid[r][currC] != 0) {
                        continue;
                    }
                    currC = currC - 2;
                    if (currC >= 0 && grid[r][currC] != 0) {
                        continue;
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isFinished(int[][] grid) {
        for (int[] i : grid) {
            for (int j : i) {
                if (j == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
