package SSA.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MilkingCows {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> cows = fillMap();

        List<Integer> milkValue = new ArrayList<>(cows.values());
        Collections.sort(milkValue);

        int secondToLast = milkValue.get(0);

        for (int i = 1; i < milkValue.size(); i++) {
            if (secondToLast != milkValue.get(i)) {
                secondToLast = milkValue.get(i);
                if (secondToLast == milkValue.get(i + 1)) {
                    System.out.println("Tie!");
                    return;
                }
                break;
            }
        }

        for (var entrySet : cows.entrySet()) {
            if (entrySet.getValue() == secondToLast) {
                System.out.println(entrySet.getKey());
            }
        }
    }

    private static Map<String, Integer> fillMap() throws IOException {
        Map<String, Integer> mapOfCows;
        try (BufferedReader bf = new BufferedReader(new FileReader("notlast.text"))) {
            mapOfCows = new HashMap<>();

            int n = Integer.parseInt(bf.readLine());
            int milk;

            for (int i = 0; i < n; i++) {
                milk = 0;
                String[] tokens = bf.readLine().split(" ");
                if (mapOfCows.containsKey(tokens[0])) {
                    milk = mapOfCows.get(tokens[0]);
                }
                mapOfCows.put(tokens[0], milk + Integer.parseInt(tokens[1]));
            }
        }

        mapOfCows = missingNames(mapOfCows);

        return mapOfCows;
    }

    private static Map<String, Integer> missingNames(Map<String, Integer> cows) {
        if (cows.size() == 7) {
            return cows;
        }
        Set<String> cowNames = Set.of("Bessie", "Elsie", "Gertie", "Daisy", "Annabelle", "Maggie", "Henrietta");

        for (String name : cowNames) {
            if (!cows.containsKey(name)) {
                cows.put(name, 0);
            }
        }

        return cows;
    }
}
