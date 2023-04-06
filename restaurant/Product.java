package restaurant;

public record Product(String name, String description, String origin, ProductType type, Allergen allergen) {

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", allergen=" + allergen +
                '}';
    }
}
