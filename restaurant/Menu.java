package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Menu {
    private final List<Item> items;
    public Menu(List<Item> items) {
        this.items = rearrangedMenu(items);
    }
    public String printMenu() {
        StringBuilder menuItems = new StringBuilder();
        boolean al = true, nal = true, p = true, ap = true, md = true, d = true;
        for (Item item : items) {
            switch (item.getType()) {
                case ALCOHOL_DRINK -> {
                    if (al) {
                        menuItems.append("Alcoholic drinks:\n");
                        al = false;
                    }
                    menuItems.append(item.menuItemPrint());
                }
                case NON_ALCOHOL_DRINK -> {
                    if (nal) {
                        menuItems.append("NonAlcoholic drinks:\n");
                        nal = false;
                    }
                    menuItems.append(item.menuItemPrint());
                }
                case PASTRY -> {
                    if (p) {
                        menuItems.append("Pastries:\n");
                        p = false;
                    }
                    menuItems.append(item.menuItemPrint());
                }
                case APPETIZER -> {
                    if (ap) {
                        menuItems.append("Appetizers:\n");
                        ap = false;
                    }
                    menuItems.append(item.menuItemPrint());
                }
                case MAIN_DISH -> {
                    if (md) {
                        menuItems.append("Main dishes:\n");
                        md = false;
                    }
                    menuItems.append(item.menuItemPrint());
                }
                case DESSERT -> {
                    if (d) {
                        menuItems.append("Desserts:\n");
                        d = false;
                    }
                    menuItems.append(item.menuItemPrint());
                }
            }

        }

        return menuItems.toString();
    }

    private List<Item> rearrangedMenu(List<Item> items) {
        List<Item> rearrangedItems = new ArrayList<>();

        Map<ItemType, List<Item>> itemsByType = items.stream()
                .collect(groupingBy(Item::getType));


        return rearrangedItems;
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}