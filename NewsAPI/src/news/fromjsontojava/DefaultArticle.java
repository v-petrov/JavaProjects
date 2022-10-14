package news.fromjsontojava;

public class DefaultArticle implements Article {

    private DefaultSource source;
    private String author;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public DefaultSource getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Article: " + '\n' +
                "Source: " + source + '\n' +
                "Author: " + author + '\n' +
                "Title:" + title + '\n' +
                "Url:" + url + '\n' +
                "UrlToImage:" + urlToImage + '\n' +
                "PublishedAt:" + publishedAt + '\n' +
                "Content:" + content + '\n' + '\n';
    }
}
