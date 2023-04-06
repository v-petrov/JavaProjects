package restaurant;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private static final int SERVER_PORT = 7777;
    private static final String FILE_NAME = "reservations.txt";
    private static int reservationCounter = 1;
    private static final int RESERVATION_NUMBER = 0;
    private static final int CLIENT_NAME = 1;
    private static final int TABLE_NUMBER = 2;
    private static final int WORKING_WAITERS = 10;
    private int cntWaiters = 0;
    private final List<Table> tables;
    private final ExecutorService executor;
    private final List<Waiter> waiters;

    public Server(List<Table> tables, List<Waiter> waiters) {
        this.tables = tables;
        this.waiters = waiters;
        this.executor = Executors.newFixedThreadPool(WORKING_WAITERS);
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            while (true) {
                try (Socket client = serverSocket.accept();
                     Scanner in = new Scanner(client.getInputStream());
                     PrintWriter out = new PrintWriter(client.getOutputStream())) {

                    String reply;
                    out.println("Welcome to our restaurant!");
                    out.println("Do you want to make a reservation? (Y/N)");
                    reply = in.nextLine();
                    if (reply.equalsIgnoreCase("Y")) {
                        makeAReservation(in, out);
                        return;
                    }
                    out.println("Do you have a reservation? (Y/N)");
                    reply = in.nextLine();

                    if (reply.equalsIgnoreCase("Y")) {
                        out.println("Tell me your reservation number? (One number in [1, 1.000.000])");
                        int resNumber = Integer.parseInt(in.nextLine());
                        out.println("Tell me your name? ([firstName lastName])");
                        String clientName = in.nextLine();

                        Table resTable = isThereReservation(resNumber, clientName);

                        if (resTable != null) {
                            out.println("Your reservation is valid, let me take you to your table...");
                            Table t = waiter(client, resTable);
                            settingTable(t);
                        } else {
                            out.println("I'm sorry but there is not a reservation on your name!");
                        }

                    } else {
                        out.println("Table with how many seats do you want!");
                        int seats = Integer.parseInt(in.nextLine());

                        Table table = isAvailableTable(seats);

                        if (table != null) {
                            out.println("There is available table, let me take you to it...");
                            Table t = waiter(client, table);
                            settingTable(t);
                        } else {
                            out.println("Sorry, currently we don't have table with that many seats.");
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Couldn't open the server!!!");
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private Table waiter(Socket client, Table table) {
        Table t = null;
        if (!(cntWaiters == 10)) {
            Waiter currWaiter = waiters.get(cntWaiters++);
            currWaiter.setClient(client);
            currWaiter.setTable(table);
            Future<Table> usedTable = executor.submit(currWaiter);
            try {
                t = usedTable.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return t;
    }

    private Table isThereReservation(int reservationNumber, String clientName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                int resNumber = Integer.parseInt(tokens[RESERVATION_NUMBER]);
                String name = tokens[CLIENT_NAME];
                int tableNumber = Integer.parseInt(tokens[TABLE_NUMBER]);
                if (reservationNumber == resNumber && clientName.equalsIgnoreCase(name)) {
                    for (Table table : tables) {
                        if (table.getTableNumber() == tableNumber) {
                            return table;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File couldn't be found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O exception occurred!");
            e.printStackTrace();
        }

        return null;
    }

    private Table isAvailableTable(int seats) {
        for (Table table : tables) {
            if (table.isTaken()) {
                if (table.getSeats() == seats) {
                    table.setTaken(true);
                    return table;
                }
            }
        }

        return null;
    }

    private void settingTable(Table table) {
        for (Table t : tables) {
            if (t.getTableNumber() == table.getTableNumber()) {
                t.setTaken(false);
                t.setReserved(false);
            }
        }
    }

    private void makeAReservation(Scanner in, PrintWriter out) {
        out.println("On what name is the reservation? (firstName lastName)");
        String cName = in.nextLine();
        out.println("For how many people is the reservation? (One number)");
        int people = Integer.parseInt(in.nextLine());
        if (!saveReservation(cName, people)) {
            out.println("Sorry there isn't available table with that many seats. Try again later!");
            return;
        }
        out.println("Your reservation is ready!");
    }

    private boolean saveReservation(String cName, int people) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            Table table = availableTable(people);
            if (table != null) {
                writer.write(reservationCounter++ + ",");
                writer.write(cName + ",");
                writer.write(table.getTableNumber());
                return true;
            }
        }  catch (IOException e) {
            System.out.println("I/O exception occurred!");
            e.printStackTrace();
        }
        return false;
    }

    private Table availableTable(int seats) {
        for (Table t : tables) {
            if (t.isTaken()) {
                if (t.getSeats() == seats) {
                    return t;
                }
            }
        }

        return null;
    }
    public int getCntWaiters() {
        return cntWaiters;
    }

    public void setCntWaiters(int cntWaiters) {
        this.cntWaiters = cntWaiters;
    }
}
