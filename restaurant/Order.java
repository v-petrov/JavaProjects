package restaurant;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order implements OrderAPI {

    private static final String FILE_NAME = "orders.txt";
    private static int orderCnt = 1;
    private final int orderNumber;
    private final LocalDateTime dateTime;
    private double total;
    private final Waiter waiter;
    private final Table table;
    private final Map<Item, Integer> orderItems;

    public Order(LocalDateTime dateTime, Waiter waiter, Table table) {
        this.orderNumber = orderCnt++;
        this.dateTime = dateTime;
        this.waiter = waiter;
        this.table = table;
        this.orderItems = new HashMap<>();
    }

    @Override
    public void saveOrder() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(orderNumber + "," + dateTime + "," + waiter.toString() + "," + table.toString() + "," + this.total);
            writer.write("{");

            for (Map.Entry<Item, Integer> itemQuantity : orderItems.entrySet()) {
                Item currItem = itemQuantity.getKey();
                Integer quantity = itemQuantity.getValue();
                writer.write(currItem.toString() + "-" + quantity + ",");
            }
            writer.write("}");

        } catch (FileNotFoundException e) {
            System.out.println("File couldn't be found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O exception occurred");
            e.printStackTrace();
        }
    }

    @Override
    public void totalSum() {
        var items = orderItems.entrySet().iterator();
        double totalSum = 0;
        while (items.hasNext()) {
            var itemQuantity = items.next();
            Item currItem = itemQuantity.getKey();
            Integer quantity = itemQuantity.getValue();
            totalSum += currItem.getPrize() * quantity;
        }

        total = totalSum;
    }

    @Override
    public void addItemToTheOrder(Item item, int quantity) {
        orderItems.put(item, quantity);
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public Waiter getWaiter() {
        return waiter;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public Map<Item, Integer> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", dateTime=" + dateTime +
                ", total=" + total +
                ", waiter=" + waiter +
                ", table=" + table +
                ", orderItems=" + orderItems +
                '}';
    }
}
