package news.fromjsontojava;

public class DefaultSource implements Source {

    private String id;
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id: " + id +
                " name: " + name;
    }
}
