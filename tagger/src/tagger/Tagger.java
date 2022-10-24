package tagger;

import java.io.*;
import java.util.*;

public class Tagger {

    private static final String XML_TAG = "<city country='%s'>%s</city>";
    private static final int CITY = 0;
    private static final int COUNTRY = 1;
    private final Map<String, String> cityCountry = new HashMap<>();

    private final Map<String, Integer> taggedCities = new HashMap<>();

    public Tagger(Reader citiesReader) {
        try (var br = new BufferedReader(citiesReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                cityCountry.put(tokens[CITY], tokens[COUNTRY]);
            }
        } catch (IOException e) {
            System.err.println("Problem with reading from file");
        }
    }

    public static void main(String[] args) throws IOException {
        Reader cityReader = new FileReader("city-country.txt");
        Tagger tg = new Tagger(cityReader);

        Reader reader = new FileReader("inputText.txt");
        Writer writer = new FileWriter("outputText.txt");

        long start = System.currentTimeMillis();
        tg.tagCities(reader, writer);
        System.out.println(tg.taggedCities);
        System.out.println(System.currentTimeMillis() - start);

        Collection<String> nCities = tg.getNMostTaggedCities(1);
        System.out.println(nCities);

        Collection<String> allCities = tg.getAllTaggedCities();
        System.out.println(allCities);

        System.out.println(tg.getAllTagsCount());
    }

    public void tagCities(Reader text, Writer output) throws IOException {
        StringBuilder sb = new StringBuilder();
        int nextChar;
        String word;

        while ((nextChar = text.read()) != -1) {

            if (nextChar == ' ' || nextChar == '\'' || nextChar == '"' || nextChar == '.' || nextChar == '|' || nextChar == '?' || nextChar == ':' || nextChar == ';' || nextChar == ',' || nextChar == '\n') {
                word = wordConverting(sb.toString());
                if (cityCountry.containsKey(word)) {
                    String country = cityCountry.get(word);
                    String xml = String.format(XML_TAG, word, country);
                    output.write(xml);
                    addTaggedCity(word);
                } else {
                    output.write(sb.toString());
                }
                output.write(nextChar);
                sb.delete(0, sb.length());
            } else {
                sb.append((char) nextChar);
            }
        }
    }

    public Collection<String> getNMostTaggedCities(int n) {
        if (taggedCities.size() == 0) {
            return Collections.emptySet();
        }

        if (taggedCities.size() <= n) {
            return taggedCities.keySet();
        }

        Set<String> nMostTaggedCities = new HashSet<>();
        var values = taggedCities.values().stream().sorted().toList();
        var nList = values.subList(values.size() - n, values.size());

        for (String city : taggedCities.keySet()) {
            int number = taggedCities.get(city);
            if (nList.contains(number)) {
                nMostTaggedCities.add(city);
            }
        }

        return nMostTaggedCities;
    }

    public Collection<String> getAllTaggedCities() {
        return taggedCities.keySet();
    }

    public long getAllTagsCount() {
        long tagsCount = 0;
        for (Integer integer : taggedCities.values()) {
            tagsCount += integer;
        }

        return tagsCount;
    }

    private void addTaggedCity(String city) {
        if (taggedCities.containsKey(city)) {
            int cnt = taggedCities.get(city);
            taggedCities.put(city, cnt + 1);
        } else {
            taggedCities.put(city, 1);
        }
    }

    private String wordConverting(String word) {
        if (word.length() == 0) {
            return word;
        }

        word = word.toUpperCase();
        char firstLetter = word.charAt(0);
        word = word.toLowerCase();
        word = word.replace(word.charAt(0), firstLetter);

        return word;
    }
}
