package news.fromjsontojava;

public interface Article {

    Source getSource();

    String getAuthor();

    String getTitle();

    String getUrl();

    String getUrlToImage();

    String getPublishedAt();

    String getContent();

    String toString();
}
