package restaurant;

import java.time.LocalDateTime;
import java.util.Map;

public interface OrderAPI {

    LocalDateTime getDateTime();

    double getTotal();

    Waiter getWaiter();

    Table getTable();

    Map<Item, Integer> getOrderItems();

    void addItemToTheOrder(Item item, int quantity);

    void saveOrder();

    void totalSum();
}
