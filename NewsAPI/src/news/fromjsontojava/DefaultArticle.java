package news.fromjsontojava;

public class DefaultArticle implements Article {

    private DefaultSource source;
    private String author;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    @Override
    public DefaultSource getSource() {
        return source;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUrlToImage() {
        return urlToImage;
    }

    @Override
    public String getPublishedAt() {
        return publishedAt;
    }

    @Override
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
