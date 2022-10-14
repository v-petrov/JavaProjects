package news.fromjsontojava;

public class DefaultSource implements Source {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id: " + id +
                " name: " + name;
    }
}
