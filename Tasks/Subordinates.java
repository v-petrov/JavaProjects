package SSA.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Subordinates {

    static List<List<Integer>> adj = new ArrayList<>();
    static int n;

    public static void main(String[] args) {
        readFile();
        printSubordinates();
    }
    private static void printSubordinates() {
        int cnt;
//        for (int i = 0; i < n; i++) {
//            cnt = adj.get(i).size();
//            for (int sub : adj.get(i)) {
//                cnt += adj.get(sub).size();
//            }
//            System.out.print(cnt + " ");
//        }

        for (int i = 0; i < n; i++) {
            cnt = dfs(i);
            System.out.print(--cnt + " ");
        }
    }

    private static int dfs(int x) {
        int cnt = 1;

        for (int a : adj.get(x)) {
            cnt += dfs(a);
        }

        return cnt;
    }

    private static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("subordinates.text"))) {
            String line;
            n = Integer.parseInt(reader.readLine());
            initAdj();
            String[] tokens = reader.readLine().split(" ");
            int currEmployee;
            int boss;
            for (int i = 0; i < tokens.length; i++) {
                currEmployee = i + 1;
                boss = Integer.parseInt(tokens[i]);
                adj.get(--boss).add(currEmployee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initAdj() {
        for (int i = 0; i < n; i++) {
            adj.add(i, new ArrayList<>());
        }
    }
}
