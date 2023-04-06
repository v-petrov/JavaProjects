package restaurant;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Waiter implements Callable<Table> {

    private static final int ITEM_NAME = 0;
    private static final int QUANTITY = 1;
    private static final Object lock = new Object();
    private final String name;
    private final Menu menu;
    private Socket client;
    private Table table;
    private String[] notePad;

    public Waiter(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    @Override
    public Table call() {
        try (Scanner in = new Scanner(client.getInputStream());
             PrintWriter out = new PrintWriter(client.getOutputStream())) {

            out.println("Welcome to your table, I'm your waiter for the night " + this.name + ".Here is the menu!");
            out.println(menu.printMenu());

            clientOrder(in, out);
            busyWaiter(5000);
            out.println("Here is all your food and drinks: " + Arrays.toString(notePad));
            busyWaiter(5001);
            readyForCheck(in, out);


        } catch (IOException e) {
            System.out.println("I/O exception occurred!");
            e.printStackTrace();
        }

        return table;
    }

    private void clientOrder(Scanner in, PrintWriter out) {
        out.println("Are you ready to order! (Y/N)");
        String reply = in.nextLine();
        while (!reply.equalsIgnoreCase("Y")) {
            busyWaiter(5000);
            out.println("Are you ready to order! (Y/N)");
            reply = in.nextLine();

        }

        out.println("Great, I'm listening, what do you want to order! (Item's name,Item's quantity):(...)");
        String order = in.nextLine();
        notePad = order.split(":");
        out.println("Your order is coming right up!");
    }

    private void busyWaiter(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Waiter got interrupted");
            e.printStackTrace();
        }
    }

    private void readyForCheck(Scanner in, PrintWriter out) {
        out.println("Do you want your check? (Y/N)");
        String reply = in.nextLine();
        while (!reply.equalsIgnoreCase("Y")) {
            busyWaiter(5000);
            out.println("Do you want your check? (Y/N)");
            reply = in.nextLine();
        }

        Order order = creatingOrder();
        out.println("Here is your order: " + order);
        savingOrder(order);
    }

    private void savingOrder(Order order) {
        synchronized (lock) {
            order.saveOrder();
        }
    }

    private Order creatingOrder() {
        String[] tokens;
        Order order = new Order(LocalDateTime.now(), this, table);

        for (String itemQuantity : notePad) {
            tokens = itemQuantity.split(",");
            String itemName = tokens[ITEM_NAME];
            int quantity = Integer.parseInt(tokens[QUANTITY]);
            Item currItem = menu.getItem(itemName);
            order.addItemToTheOrder(currItem, quantity);
            order.totalSum();
        }
        return order;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}