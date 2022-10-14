package news.connection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class GettingUri {

    private final static String API_KEY = "4ac4751cbb2d4f4991c71c91f6993f8a";

    private final String uri;

    public GettingUri() {
        String searchingBy = categorySearching();
        uri = String.format("https://newsapi.org/v2/top-headlines?%s&pageSize=50&page=1&apiKey=%s", searchingBy, API_KEY);
    }

    private String categorySearching() {
        examples();
        System.out.print("Write a category on which you want to search: ");
        String categoryBy;

        try (Scanner sc = new Scanner(System.in)) {
            categoryBy = sc.nextLine();
        }

        return categoryBy;
    }

    private void examples() {
        try (var reader = new BufferedReader(new FileReader("example"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File couldn't be found!");
        } catch (IOException e) {
            System.out.println("Couldn't read from file!");
        }

    }

    public String getURI() {
        return uri;
    }
}
