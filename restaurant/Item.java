package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {

    private final String name;
    private final int grammage;
    private final double prize;
    private final ItemType type;
    private final List<Product> products;

    public Item(String name, int grammage, double prize, ItemType type) {
        this.name = name;
        this.grammage = grammage;
        this.prize = prize;
        this.type = type;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public String getName() {
        return name;
    }

    public int getGrammage() {
        return grammage;
    }

    public double getPrize() {
        return prize;
    }

    public ItemType getType() {
        return type;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String menuItemPrint() {
        return "Item{" +
                "name='" + name + '\'' +
                ", grammage=" + grammage +
                ", prize=" + prize +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return grammage == item.grammage && Double.compare(item.prize, prize) == 0 && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grammage, prize);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", grammage=" + grammage +
                ", prize=" + prize +
                ", products=" + products +
                '}';
    }
}
