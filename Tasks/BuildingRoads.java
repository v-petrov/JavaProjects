package SSA.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildingRoads {

    static final String FILE_NAME = "factory.text";
    static int n;
    static int m;

    static List<List<Integer>> adj = new ArrayList<>();

    static boolean[] isVisited;

    public static void main(String[] args) {
        readInput();
        System.out.println(connectAllRoads());
    }

    private static int connectAllRoads() {
        int cnt = 0;
        int firstFalse;
        int node;

        for (int i = 0; i < n; i++) {
            while (dfs(i) != n) {
                firstFalse = firstNotVisited();
                if (firstFalse != -1) {
                    adj.get(i).add(firstFalse);
                    adj.get(firstFalse).add(i);
                    cnt++;
                    node = i + 1;
                    System.out.println(node + " " + ++firstFalse);
                } else {
                    initVisited();
                }

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

    private static void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);


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
