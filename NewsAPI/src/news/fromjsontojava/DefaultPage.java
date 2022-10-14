package news.fromjsontojava;

import java.util.Arrays;

public class DefaultPage implements Page {

    private String status;

    private int totalResults;

    private DefaultArticle[] articles;


    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public DefaultArticle[] getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Article article : articles) {
            sb.append(article.toString());
        }
        return sb.toString();
    }
}
