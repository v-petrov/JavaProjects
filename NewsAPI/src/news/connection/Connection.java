package news.connection;

import news.ManagingResponses;
import news.exceptions.BadRequest;
import news.exceptions.ServerError;
import news.exceptions.TooManyRequests;
import news.exceptions.Unauthorized;
import news.fromjsontojava.Page;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class Connection {
    private final ManagingResponses manager;
    private String uri;
    private int previousPageNumber = 1;
    private int nextPageNumber = 2;

    public Connection() {
        this.uri = new GettingUri().getURI();
        this.manager = new ManagingResponses();

    }

    public static void main(String[] args) throws BadRequest, ServerError, TooManyRequests, Unauthorized, IOException, InterruptedException {
        new Connection().connect();
    }

    public void connect() throws IOException, InterruptedException, BadRequest, ServerError, TooManyRequests, Unauthorized {
        boolean ff = true;
        int responses = 0;

        var client = HttpClient.newBuilder().build();
        do {
            var request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(uri))
                    .build();

            uri = uri.replace(String.format("page=%d", previousPageNumber++), String.format("page=%d", nextPageNumber++));
            Path filePath = Path.of("page.json");

            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(filePath));
            manager.checkingForErrors(response);

            Page page = manager.createPage();
            manager.writingPageToFile(page);

            Files.delete(filePath);

            if (ff) {
                responses = page.getTotalResults();
                ff = false;
            }
            responses -= 50;

        } while (responses > 0);
    }
}
