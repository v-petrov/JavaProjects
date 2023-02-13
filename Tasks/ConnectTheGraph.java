package SSA.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectTheGraph {

    static final String FILE_NAME = "factory.text";
    static int n;
    static int m;
    static List<List<Integer>> adj = new ArrayList<>();
    static boolean[] isVisited;

    public static void main(String[] args) {
        if (readInput() == -1) {
            System.out.println(-1);
            return;
        }
        System.out.println(connectTheGraph());
    }

    private static int connectTheGraph() {
        int firstFalse;
        int cnt = 0;
        int remove;

        while (dfs(0) != n) {
            firstFalse = firstNotVisited();
            if (firstFalse != -1) {
                adj.get(0).add(firstFalse);
                adj.get(firstFalse).add(0);
                remove = adj.get(0).remove(0);
                adj.get(remove).remove(Integer.valueOf(0));
                cnt++;
                initVisited();
                System.out.print(1 + " " + ++remove + ", ");
                System.out.println(1 + " " + ++firstFalse);
            }
        }

        return cnt;
    }

    private static int firstNotVisited() {
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                return i;
            }
        }
        return -1;
    }

    private static int dfs(int root) {
        int visitCnt = 1;
        isVisited[root] = true;
        for (int a : adj.get(root)) {
            if (!isVisited[a]) {
                visitCnt += dfs(a);
            }
        }

        return visitCnt;
    }


    private static int readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);

            if (!checkIfCanBeConnected()) {
                return -1;
            }


            initAdjList();
            initVisited();

            while ((line = reader.readLine()) != null) {
                tokens = line.split(" ");
                int node = Integer.parseInt(tokens[0]);
                int adjNode = Integer.parseInt(tokens[1]);

                adj.get(node - 1).add(adjNode - 1);
                adj.get(adjNode - 1).add(node - 1);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static boolean checkIfCanBeConnected() {
        return m >= n - 1;
    }

    private static void initVisited() {
        isVisited = new boolean[n];
    }

    private static void initAdjList() {
        for (int i = 0; i < n; i++) {
            adj.add(i, new ArrayList<>());
        }
    }
}
