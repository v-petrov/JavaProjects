package SSA.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiameterOfTree {

    static List<List<Integer>> adj = new ArrayList<>();
    static int n;

    public static void main(String[] args) {
        readFile();
        diameterOfTree();
    }

    private static void diameterOfTree() {
        int cnt;
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                cnt = dfs(i, -1, false) - 1;
                System.out.println(cnt);
                break;
            }
        }
    }

    private static int dfs(int x, int p, boolean f) {
        int cnt = 1;

        for (int a : adj.get(x)) {
            if (a != p && !f) {
                f = true;
                cnt += dfs(a, x, false);
            }
        }

        return cnt;
    }

    private static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("subordinates.text"))) {
            String line;
            n = Integer.parseInt(reader.readLine());
            initAdj();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int p = Integer.parseInt(tokens[0]);
                int c = Integer.parseInt(tokens[1]);
                adj.get(--p).add(--c);
                adj.get(c).add(p);
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
