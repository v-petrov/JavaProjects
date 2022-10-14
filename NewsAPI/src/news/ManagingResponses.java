package news;

import com.google.gson.Gson;
import news.exceptions.BadRequest;
import news.exceptions.ServerError;
import news.exceptions.TooManyRequests;
import news.exceptions.Unauthorized;
import news.fromjsontojava.DefaultPage;
import news.fromjsontojava.Page;

import java.io.*;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ManagingResponses {

    private final Map<Integer, Page> pages = new HashMap<>();

    private int NumberOfPage = 1;

    public void checkingForErrors(HttpResponse<Path> response) throws BadRequest, Unauthorized, TooManyRequests, ServerError {
        switch (response.statusCode()) {
            case 400 ->
                    throw new BadRequest("The request was unacceptable, often due to a missing or misconfigured parameter");
            case 401 -> throw new Unauthorized(" Your API key was missing from the request, or wasn't correct.");
            case 429 ->
                    throw new TooManyRequests("You made too many requests within a window of time and have been rate limited. Back off for a while.");
            case 500 -> throw new ServerError("Something went wrong on our side.");
            default -> System.out.println(response.statusCode());
        }
    }

    public Page createPage() {
        Page newPage;
        Gson gson = new Gson();

        try (var reader = new FileReader("page.json")) {
            newPage = gson.fromJson(reader, DefaultPage.class);
            return newPage;

        } catch (FileNotFoundException e) {
            System.out.println("File couldn't be found!");
            return null;

        } catch (IOException e) {
            System.out.println("Couldn't read from file!");
            return null;
        }
    }

    public void writingPageToFile(Page page) {
        pages.put(NumberOfPage, page);
        try (var writer = new BufferedWriter(new FileWriter(String.format("page%d.txt", NumberOfPage++)))) {
            assert page != null;
            writer.write(pages.get(NumberOfPage - 1).toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Couldn't write to the file!");
        }
    }
}
