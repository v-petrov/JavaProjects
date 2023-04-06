package restaurant;

public class Table {
    private static int tableCnt = 1;
    private final int tableNumber;
    private final byte seats;
    private boolean isReserved;
    private boolean isTaken;

    public Table(byte seats, boolean isReserved) {
        this.tableNumber = tableCnt++;
        this.seats = seats;
        this.isReserved = isReserved;
        if (this.isReserved) {
            this.isTaken = true;
        }
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public byte getSeats() {
        return seats;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public boolean isTaken() {
        return !isTaken;
    }

    public void setTaken(boolean taken) {
        this.isTaken = taken;
    }
    public void setReserved(boolean reserved) {
        this.isReserved = reserved;
    }


}
