package news.fromjsontojava;

public interface Page {

    String getStatus();

    int getTotalResults();

    Article[] getArticles();

    String toString();
}
